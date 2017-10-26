package com.embracesource.yilianti.ui.homepage.diagnosis;

import android.content.Intent;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.embracesource.yilianti.R;
import com.embracesource.yilianti.bean.CustomerServiceDiagnosisListBean;
import com.embracesource.yilianti.bean.enums.DiagnosisExaminationType;
import com.embracesource.yilianti.bean.DiagnosisItemBean;
import com.embracesource.yilianti.bean.HospitalWaitHandleListBean;
import com.embracesource.yilianti.bean.MyLaunchListBean;
import com.embracesource.yilianti.bean.SimpleBean;
import com.embracesource.yilianti.bean.UserType;
import com.embracesource.yilianti.bean.eventbus.RefreshDiagnosisListBean;
import com.embracesource.yilianti.common.adapter.CommonAdapter;
import com.embracesource.yilianti.common.adapter.ViewHolder;
import com.embracesource.yilianti.common.memory.MyPrefrences;
import com.embracesource.yilianti.common.recyclerview.SwipeRecyclerView;
import com.embracesource.yilianti.databinding.ActivityDiagnosisPictureBinding;
import com.embracesource.yilianti.ui.base.AacBaseActivity;
import com.embracesource.yilianti.util.StringUtils;
import com.embracesource.yilianti.viewmodel.DiagnosisPictureCallBack;
import com.orhanobut.logger.Logger;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import me.zhouzhuo.zzhorizontalprogressbar.ZzHorizontalProgressBar;

/**
 * 会诊/转诊 列表
 */
public class DiagnosisPictureActivity extends AacBaseActivity<ActivityDiagnosisPictureBinding> implements DiagnosisPictureCallBack {

    //    @Inject
    DiagnosisPictureViewModel viewModel;
    private CommonAdapter mAdapter;
    private int currentPage = 1;
    private static int pageSize = 20;
    private int currentCheckPage;//tab当前选择的页面
    private int role;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        DaggerAppComponent.builder().appModule(new AppModule()).build().inject(this); //注入
        EventBus.getDefault().register(this);
        role = myPrefrences.getInt(MyPrefrences.Key.role);
        initView();

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_diagnosis_picture;
    }

    @Override
    protected void initView() {
        viewModel = new DiagnosisPictureViewModel(this);
        switch (role) {
            case UserType.DOCTOR://医生
                initTitleLayout(getString(R.string.diagnosis));
                setTitleRightVisiable(getString(R.string.apply_diagnosis), new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //申请会诊
                        Intent intent = new Intent(mContext, ApplyDiagnosisActivity01.class);
                        startActivity(intent);
                    }
                });
                break;
            case UserType.Medical_Service://医务处，上级医院
                initTitleLayout(getString(R.string.wait_handle));
                binding.radioGroup.setVisibility(View.GONE);
                break;
            case UserType.Customer_Service://客服
                binding.radioGroup.setVisibility(View.VISIBLE);
                binding.rbMyLaunch.setText(getString(R.string.doing));
                binding.rbMyParticipate.setText(R.string.finished);
                break;
        }

        initRecycler();
    }

    private void initRecycler() {
        List<Object> mList = new ArrayList<>();
        int layout;
        if (role == UserType.Customer_Service) {//客服
            layout = R.layout.diagnosis_item_customer_service;
        } else {
            layout = R.layout.diagnosis_item;
        }
        mAdapter = new CommonAdapter(this, layout, mList) {
            @Override
            protected void convert(ViewHolder viewHolder, Object item, int position) {
                if (role == UserType.Customer_Service) {//客服
                    final CustomerServiceDiagnosisListBean.DataBean bean = (CustomerServiceDiagnosisListBean.DataBean) item;
                    ZzHorizontalProgressBar progressBar = (ZzHorizontalProgressBar) viewHolder.itemView.findViewById(R.id.progressBar);
                    View tv1 = viewHolder.itemView.findViewById(R.id.tv1);
                    View tv2 = viewHolder.itemView.findViewById(R.id.tv2);
                    View tv3 = viewHolder.itemView.findViewById(R.id.tv3);
                    View tv4 = viewHolder.itemView.findViewById(R.id.tv4);
                    TextView tv_name = (TextView) viewHolder.itemView.findViewById(R.id.tv_name);
                    Button btn = (Button) viewHolder.itemView.findViewById(R.id.next_step);
                    tv_name.setText(bean.getPatientName());
                    btn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {//下一步
//                            操作
//                                    短信通知
//                            http://192.168.1.165:8002/workbench/medicalService/op/{id}?available=8
//                            医生就诊
//                            http://192.168.1.165:8002/workbench/medicalService/op/{id}?available=9
//                            就诊结果
//                            http://192.168.1.165:8002/workbench/medicalService/op/{id}?available=10
//                            平台随访
//                            http://192.168.1.165:8002/workbench/medicalService/op/{id}?available=11
                            viewModel.nextStep(bean.getId(), bean.getAvailable());
                        }
                    });

                    GradientDrawable drawable = (GradientDrawable) btn.getBackground();
                    drawable.setColor(getResources().getColor(R.color.green));
                    btn.setEnabled(true);
                    btn.setText(getString(R.string.next_step));
                    switch (bean.getAvailable()) {
                        case 6:
                            GradientDrawable drawable01 = (GradientDrawable) tv1.getBackground();
                            drawable01.setColor(getResources().getColor(R.color.color_a1a1a1));
                            GradientDrawable drawable02 = (GradientDrawable) tv2.getBackground();
                            drawable02.setColor(getResources().getColor(R.color.color_a1a1a1));
                            GradientDrawable drawable03 = (GradientDrawable) tv3.getBackground();
                            drawable03.setColor(getResources().getColor(R.color.color_a1a1a1));
                            GradientDrawable drawable04 = (GradientDrawable) tv4.getBackground();
                            drawable04.setColor(getResources().getColor(R.color.color_a1a1a1));
                            progressBar.setProgress(0);
                            break;
                        case 8:
                            GradientDrawable drawable11 = (GradientDrawable) tv1.getBackground();
                            drawable11.setColor(getResources().getColor(R.color.green));
                            GradientDrawable drawable12 = (GradientDrawable) tv2.getBackground();
                            drawable12.setColor(getResources().getColor(R.color.color_a1a1a1));
                            GradientDrawable drawable13 = (GradientDrawable) tv3.getBackground();
                            drawable13.setColor(getResources().getColor(R.color.color_a1a1a1));
                            GradientDrawable drawable14 = (GradientDrawable) tv4.getBackground();
                            drawable14.setColor(getResources().getColor(R.color.color_a1a1a1));
                            progressBar.setProgress(1);
                            break;
                        case 9:
                            GradientDrawable drawable21 = (GradientDrawable) tv1.getBackground();
                            drawable21.setColor(getResources().getColor(R.color.green));
                            GradientDrawable drawable22 = (GradientDrawable) tv2.getBackground();
                            drawable22.setColor(getResources().getColor(R.color.green));
                            GradientDrawable drawable23 = (GradientDrawable) tv3.getBackground();
                            drawable23.setColor(getResources().getColor(R.color.color_a1a1a1));
                            GradientDrawable drawable24 = (GradientDrawable) tv4.getBackground();
                            drawable24.setColor(getResources().getColor(R.color.color_a1a1a1));
                            progressBar.setProgress(3);
                            break;
                        case 10:
                            GradientDrawable drawable31 = (GradientDrawable) tv1.getBackground();
                            drawable31.setColor(getResources().getColor(R.color.green));
                            GradientDrawable drawable32 = (GradientDrawable) tv2.getBackground();
                            drawable32.setColor(getResources().getColor(R.color.green));
                            GradientDrawable drawable33 = (GradientDrawable) tv3.getBackground();
                            drawable33.setColor(getResources().getColor(R.color.green));
                            GradientDrawable drawable34 = (GradientDrawable) tv4.getBackground();
                            drawable34.setColor(getResources().getColor(R.color.color_a1a1a1));
                            progressBar.setProgress(5);
                            break;
                        case 11:
                            GradientDrawable drawable41 = (GradientDrawable) tv1.getBackground();
                            drawable41.setColor(getResources().getColor(R.color.green));
                            GradientDrawable drawable42 = (GradientDrawable) tv2.getBackground();
                            drawable42.setColor(getResources().getColor(R.color.green));
                            GradientDrawable drawable43 = (GradientDrawable) tv3.getBackground();
                            drawable43.setColor(getResources().getColor(R.color.green));
                            GradientDrawable drawable44 = (GradientDrawable) tv4.getBackground();
                            drawable44.setColor(getResources().getColor(R.color.green));
                            progressBar.setProgress(8);

                            GradientDrawable drawable5 = (GradientDrawable) btn.getBackground();
                            drawable5.setColor(getResources().getColor(R.color.color_a1a1a1));
                            btn.setEnabled(false);
                            btn.setText(getString(R.string.finish));
                            break;
                        default:
                            GradientDrawable drawableD = (GradientDrawable) btn.getBackground();
                            drawableD.setColor(getResources().getColor(R.color.color_a1a1a1));
                            btn.setEnabled(false);
                            btn.setText(getString(R.string.finish));
                            break;
                    }
                } else {//非客服
                    final DiagnosisItemBean entity = (DiagnosisItemBean) item;
                    TextView tv_name = viewHolder.getView(R.id.tv_name);
                    TextView tv_status = viewHolder.getView(R.id.tv_status);
                    TextView tv_content = viewHolder.getView(R.id.tv_content);
                    TextView tv_date = viewHolder.getView(R.id.tv_date);
                    switch (role) {
                        case UserType.DOCTOR://医生
                            tv_name.setText(entity.getPatientName());
                            break;
                        case UserType.Medical_Service://医务处，上级医院
                            tv_name.setText(entity.getChiefComplaint());
                            break;
                        case UserType.Customer_Service://客服
                            tv_name.setText(entity.getPatientName());
                            break;
                    }
                    String type = DiagnosisExaminationType.getDiagnosisExaminationTypeString(entity.getAvailable());
                    tv_status.setText(type);
                    if (StringUtils.isNullorEmpty(entity.getIllnessDescription())) {
                        tv_content.setVisibility(View.GONE);
                    } else {
                        tv_content.setVisibility(View.VISIBLE);
                    }
                    tv_content.setText(entity.getIllnessDescription());
                    tv_date.setText(entity.getCreatedTime());
                    viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(DiagnosisPictureActivity.this, ApplyDiagnosisDetailActivity.class);
                            intent.putExtra(ApplyDiagnosisDetailActivity.class.getName(), entity.getId());
                            intent.putExtra(ApplyDiagnosisDetailActivity.IS_participate, currentCheckPage);//判断 是我发起的，还是我参与的，（医生，我参与的 详情中需要显示审批按钮）
                            startActivity(intent);
                        }
                    });
                }
            }
        };

        binding.swipeRecyclerView.setAdapter(mAdapter);
        binding.swipeRecyclerView.getRecyclerView().setLayoutManager(new LinearLayoutManager(this));
        binding.radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                currentPage = 1;
                requestList(checkedId);
            }
        });
        binding.rbMyLaunch.setChecked(true);
        binding.swipeRecyclerView.setOnLoadListener(new SwipeRecyclerView.OnLoadListener() {
            @Override
            public void onRefresh() {
                currentPage = 1;
                requestList(currentCheckPage);
                binding.swipeRecyclerView.setRefreshing(false);
            }

            @Override
            public void onLoadMore() {
                switch (currentCheckPage) {
                    case R.id.rb_my_launch:
//                        showDialog();
                        viewModel.getMyLaunchList(++currentPage, pageSize);
                        break;
                    case R.id.rb_my_participate:
//                        showDialog();
                        viewModel.getMyParticipateList(++currentPage, pageSize);
                        break;
                }
            }
        });
    }


    private void requestList(int checkedId) {
        switch (role) {
            case UserType.DOCTOR://医生
                currentCheckPage = checkedId;
                switch (checkedId) {
                    case R.id.rb_my_launch:
                        showDialog();
                        viewModel.getMyLaunchList(currentPage, pageSize);
                        break;
                    case R.id.rb_my_participate:
                        showDialog();
                        viewModel.getMyParticipateList(currentPage, pageSize);
                        break;
                }
                break;

            case UserType.Medical_Service://医务处，上级医院
                showDialog();
                viewModel.getHospitalList(currentPage, pageSize);
                break;
            case UserType.Customer_Service://客服
                switch (checkedId) {
                    case R.id.rb_my_launch:
                        showDialog();
                        viewModel.getCustomerServiceList(0);
                        break;
                    case R.id.rb_my_participate:
                        showDialog();
                        viewModel.getCustomerServiceList(1);
                        break;
                }

                break;
        }


    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void getMyLaunchListOK(MyLaunchListBean response, int pageNum) {
        if (response != null && response.getData() != null && response.getData().getList() != null) {
            refreshView(response.getData().getList(), pageNum);
        } else {
            refreshView(new ArrayList<DiagnosisItemBean>(), pageNum);
        }
    }

    @Override
    public void getMyParticipateListOK(MyLaunchListBean response, int pageNum) {
        if (response != null && response.getData() != null && response.getData().getList() != null) {
            refreshView(response.getData().getList(), pageNum);
        } else {
            refreshView(new ArrayList<DiagnosisItemBean>(), pageNum);
        }
    }

    @Override
    public void getHospitalListOK(HospitalWaitHandleListBean response, int pageNum) {
        if (response != null && response.getData() != null && response.getData().getList() != null) {
            refreshView(response.getData().getList(), pageNum);
        } else {
            refreshView(new ArrayList<DiagnosisItemBean>(), pageNum);
        }

    }

    @Override
    public void customer_Service_nextStepOK(SimpleBean response) {
        currentPage = 1;
        requestList(currentCheckPage);
    }

    @Override
    public void getCustomerServiceListOK(CustomerServiceDiagnosisListBean response) {
        if (response != null && response.getData() != null) {
            refreshCustomerServiceView(response.getData());
        }
    }

    private void refreshCustomerServiceView(List<CustomerServiceDiagnosisListBean.DataBean> data) {
        binding.swipeRecyclerView.setLoadMoreEnable(false);
        binding.swipeRecyclerView.complete();
        mAdapter.setDatas(data);
        if (data.size() == 0) {
            showEmptyView();
        }
    }

    private void refreshView(List<DiagnosisItemBean> response, int pageNum) {
        binding.swipeRecyclerView.stopLoadingMore();
        binding.swipeRecyclerView.complete();
        if (response.size() < pageSize) {
            binding.swipeRecyclerView.onNoMore(getString(R.string.recyclerview_nomore));
        }
        if (pageNum == 1) {
            if (response == null || response.isEmpty()) {
                showEmptyView();
            }
            mAdapter.setDatas(response);
        } else {
            if (response == null || response.isEmpty()) {
                mAdapter.addDatas(response);
            }
        }
    }

    private void showEmptyView() {
        binding.swipeRecyclerView.setEmptyView(View.inflate(this, R.layout.list_empty_view, null));
    }

    @Subscribe(threadMode = ThreadMode.MAIN) //在ui线程执行
    public void onDataSynEvent(RefreshDiagnosisListBean event) {
//        binding.rbMyLaunch.setChecked(true);
        currentPage = 1;
        requestList(currentCheckPage);
        Logger.d("eventBus:--->" + RefreshDiagnosisListBean.class.getName());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}

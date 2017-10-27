package com.embracesource.yilianti.ui.homepage.diagnosis;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.embracesource.yilianti.R;
import com.embracesource.yilianti.bean.CustomerServiceDiagnosisListBean;
import com.embracesource.yilianti.bean.DiagnosisItemBean;
import com.embracesource.yilianti.bean.HospitalWaitHandleListBean;
import com.embracesource.yilianti.bean.MyLaunchListBean;
import com.embracesource.yilianti.bean.SimpleBean;
import com.embracesource.yilianti.bean.UserType;
import com.embracesource.yilianti.bean.eventbus.RefreshDiagnosisListBean;
import com.embracesource.yilianti.common.memory.MyPrefrences;
import com.embracesource.yilianti.common.recyclerview.SwipeRecyclerView;
import com.embracesource.yilianti.databinding.ActivityDiagnosisPictureBinding;
import com.embracesource.yilianti.ui.base.AacBaseActivity;
import com.embracesource.yilianti.viewmodel.DiagnosisPictureCallBack;
import com.orhanobut.logger.Logger;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

/**
 * 会诊/转诊 列表
 */
public class DiagnosisPictureActivity extends AacBaseActivity<ActivityDiagnosisPictureBinding> implements DiagnosisPictureCallBack {

    //    @Inject
    DiagnosisPictureViewModel viewModel;
    private DiagnosisPicAdapter mAdapter;
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
                binding.radioGroup.getTabAt(0).setText(getString(R.string.doing));
                binding.radioGroup.getTabAt(1).setText(getString(R.string.finished));
//                binding.rbMyLaunch.setText(getString(R.string.doing));
//                binding.rbMyParticipate.setText(R.string.finished);
                break;
        }

        initRecycler();
    }

    private void initRecycler() {
        //CommonAdapter
        mAdapter = new DiagnosisPicAdapter(role, viewModel, this);
        mAdapter.setOnItemClickListener(new DiagnosisPicAdapter.OnItemClickListener() {
            @Override
            public void onItemClickListener(View view, int position, Object obj) {
                DiagnosisItemBean entity = (DiagnosisItemBean) obj;
                Intent intent = new Intent(DiagnosisPictureActivity.this, ApplyDiagnosisDetailActivity.class);
                intent.putExtra(ApplyDiagnosisDetailActivity.class.getName(), entity.getId());
                intent.putExtra(ApplyDiagnosisDetailActivity.IS_participate, currentCheckPage);//判断 是我发起的，还是我参与的，（医生，我参与的 详情中需要显示审批按钮）
                startActivity(intent);
            }
        });
        binding.swipeRecyclerView.setAdapter(mAdapter);
        binding.swipeRecyclerView.getRecyclerView().setLayoutManager(new LinearLayoutManager(this));

     /*   binding.radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                currentPage = 1;
                requestList(checkedId);
            }
        });*/
        binding.radioGroup.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                currentPage = 1;
                requestList(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        binding.radioGroup.getTabAt(0).select();
        currentPage = 1;
        requestList(0);
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
//                    case R.id.rb_my_launch:
                    case 0:
                        showDialog();
                        viewModel.getMyLaunchList(currentPage, pageSize);
                        break;
//                    case R.id.rb_my_participate:
                    case 1:
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
//                    case R.id.rb_my_launch:
                    case 0:
                        showDialog();
                        viewModel.getCustomerServiceList(0);
                        break;
//                    case R.id.rb_my_participate:
                    case 1:
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
        mAdapter.setDatas_CustomerServiceDiagnosisListBean(data);
        if (data.size() == 0) {
            showEmptyView();
        }
    }

    private void refreshView(List<DiagnosisItemBean> response, int pageNum) {
        if (response == null) response = new ArrayList<>();
        binding.swipeRecyclerView.stopLoadingMore();
        binding.swipeRecyclerView.complete();
        if (response.size() < pageSize) {
            binding.swipeRecyclerView.onNoMore(getString(R.string.recyclerview_nomore));
        }
        if (pageNum == 1) {
            if (response.isEmpty()) {
                showEmptyView();
            }
            mAdapter.setDatas(response);
        } else {
            if (response.isEmpty()) {
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

package com.embracesource.yilianti.ui.homepage.diagnosis;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.embracesource.yilianti.R;
import com.embracesource.yilianti.bean.DiagnosisItemBean;
import com.embracesource.yilianti.bean.HospitalWaitHandleListBean;
import com.embracesource.yilianti.bean.MyLaunchListBean;
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

/**
 * 会诊/转诊 列表
 */
public class DiagnosisPictureActivity extends AacBaseActivity<ActivityDiagnosisPictureBinding> implements DiagnosisPictureCallBack {

    //    @Inject
    DiagnosisPictureViewModel viewModel;
    private CommonAdapter mAdapter;
    private int currentPage = 1;
    private static int pageSize = 20;
    private int currentCheckPage;//当前选择的页面
    private int role;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        DaggerAppComponent.builder().appModule(new AppModule()).build().inject(this); //注入
        EventBus.getDefault().unregister(this);
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
            case 2://医生
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
            case 5://医务处，上级医院
                initTitleLayout(getString(R.string.wait_handle));
                binding.radioGroup.setVisibility(View.GONE);
                break;
            case 6://客服
                //// TODO: 2017/10/20 0020  
                break;
        }

        initRecycler();
    }

    private void initRecycler() {
        List<Object> mList = new ArrayList<>();
        mAdapter = new CommonAdapter(this, R.layout.diagnosis_item, mList) {
            @Override
            protected void convert(ViewHolder viewHolder, Object item, int position) {
                final DiagnosisItemBean entity = (DiagnosisItemBean) item;
                TextView tv_name = viewHolder.getView(R.id.tv_name);
                TextView tv_status = viewHolder.getView(R.id.tv_status);
                TextView tv_content = viewHolder.getView(R.id.tv_content);
                TextView tv_date = viewHolder.getView(R.id.tv_date);

                switch (role) {
                    case 2://医生
                        tv_name.setText(entity.getPatientName());
                        break;

                    case 5://医务处，上级医院
                        tv_name.setText(entity.getChiefComplaint());
                        break;
                    case 6://客服
                        tv_name.setText(entity.getPatientName());
                        break;
                }


                String type;
                switch (entity.getAvailable()) {
                    case 1:
                        type = "等待医务处审核";
                        break;
                    case 2:
                        type = "等待上级医院审核";
                        break;
                    case 3:
                        type = "等待专家团队回复";
                        break;
                    default:
                        type = "已完成";
                        break;
                }
                tv_status.setText(type);
                if(StringUtils.isNullorEmpty(entity.getIllnessDescription())){
                    tv_content.setVisibility(View.GONE);
                }else {
                    tv_content.setVisibility(View.VISIBLE);
                }
                tv_content.setText(entity.getIllnessDescription());
                tv_date.setText(entity.getCreatedTime());
                viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(DiagnosisPictureActivity.this, ApplyDiagnosisDetailActivity.class);
                        intent.putExtra(ApplyDiagnosisDetailActivity.class.getName(), entity.getId());
                        startActivity(intent);
                    }
                });
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
            case 2://医生
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

            case 5://医务处，上级医院
                viewModel.getHospitalList(currentPage, pageSize);
                break;
            case 6://客服
                //// TODO: 2017/10/20 0020  
                break;
        }


    }

    @Override
    protected void onResume() {
        super.onResume();
//        viewModel.getMyLaunchList(mContext, 0);
//        eventBus()refresh()//// TODO: 2017/10/19 0019
    }

    @Override
    public void getMyLaunchListOK(MyLaunchListBean response, int pageNum) {
        if (response != null && response.getData() != null)
            refreshView(response.getData().getList(), pageNum);
    }

    @Override
    public void getMyParticipateListOK(MyLaunchListBean response, int pageNum) {
        if (response != null && response.getData() != null)
            refreshView(response.getData().getList(), pageNum);
    }

    @Override
    public void getHospitalListOK(HospitalWaitHandleListBean response, int pageNum) {
        if (response != null && response.getData() != null)
            refreshView(response.getData().getList(), pageNum);
    }


    private void refreshView(List<DiagnosisItemBean> response, int pageNum) {
        binding.swipeRecyclerView.stopLoadingMore();
        binding.swipeRecyclerView.complete();
        if (pageNum == 1) {
            mAdapter.setDatas(response);
        } else {
            mAdapter.addDatas(response);
            if (response.size() == 0) {
                binding.swipeRecyclerView.setEmptyView(View.inflate(this, R.layout.list_empty_view, null));
            }
        }
        if (response.size() < pageSize) {
            binding.swipeRecyclerView.onNoMore(getString(R.string.recyclerview_nomore));
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN) //在ui线程执行
    public void onDataSynEvent(RefreshDiagnosisListBean event) {
//      refreshList();
        binding.rbMyLaunch.setChecked(true);
        Logger.d("eventBus:--->" + RefreshDiagnosisListBean.class.getName());
//        currentPage = 1;
//        requestList(R.id.rb_my_launch);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}

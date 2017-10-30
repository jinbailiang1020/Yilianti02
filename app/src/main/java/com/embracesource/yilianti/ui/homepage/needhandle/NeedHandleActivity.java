package com.embracesource.yilianti.ui.homepage.needhandle;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.embracesource.yilianti.R;
import com.embracesource.yilianti.bean.CustomerServiceDiagnosisListBean;
import com.embracesource.yilianti.bean.DiagnosisItemBean;
import com.embracesource.yilianti.bean.HospitalWaitHandleListBean;
import com.embracesource.yilianti.bean.MyLaunchListBean;
import com.embracesource.yilianti.bean.SimpleBean;
import com.embracesource.yilianti.bean.eventbus.RefreshDiagnosisListBean;
import com.embracesource.yilianti.common.memory.MyPrefrences;
import com.embracesource.yilianti.common.recyclerview.SwipeRecyclerView;
import com.embracesource.yilianti.databinding.ActivityNeedHandleBinding;
import com.embracesource.yilianti.ui.base.AacBaseActivity;
import com.embracesource.yilianti.ui.homepage.diagnosis.ApplyDiagnosisDetailActivity;
import com.embracesource.yilianti.viewmodel.NeedHandleCallBack;
import com.orhanobut.logger.Logger;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

/**
 * 待办事项
 */
public class NeedHandleActivity extends AacBaseActivity<ActivityNeedHandleBinding> implements NeedHandleCallBack {

    NeedHandleViewModel viewModel;
    private NeedHandleAdapter mAdapter;
    private int currentPage = 1;
    private static int pageSize = 20;
    private int currentCheckPage;//tab当前选择的页面
    private int role;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
        role = myPrefrences.getInt(MyPrefrences.Key.role);
        initView();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_need_handle;
    }

    @Override
    protected void initView() {
        viewModel = new NeedHandleViewModel(this);
        initTitleLayout(getString(R.string.wait_handle));
        initRecycler();
    }

    private void initRecycler() {
        //CommonAdapter
        mAdapter = new NeedHandleAdapter(role, viewModel, this);
        mAdapter.setOnItemClickListener(new NeedHandleAdapter.OnItemClickListener() {
            @Override
            public void onItemClickListener(View view, int position, Object obj) {
                DiagnosisItemBean entity = (DiagnosisItemBean) obj;
                Intent intent = new Intent(NeedHandleActivity.this, ApplyDiagnosisDetailActivity.class);
                intent.putExtra(ApplyDiagnosisDetailActivity.class.getName(), entity.getId());
                intent.putExtra(ApplyDiagnosisDetailActivity.IS_participate, true);//待办事项 ，一定需要编辑（）
                startActivity(intent);
            }
        });
        binding.swipeRecyclerView.setAdapter(mAdapter);
        binding.swipeRecyclerView.getRecyclerView().setLayoutManager(new LinearLayoutManager(this));

        currentPage = 1;
        requestList();
        binding.swipeRecyclerView.setOnLoadListener(new SwipeRecyclerView.OnLoadListener() {
            @Override
            public void onRefresh() {
                currentPage = 1;
                requestList();
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

    private void requestList() {
        viewModel.getNeedHandleList(currentPage, pageSize);
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
    public void getNeedHandleListOK(HospitalWaitHandleListBean response, int pageNum) {
        if (response != null && response.getData() != null && response.getData().getList() != null) {
            refreshView(response.getData().getList(), pageNum);
        } else {
            refreshView(new ArrayList<DiagnosisItemBean>(), pageNum);
        }
    }

    @Override
    public void customer_Service_nextStepOK(SimpleBean response) {
        currentPage = 1;
        requestList();
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
        requestList();
        Logger.d("eventBus:--->" + RefreshDiagnosisListBean.class.getName());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}

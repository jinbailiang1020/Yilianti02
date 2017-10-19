package com.embracesource.yilianti.ui.homepage.diagnosis;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.embracesource.yilianti.R;
import com.embracesource.yilianti.bean.MyLaunchListBean;
import com.embracesource.yilianti.common.adapter.CommonAdapter;
import com.embracesource.yilianti.common.adapter.ViewHolder;
import com.embracesource.yilianti.common.recyclerview.SwipeRecyclerView;
import com.embracesource.yilianti.databinding.ActivityDiagnosisPictureBinding;
import com.embracesource.yilianti.ui.base.AacBaseActivity;
import com.embracesource.yilianti.viewmodel.DiagnosisPictureCallBack;

import java.util.ArrayList;
import java.util.List;

public class DiagnosisPictureActivity extends AacBaseActivity<ActivityDiagnosisPictureBinding> implements DiagnosisPictureCallBack {

    //    @Inject
    DiagnosisPictureViewModel viewModel;
    private CommonAdapter mAdapter;
    private int currentPage = 1;
    private final static int pageSize = 20;
    private int currentCheckPage;//当前选择的页面

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        DaggerAppComponent.builder().appModule(new AppModule()).build().inject(this); //注入
        initView();
        initTitleLayout(getString(R.string.diagnosis));
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_diagnosis_picture;
    }

    @Override
    protected void initView() {
        viewModel = new DiagnosisPictureViewModel(this);
        setTitleRightVisiable(getString(R.string.apply_diagnosis), new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //申请会诊
                Intent intent = new Intent(mContext, ApplyDiagnosisActivity01.class);
                startActivity(intent);
            }
        });
        initRecycler();
    }

    private void initRecycler() {
        int i = 10;
        List<Object> mList = new ArrayList<>();
      /*  while (i-- > 0) {
            mList.add(i);
        }*/
        mAdapter = new CommonAdapter(this, R.layout.diagnosis_item, mList) {
            @Override
            protected void convert(ViewHolder viewHolder, Object item, int position) {
                final MyLaunchListBean.DataBean.ListBean entity = (MyLaunchListBean.DataBean.ListBean) item;
                TextView tv_name = viewHolder.getView(R.id.tv_name);
                TextView tv_status = viewHolder.getView(R.id.tv_status);
                TextView tv_content = viewHolder.getView(R.id.tv_content);
                TextView tv_date = viewHolder.getView(R.id.tv_date);

                tv_name.setText(entity.getPatientName());
                tv_status.setText(entity.getType() + "");
                tv_content.setText(entity.getContent());
                tv_date.setText(entity.getReferralPlanDate());
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
                switch (currentCheckPage){
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
    }

    @Override
    protected void onResume() {
        super.onResume();
//        viewModel.getMyLaunchList(mContext, 0);
//        eventBus()refresh()//// TODO: 2017/10/19 0019
    }

    public void getMyLaunchListOK(MyLaunchListBean response, int pageNum) {
        refreshView(response, pageNum);
    }

    @Override
    public void getMyParticipateListOK(MyLaunchListBean response, int pageNum) {
        refreshView(response, pageNum);
    }

    private void refreshView(MyLaunchListBean response, int pageNum) {
        binding.swipeRecyclerView.stopLoadingMore();
        binding.swipeRecyclerView.complete();
        if (pageNum == 1) {
            mAdapter.setDatas(response.getData().getList());
        } else {
            mAdapter.addDatas(response.getData().getList());
            if (response.getData().getList().size() == 0) {
                binding.swipeRecyclerView.setEmptyView(View.inflate(this, R.layout.list_empty_view, null));
            }
            if (response.getData().getList().size() < pageSize) {
                binding.swipeRecyclerView.onNoMore(getString(R.string.recyclerview_nomore));
            }
        }
    }
}

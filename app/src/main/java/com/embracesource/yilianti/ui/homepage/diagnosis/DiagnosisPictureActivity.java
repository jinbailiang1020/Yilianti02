package com.embracesource.yilianti.ui.homepage.diagnosis;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RadioGroup;

import com.embracesource.yilianti.R;
import com.embracesource.yilianti.bean.MyLaunchListBean;
import com.embracesource.yilianti.common.adapter.CommonAdapter;
import com.embracesource.yilianti.common.adapter.MultiItemTypeAdapter;
import com.embracesource.yilianti.common.adapter.ViewHolder;
import com.embracesource.yilianti.common.memory.MyPrefrences;
import com.embracesource.yilianti.databinding.ActivityDiagnosisPictureBinding;
import com.embracesource.yilianti.ui.base.AacBaseActivity;
import com.embracesource.yilianti.viewmodel.ApplyDiagnosisDetailCallBack;
import com.embracesource.yilianti.viewmodel.DiagnosisPictureCallBack;

import java.util.ArrayList;
import java.util.List;

public class DiagnosisPictureActivity extends AacBaseActivity<ActivityDiagnosisPictureBinding> implements DiagnosisPictureCallBack {

//    @Inject
    DiagnosisPictureViewModel viewModel;
    private CommonAdapter mAdapter;

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
        binding.radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                switch (checkedId) {
                    case R.id.rb_my_launch:
                        viewModel.getMyLaunchList(mContext,0,myPrefrences.getString(MyPrefrences.Key.sessionid));
                        break;
                    case R.id.rb_my_participate:
//                        requestData02();
                        break;
                }
            }
        });
        binding.rbMyLaunch.setChecked(true);

        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        int i = 10;
        List<Object> mList = new ArrayList<>();
        while (i-- > 0) {
            mList.add(i);
        }
        mAdapter = new CommonAdapter(this, R.layout.diagnosis_item, mList) {
            @Override
            protected void convert(ViewHolder viewHolder, Object item, int position) {

            }
        };
        mAdapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
                //// TODO: 2017/10/17 0017 test 
                new ApplyDiagnosisDetailViewModel(new ApplyDiagnosisDetailCallBack(){

                }).getDetail("65","2");
            }

            @Override
            public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
                return false;
            }
        });
        binding.recyclerView.setAdapter(mAdapter);
        binding.swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                //
                binding.swipeRefreshLayout.setRefreshing(false);
            }
        });
        setTitleRightVisiable(getString(R.string.apply_diagnosis), new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //申请会诊
                Intent intent = new Intent(mContext, ApplyDiagnosisActivity01.class);
                startActivity(intent);
            }
        });
    }

    public void getMyLaunchListOK(MyLaunchListBean response) {
        mAdapter.setDatas(response.getData().getList());//test // TODO: 2017/10/17 0017
    }
}

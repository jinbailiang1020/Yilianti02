package com.embracesource.yilianti.ui.homepage.diagnosis;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.RadioGroup;

import com.embracesource.yilianti.R;
import com.embracesource.yilianti.databinding.ActivityDiagnosisPictureBinding;
import com.embracesource.yilianti.ui.base.AacBaseActivity;
import com.embracesource.yilianti.ui.base.common.adapter.CommonAdapter;
import com.embracesource.yilianti.ui.base.common.adapter.ViewHolder;

import java.util.ArrayList;
import java.util.List;

public class DiagnosisPictureActivity extends AacBaseActivity<ActivityDiagnosisPictureBinding> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initTitleLayout(getString(R.string.diagnosis));
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_diagnosis_picture;
    }

    @Override
    protected void initView() {
        binding.radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                switch (checkedId){
                    case R.id.rb_my_launch:
//                        requestData01();
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
            mList.add("s"+i);
        }
        CommonAdapter mAdapter = new CommonAdapter(this, R.layout.diagnosis_item, mList) {
            @Override
            protected void convert(ViewHolder viewHolder, Object item, int position) {

            }
        };
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
                Intent intent = new Intent(mContext,ApplyDiagnosisActivity.class);
                startActivity(intent);
            }
        });
    }
}

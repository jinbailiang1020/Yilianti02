package com.embracesource.yilianti.ui.homepage.diagnosis;

import android.os.Bundle;

import com.embracesource.yilianti.R;
import com.embracesource.yilianti.databinding.ActivityApplyDiagnosisDetailBinding;
import com.embracesource.yilianti.ui.base.AacBaseActivity;
import com.embracesource.yilianti.viewmodel.ApplyDiagnosisDetailCallBack;

public class ApplyDiagnosisDetailActivity extends AacBaseActivity<ActivityApplyDiagnosisDetailBinding> implements ApplyDiagnosisDetailCallBack {
    private ApplyDiagnosisDetailViewModel viewModel;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_apply_diagnosis_detail;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();
        viewModel = new ApplyDiagnosisDetailViewModel(this);
    }

    @Override
    protected void initData() {
        super.initData();
        String id = getIntent().getStringExtra(Key_ID);
        viewModel.getDetail(id,"0");//// TODO: 2017/10/17 0017  flag
    }
}

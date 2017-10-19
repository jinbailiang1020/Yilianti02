package com.embracesource.yilianti.ui.homepage.diagnosis;

import android.os.Bundle;

import com.embracesource.yilianti.R;
import com.embracesource.yilianti.bean.ApplyDiagnosisDetailBean;
import com.embracesource.yilianti.databinding.ActivityApplyDiagnosisDetailBinding;
import com.embracesource.yilianti.ui.base.AacBaseActivity;
import com.embracesource.yilianti.viewmodel.ApplyDiagnosisDetailCallBack;

//                        转诊会诊详情
//                        http://192.168.1.165:8002/referralAndConsultation/detail/{id}?flag={flag}
//                        flag：1 医务处审核，2 详情展示，3 专家回复

public class ApplyDiagnosisDetailActivity extends AacBaseActivity<ActivityApplyDiagnosisDetailBinding> implements ApplyDiagnosisDetailCallBack {
    private ApplyDiagnosisDetailViewModel viewModel;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_apply_diagnosis_detail;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initTitleLayout("会诊详情");
        initData();
    }

    @Override
    protected void initData() {
        super.initData();
        viewModel = new ApplyDiagnosisDetailViewModel(this);
        int id = getIntent().getIntExtra(ApplyDiagnosisDetailActivity.class.getName(),0);
        showDialog();
        viewModel.getDetail(id, "2");
    }

    @Override
    public void getApplyDiagnosisDetailOK(ApplyDiagnosisDetailBean applyDiagnosisDetailBean) {
        try {
            binding.setBean(applyDiagnosisDetailBean);
            binding.setItemBean(applyDiagnosisDetailBean.getData().getList().get(0));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}

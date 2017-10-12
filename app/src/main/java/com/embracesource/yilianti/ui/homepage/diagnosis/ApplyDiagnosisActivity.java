package com.embracesource.yilianti.ui.homepage.diagnosis;

import android.os.Bundle;
import android.view.View;

import com.embracesource.yilianti.R;
import com.embracesource.yilianti.databinding.ActivityApplyDiagnosisBinding;
import com.embracesource.yilianti.ui.base.AacBaseActivity;

public class ApplyDiagnosisActivity extends AacBaseActivity<ActivityApplyDiagnosisBinding>{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initTitleLayout(getString(R.string.diagnosis_detail));
        setTitleRightVisiable(getString(R.string.submit), new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_apply_diagnosis;
    }
}

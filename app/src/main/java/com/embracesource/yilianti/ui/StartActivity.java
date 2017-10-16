package com.embracesource.yilianti.ui;

import android.content.Intent;
import android.os.Bundle;

import com.embracesource.yilianti.R;
import com.embracesource.yilianti.databinding.ActivityStartBinding;
import com.embracesource.yilianti.ui.base.AacBaseActivity;
import com.embracesource.yilianti.ui.loginregister.LoginActivity;

public class StartActivity extends AacBaseActivity<ActivityStartBinding> {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_start;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        init();
        gotoNext();
    }

/*    private void init() {
        StartViewModel viewModel = ViewModelProviders.of(this, viewModelFactory()).get(StartViewModel.class);
        viewModel.getStartInfo().observe(this, new Observer<GetStartInfoResponse>() {
            @Override
            public void onChanged(@Nullable GetStartInfoResponse response) {
                if (response != null) {
                    binding.setInfo(response);
                    binding.executePendingBindings();
                }
            }
        });
    }*/

    private void gotoNext() {
        uiHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(getContext(), LoginActivity.class));
                finish();
            }
        }, 1000);
    }

    @Override
    public void onBackPressed() {
        // super.onBackPressed();
    }
}
package com.embracesource.yilianti.common;

import android.databinding.ViewDataBinding;
import android.os.Bundle;

import com.embracesource.yilianti.R;
import com.embracesource.yilianti.ui.base.AacBaseActivity;

public class CommonActivity<T extends ViewDataBinding> extends AacBaseActivity<T> {

    private ActivityDelegate<T, CommonActivity<T>, CommonFragment> delegate;

    protected void onCreate(Bundle savedInstanceState) {
        if (delegate == null) {
            delegate = getActivityDelegate();
        }

        delegate.beforeOnCreate(savedInstanceState);
        super.onCreate(savedInstanceState);
        delegate.afterOnCreate(savedInstanceState);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    protected ActivityDelegate<T, CommonActivity<T>, CommonFragment> getActivityDelegate() {
        return new ActivityDelegate<>(this);
    }

    protected final CommonExtraParam getExtraParam() {
        return delegate.getExtraParam();
    }

    protected CommonFragment getCommonFragment() {
        return delegate.getCommonFragment();
    }

    public void onBackPressed() {
        if (getCommonFragment() != null) {
            if (!getCommonFragment().onActivityPressBack()) {
                super.onBackPressed();
            }
        } else {
            super.onBackPressed();
        }
    }

    public boolean onSupportNavigateUp() {
        return getCommonFragment() != null
                && getCommonFragment().onActivitySupportNavigateUp()
                || super.onSupportNavigateUp();
    }
}
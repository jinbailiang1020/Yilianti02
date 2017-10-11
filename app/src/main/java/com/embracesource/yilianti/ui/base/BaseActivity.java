package com.embracesource.yilianti.ui.base;

import android.arch.lifecycle.ViewModelProvider;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import com.embracesource.yilianti.R;
import com.embracesource.yilianti.app.AppContext;
import com.embracesource.yilianti.util.StatusBarCompat;

public abstract class BaseActivity extends AppCompatActivity implements ILoadDataView {

    static final String LOADING_DIALOG_TAG = "loading_dialog";

    private DialogFragment loadingDialogFragment;
    protected Handler uiHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusBarCompat.compat(this,getResources().getColor(R.color.main_color));
        initContentView();

        initActionBar();
        uiHandler = new Handler(getMainLooper());
    }

    protected void initContentView() {
        setContentView(getLayoutId());
    }

    protected abstract int getLayoutId();

    // ***************************************** ActionBar *****************************
    private void initActionBar() {
        if (getSupportActionBar() != null) {
            int options = ActionBar.DISPLAY_HOME_AS_UP | ActionBar.DISPLAY_USE_LOGO | ActionBar.DISPLAY_SHOW_TITLE;
            getSupportActionBar().setDisplayOptions(options);
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }

    public void setTitle(int titleId) {
        setTitle(getString(titleId));
    }

    public void setTitle(CharSequence title) {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(title);
        }
    }

    // ***************************************** ILoadDataView *****************************

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void showLoading() {
        showLoading(LOADING_TYPE_DEFAULT);
    }

    @Override
    public final void showLoading(int loadingType) {
        switch (loadingType) {
            case LOADING_TYPE_DEFAULT:
            default:
                showDefaultStyleLoadingDialog(null);
                break;
        }
    }

    // 显示用默认样式的Loading对话框
    private void showDefaultStyleLoadingDialog(String loadingTitle) {
        hideDefaultStyleLoadingDialog();

        DialogFragment newFragment = LoadingDialogFragment.newInstance(loadingTitle);
        newFragment.show(getSupportFragmentManager(), LOADING_DIALOG_TAG);
        loadingDialogFragment = newFragment;
    }

    @Override
    public void hideLoading() {
        hideLoading(LOADING_TYPE_DEFAULT);
    }

    @Override
    public final void hideLoading(int loadingType) {
        switch (loadingType) {
            case LOADING_TYPE_DEFAULT:
            default:
                hideDefaultStyleLoadingDialog();
                break;
        }
    }

    //  隐藏默认样式的loading对话框
    private void hideDefaultStyleLoadingDialog() {
        if (loadingDialogFragment != null) {
            loadingDialogFragment.dismiss();
            loadingDialogFragment = null;
        }
    }

    @Override
    public void showError(String message) {
        Toast.makeText(BaseActivity.this, message, Toast.LENGTH_SHORT).show();
    }

    protected final ViewModelProvider.Factory viewModelFactory() {
        return AppContext.getInstance().getViewModelFactory();
    }


    /**
     *
     * @param titleName 标题
     * @param rightName 右上角按钮名称
     * @return 标题view
     */
    public List<View> initTitleLayout(String titleName, String rightName) {
        TextView left = (TextView) findViewById(R.id.left);
        TextView title = (TextView) findViewById(R.id.title);
        TextView right = (TextView) findViewById(R.id.right);
        left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BaseActivity.this.finish();
            }
        });

        title.setText(titleName);

        if (rightName != null) {
            right.setText(rightName);
        }
        List<View> viewList = new ArrayList<>();
        viewList.add(left);
        viewList.add(title);
        viewList.add(right);
        return viewList;
    }
    public void setTitleLeftViewGone(){
        findViewById(R.id.left).setVisibility(View.GONE);
    }
}
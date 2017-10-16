package com.embracesource.yilianti.ui.base;

import android.app.Activity;
import android.app.ProgressDialog;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import com.embracesource.yilianti.R;
import com.embracesource.yilianti.app.AppContext;
import com.embracesource.yilianti.common.permission.PermissionListener;
import com.embracesource.yilianti.util.StatusBarCompat;
import com.embracesource.yilianti.viewmodel.BaseViewModelCallBack;

import io.reactivex.Observer;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public abstract class BaseActivity extends AppCompatActivity implements ILoadDataView ,BaseViewModelCallBack{

    static final String LOADING_DIALOG_TAG = "loading_dialog";
    private static PermissionListener mListener;
    protected static Context mContext;
    private static Activity activity;
    protected static Disposable mDisposable;

    private DialogFragment loadingDialogFragment;
    protected Handler uiHandler;
    public static final int REQUEST_CODE = 1;
    public static ProgressDialog dialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dialog = new ProgressDialog(this);
        StatusBarCompat.compat(this, getResources().getColor(R.color.main_color));
        mContext = this.getApplicationContext();
        activity = this;
        initContentView();

        initActionBar();
        uiHandler = new Handler(getMainLooper());
    }

    protected void initView() {
    }

    ;

    protected void initData() {
    }

    ;

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

    public static void showToast(String message) {
        Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
    }

    protected final ViewModelProvider.Factory viewModelFactory() {
        return AppContext.getInstance().getViewModelFactory();
    }


    /**
     * @param titleName 标题
     * @return 标题view
     */
    public List<View> initTitleLayout(String titleName) {
        TextView left = (TextView) findViewById(R.id.left);
        TextView title = (TextView) findViewById(R.id.title);
//        TextView right = (TextView) findViewById(R.id.right);
        left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BaseActivity.this.finish();
            }
        });

        title.setText(titleName);

        List<View> viewList = new ArrayList<>();
        viewList.add(left);
        viewList.add(title);
//        viewList.add(right);
        return viewList;
    }

    public void setTitleLeftViewGone() {
        findViewById(R.id.left).setVisibility(View.GONE);
    }

    public void setTitleRightVisiable(String rightName, View.OnClickListener listener) {
        TextView right = (TextView) findViewById(R.id.right);
        right.setVisibility(View.VISIBLE);
        right.setText(rightName);
        right.setOnClickListener(listener);
    }

    public static void requestRuntimePermission(String[] permissions, PermissionListener listener) {
        mListener = listener;
        // 需要请求的权限列表
        List<String> requestPermisssionList = new ArrayList<>();
        // 检查权限  是否已被授权
        for (String permission : permissions) {
            if (ActivityCompat.checkSelfPermission(mContext, permission)
                    != PackageManager.PERMISSION_GRANTED)
                // 未授权时添加该权限
                requestPermisssionList.add(permission);
        }

        if (requestPermisssionList.isEmpty())
            // 所有权限已经被授权过 回调 Listener onGranted 方法 已授权
            listener.onGranted();
        else
            // 进行请求权限操作
            ActivityCompat.requestPermissions(activity,
                    requestPermisssionList.toArray(new String[requestPermisssionList.size()]),
                    REQUEST_CODE);

    }

    // 请求权限的回调
    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode) {
            case REQUEST_CODE: {

                List<String> deniedPermissionList = new ArrayList<>();
                // 检查返回授权结果不为空
                if (grantResults.length > 0) {
                    // 判断授权结果
                    for (int i = 0; i < grantResults.length; i++) {
                        int result = grantResults[i];
                        if (result != PackageManager.PERMISSION_GRANTED)
                            // 保存被用户拒绝的权限
                            deniedPermissionList.add(permissions[i]);
                    }
                    if (deniedPermissionList.isEmpty())
                        // 都被授权  回调 Listener onGranted 方法 已授权
                        mListener.onGranted();
                    else
                        // 有权限被拒绝 回调 Listner onDeynied 方法
                        mListener.onDenied(deniedPermissionList);
                }
                break;
            }
            default:
                break;
        }
    }

    public abstract static   class MyObserver<T> implements Observer<T> {
        private  final String TAG = BaseActivity.class.getName();

        @Override
        public void onSubscribe(@io.reactivex.annotations.NonNull Disposable d) {
            mDisposable = d;
        }
        @Override
        public void  onError(Throwable e){
            dialog.dismiss();
            showToast(e.getMessage());
            Log.i(TAG, "onError: "+e.getMessage());
        }

        @Override
        public void onNext(@io.reactivex.annotations.NonNull T t) {
            dialog.dismiss();
            Log.i(TAG, "onError: "+t.toString());//BaseBean{code=0, message='请输入用户名', success=false}
        }


        @Override
        public void onComplete() {
            dialog.dismiss();
        }


    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mDisposable != null) mDisposable.dispose();
    }
}
package com.embracesource.yilianti.ui.base;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;

import com.embracesource.yilianti.R;
import com.embracesource.yilianti.common.memory.MyPrefrences;
import com.orhanobut.logger.Logger;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

import static com.embracesource.yilianti.ui.base.BaseActivity.dialog;
import static com.embracesource.yilianti.ui.base.BaseActivity.showToast;

public abstract class BaseFragment extends Fragment {
    public MyPrefrences myPrefrences;
    protected static Context mContext;
    protected static Disposable mDisposable;
    private static BaseFragment fragment;
    public static  BaseActivity activity;

    @Override
    public Animation onCreateAnimation(int transit, boolean enter, int nextAnim) {
        return super.onCreateAnimation(transit, enter, nextAnim);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        fragment = this;
        activity = (BaseActivity) fragment.getActivity();
        mContext = getContext().getApplicationContext();
        myPrefrences = new MyPrefrences(mContext);
        int layoutRes = getFragmentLayout();
        return inflater.inflate(layoutRes, container, false);
    }

    public BaseActivity getBaseActivityInstance(){
        if(activity == null){
            activity = (BaseActivity)getActivity();
        }
        return activity;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    protected ActionBar getSupportActionBar() {
        return ((AppCompatActivity) getActivity()).getSupportActionBar();
    }

    protected void setTitle(int resId) {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle(resId);
        }
    }

    protected void setTitle(String title) {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle(title);
        }
    }

    /**
     * 每个Fragment自己的布局
     */
    protected abstract int getFragmentLayout();

/*    protected ViewModelProvider.Factory viewModelFactory() {
        return AppContext.getInstance().getViewModelFactory();
    }*/




    public abstract static class MyObserver<T> implements Observer<T> {
        public final String TAG = BaseFragment.class.getName();

        @Override
        public void onSubscribe(@io.reactivex.annotations.NonNull Disposable d) {
            mDisposable = d;
        }

        @Override
        public void onError(final Throwable e) {
            if (dialog != null) dialog.dismiss();
            Logger.e(TAG, "onError: " + e.getMessage());
            activity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    onErrorUI(e);
                }
            });
        }


        @Override
        public void onNext(@io.reactivex.annotations.NonNull final T t) {
            if (dialog != null) dialog.dismiss();
            if(t ==null){
                showToast(activity.getString(R.string.msg_data_error));
                return;
            }
            Logger.d(TAG, "onNext: " + t.toString());//BaseBean{code=0, message='请输入用户名', success=false}
            activity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    onNextUI(t);
                }
            });
        }

        protected abstract void onNextUI(T t);

        @Override
        public void onComplete() {
            if (dialog != null) dialog.dismiss();
        }

        public void onErrorUI(Throwable e) {
            showToast(e.getMessage());

        }

        ;

    }

}
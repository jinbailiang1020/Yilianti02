package com.embracesource.yilianti.ui.loginregister;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.embracesource.yilianti.bean.LoginBean;
import com.embracesource.yilianti.bean.UserTypeBean;
import com.embracesource.yilianti.common.http.Api;
import com.embracesource.yilianti.ui.base.BaseActivity;
import com.embracesource.yilianti.viewmodel.LoginViewModelCallBack;

import io.reactivex.annotations.NonNull;

/**
 * OtherThemeViewModel <br/>
 * Created by xiaqiulei on 2017-05-26.
 */
public class LoginViewModel extends ViewModel {


    private final LoginViewModelCallBack callBack;
    public Api api;
    private final MutableLiveData<Object> themeResponse = new MutableLiveData<>();

//    @Inject
    LoginViewModel(LoginViewModelCallBack callBack) {
        this.callBack = callBack;
        api = new Api();
    }


    public void login(final String name , final String pwd) {
        api.login( name , pwd).subscribe(new BaseActivity.MyObserver<LoginBean>() {
            @Override
            public void onNext(@NonNull LoginBean response) {
                super.onNext(response);
                callBack.loginOK(response,name,pwd);
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
                callBack.loginOK(null, name, pwd);
            }
        });
    }

    public void selectUserRole() {
        api.selectUserRole().subscribe(new BaseActivity.MyObserver<UserTypeBean>() {
            @Override
            public void onNext(@NonNull UserTypeBean response) {
                super.onNext(response);
                callBack.selectUserRoleOK(response);
            }
        });
    }

/*
    //为了方便改写，来实现复杂布局的切换
    private class LocalImageHolderView implements Holder<Integer> {
        private ImageView imageView;

        @Override
        public View createView(Context context) {
            //你可以通过layout文件来创建，不一定是Image，任何控件都可以进行翻页
            imageView = new ImageView(context);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            return imageView;
        }

        @Override
        public void UpdateUI(Context context, int position, Integer data) {
            imageView.setImageResource(data);
        }
    }

    public MutableLiveData<Object> clickBannerCallBack(int id) {
       new MutableLiveData<Object>().observeForever(new SimpleObserver<>(themeResponse));
//        yiliantiApi.getThemeResponse(new GetThemeRequest(id)).observeForever(new SimpleObserver<>(themeResponse));
        return themeResponse;
    }*/
}
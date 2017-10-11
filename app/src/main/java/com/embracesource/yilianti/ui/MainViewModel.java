package com.embracesource.yilianti.ui;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import javax.inject.Inject;

import com.embracesource.yilianti.biz.api.ZhihuApi;
import com.embracesource.yilianti.biz.pojo.request.ext.GetAllThemesRequest;
import com.embracesource.yilianti.util.SimpleObserver;

/**
 * MainViewModel <br/>
 * Created by xiaqiulei on 2017-05-25.
 */
public class MainViewModel extends ViewModel {

    private ZhihuApi zhihuApi;
//    private final MutableLiveData<GetAllThemesResponse> allThemeResponse = new MutableLiveData<>();

    @Inject
    MainViewModel(ZhihuApi zhihuApi) {
        this.zhihuApi = zhihuApi;
    }

/*    LiveData<GetAllThemesResponse> loadAllTheme() {
        zhihuApi.getAllThemesResponse(new GetAllThemesRequest())
                .observeForever(new SimpleObserver<>(allThemeResponse));
        return allThemeResponse;
    }*/
}
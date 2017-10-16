package com.embracesource.yilianti.ui;

import android.arch.lifecycle.ViewModel;

import com.embracesource.yilianti.common.http.Api;

import javax.inject.Inject;

/**
 * MainViewModel <br/>
 * Created by xiaqiulei on 2017-05-25.
 */
public class MainViewModel extends ViewModel {

    private Api zhihuApi;
//    private final MutableLiveData<GetAllThemesResponse> allThemeResponse = new MutableLiveData<>();

    @Inject
    MainViewModel(Api zhihuApi) {
        this.zhihuApi = zhihuApi;
    }

/*    LiveData<GetAllThemesResponse> loadAllTheme() {
        zhihuApi.getAllThemesResponse(new GetAllThemesRequest())
                .observeForever(new SimpleObserver<>(allThemeResponse));
        return allThemeResponse;
    }*/
}
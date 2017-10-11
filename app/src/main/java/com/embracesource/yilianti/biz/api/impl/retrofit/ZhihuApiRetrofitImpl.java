package com.embracesource.yilianti.biz.api.impl.retrofit;


import android.arch.lifecycle.LiveData;

import com.embracesource.yilianti.biz.api.ZhihuApi;
import com.embracesource.yilianti.biz.pojo.request.ext.GetAllThemesRequest;
import com.embracesource.yilianti.biz.pojo.request.ext.GetLastThemeRequest;
import com.embracesource.yilianti.biz.pojo.request.ext.GetLongCommentsRequest;
import com.embracesource.yilianti.biz.pojo.request.ext.GetNewsRequest;
import com.embracesource.yilianti.biz.pojo.request.ext.GetShortCommentsRequest;
import com.embracesource.yilianti.biz.pojo.request.ext.GetStartInfoRequest;
import com.embracesource.yilianti.biz.pojo.request.ext.GetStoryExtraRequest;
import com.embracesource.yilianti.biz.pojo.request.ext.GetThemeRequest;
import com.embracesource.yilianti.biz.pojo.response.ext.GetThemeResponse;
import com.embracesource.yilianti.core.log.AppLog;

/**
 * ZhihuApiRetrofitImpl <br/>
 * Created by xiaqiulei on 2016-01-10.
 */
public final class ZhihuApiRetrofitImpl implements ZhihuApi {

    private final IZhihuRetrofitApi httpApi;

    public ZhihuApiRetrofitImpl(IZhihuRetrofitApi httpApi) {
        this.httpApi = httpApi;
    }

/*    @Override
    public LiveData<GetStartInfoResponse> getStartInfoResponse(GetStartInfoRequest request) {
        AppLog.i("ZhihuApiRetrofitImpl.getStartInfoResponse request = " + request);

        return httpApi.getStartInfoResponse(request.width, request.height);
    }

    @Override
    public LiveData<GetAllThemesResponse> getAllThemesResponse(GetAllThemesRequest request) {
        AppLog.i("ZhihuApiRetrofitImpl.getAllThemesResponse request = " + request);
        return httpApi.getAllThemesResponse();
    }

    @Override
    public LiveData<GetLastThemeResponse> getLastThemeResponse(GetLastThemeRequest request) {
        AppLog.i("ZhihuApiRetrofitImpl.getLastThemeResponse request = " + request);

        return httpApi.getLastThemeResponse();
    }

    @Override
    public LiveData<GetNewsResponse> getNewsResponse(GetNewsRequest request) {
        AppLog.i("ZhihuApiRetrofitImpl.getNewsResponse request = " + request);

        return httpApi.getNewsResponse(request.id);
    }

    @Override
    public LiveData<GetThemeResponse> getThemeResponse(GetThemeRequest request) {
        AppLog.i("ZhihuApiRetrofitImpl.getThemeResponse request = " + request);

        return httpApi.getThemeResponse(request.id);
    }

    @Override
    public LiveData<GetStoryExtraResponse> getStoryExtraResponse(GetStoryExtraRequest request) {
        AppLog.i("ZhihuApiRetrofitImpl.getStoryExtraResponse request = " + request);

        return httpApi.getStoryExtraResponse(request.id);
    }

    @Override
    public LiveData<GetShortCommentsResponse> getShortComments(GetShortCommentsRequest request) {
        AppLog.i("ZhihuApiRetrofitImpl.getShortComments request = " + request);

        return httpApi.getShortComments(request.id);
    }

    @Override
    public LiveData<GetLongCommentsResponse> getLongComments(GetLongCommentsRequest request) {
        AppLog.i("ZhihuApiRetrofitImpl.GetStartInfoResponse request = " + request);

        return httpApi.getLongComments(request.id);
    }*/
}
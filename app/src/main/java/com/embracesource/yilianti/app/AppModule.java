package com.embracesource.yilianti.app;

import android.arch.lifecycle.ViewModelProvider;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.inject.Singleton;

import com.embracesource.yilianti.biz.api.ZhihuApi;
import com.embracesource.yilianti.biz.api.impl.OkHttpUtil;
import com.embracesource.yilianti.biz.api.impl.okhttp.ZhihuApiOkHttpImpl;
import com.embracesource.yilianti.biz.api.impl.retrofit.IZhihuRetrofitApi;
import com.embracesource.yilianti.biz.api.impl.retrofit.RetrofitUtil;
import com.embracesource.yilianti.biz.api.impl.retrofit.ZhihuApiRetrofitImpl;
import com.embracesource.yilianti.viewmodel.ViewModelFactory;
import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;

/**
 * AppModule <br/>
 * Created by xiaqiulei on 2016-06-29.
 */

@Module(subcomponents = ViewModelSubComponent.class)
class AppModule {

    // @Provides
    ZhihuApi providerZhihuApi(IZhihuRetrofitApi zhihuRetrofitApi) {
        return new ZhihuApiRetrofitImpl(zhihuRetrofitApi);
    }

    @Provides
    ZhihuApi providerZhihuApi(OkHttpClient client) {
        return new ZhihuApiOkHttpImpl(client);
    }

    @Provides
    @Singleton
    OkHttpClient providerOkHttpClient() {
        return OkHttpUtil.newOkHttpClient();
    }

    @Provides
    IZhihuRetrofitApi providerIZhihuRetrofitApi(OkHttpClient client, Gson gson) {
        return RetrofitUtil.createApi(IZhihuRetrofitApi.class, client, gson);
    }

    @Provides
    @Singleton
    Gson providerGson() {
        return new GsonBuilder().registerTypeAdapterFactory(AdapterFactory.create()).create();
    }

    @Singleton
    @Provides
    ViewModelProvider.Factory provideViewModelFactory(ViewModelSubComponent.Builder builder) {
        return new ViewModelFactory(builder.build());
    }
}
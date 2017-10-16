package com.embracesource.yilianti.common.http;


import io.reactivex.Observable;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Administrator on 2017/10/16 0016.
 */

public class RetrofitConfig  {
    private static RetrofitService retrofitService;
    private static OkHttpClient okHttpClient = new OkHttpClient();

    public static RetrofitService getInstance() {
        if (retrofitService == null) {
            synchronized (RetrofitConfig.class){
                if(retrofitService == null){
                    Retrofit retrofit = new Retrofit.Builder()
                            .client(okHttpClient)
                            .baseUrl("http://192.168.1.165:8002/")//165 //118
                            .addConverterFactory(GsonConverterFactory.create())
                            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                            .build();
                    retrofitService = retrofit.create(RetrofitService.class);

                }
            }

        }
        return retrofitService;
    }

}

package com.embracesource.yilianti.common.http;


import android.content.Context;
import android.util.Log;

import com.embracesource.yilianti.common.memory.MyPrefrences;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.content.ContentValues.TAG;

/**
 * Created by Administrator on 2017/10/16 0016.
 */

public class RetrofitConfig {
    private static  RetrofitService retrofitService;

    public static void setJsessionid(String jsessionid, Context context) {
        if (jsessionid == null && jsessionid.isEmpty() && jsessionid.equals("null")) {
            MyPrefrences myPrefrences = new MyPrefrences(context);
            RetrofitConfig.jsessionid = myPrefrences.getString(MyPrefrences.Key.sessionid);
        }
        RetrofitConfig.jsessionid = jsessionid;
        Log.i(TAG,RetrofitConfig.jsessionid);
    }

    private static String jsessionid;
    private static RetrofitService retrofitService_afterLogin;
    private static OkHttpClient okHttpClient = new OkHttpClient();

    public static RetrofitService getInstance() {
        if (retrofitService == null) {
            synchronized (RetrofitConfig.class) {
                if (retrofitService == null) {
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

    public static RetrofitService getInstance_afterLogin() {
        if (retrofitService_afterLogin == null) {
            synchronized (RetrofitConfig.class) {
                if (retrofitService_afterLogin == null) {
                    Retrofit retrofit = new Retrofit.Builder()
                            .client(genericClient(jsessionid))
                            .baseUrl("http://192.168.1.165:8002/")//165 //118
                            .addConverterFactory(GsonConverterFactory.create())
                            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                            .build();
                    retrofitService_afterLogin = retrofit.create(RetrofitService.class);

                }
            }

        }
        return retrofitService_afterLogin;
    }

    private static OkHttpClient genericClient(final String jsessionid) {
        OkHttpClient httpClient = new OkHttpClient.Builder()
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request request = chain.request()
                                .newBuilder()
                                .addHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8")
                                .addHeader("Accept-Encoding", "gzip, deflate")
                                .addHeader("Connection", "keep-alive")
                                .addHeader("Accept", "*/*")
                                .addHeader("Cookie", "add cookies here")
                                .addHeader("jsessionid", jsessionid)
                                .build();
                        return chain.proceed(request);//jsessionid:D0924D8BD4C842AEAE4A25C320391169
                    }

                })
                .build();

        return httpClient;
    }

}
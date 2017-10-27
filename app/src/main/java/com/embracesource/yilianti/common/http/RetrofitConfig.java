package com.embracesource.yilianti.common.http;


import android.content.Context;
import android.util.Log;

import com.embracesource.yilianti.common.memory.MyPrefrences;
import com.orhanobut.logger.Logger;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.content.ContentValues.TAG;

/**
 * Created by Administrator on 2017/10/16 0016.
 */

public class RetrofitConfig {
    public static final String BASE_URL = "http://122.207.81.240:9002/";         //外网
    //    public static final String BASE_URL = "http://192.168.1.165:8002/";//内网
    private static RetrofitService retrofitService;

    public static void setJsessionid(String jsessionid, Context context) {
        if (jsessionid == null && jsessionid.isEmpty() && jsessionid.equals("null")) {
            MyPrefrences myPrefrences = new MyPrefrences(context);
            RetrofitConfig.jsessionid = myPrefrences.getString(MyPrefrences.Key.sessionid);
        }
        RetrofitConfig.jsessionid = jsessionid;
        retrofitService_afterLogin = null;
        Log.i(TAG, RetrofitConfig.jsessionid);
    }

    public static String jsessionid;
    private static RetrofitService retrofitService_afterLogin;
    private static OkHttpClient okHttpClient = new OkHttpClient();

    public static RetrofitService getInstance() {
        if (retrofitService == null) {
            synchronized (RetrofitConfig.class) {
                if (retrofitService == null) {
                    Retrofit retrofit = new Retrofit.Builder()
                            .client(okHttpClient)
                            .baseUrl(BASE_URL)//165 //118
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
                            .baseUrl(BASE_URL)//165 //118
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
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {

            @Override
            public void log(String message) {
                Log.e("=====log======", message);
            }
        }).setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient httpClient = new OkHttpClient.Builder()
                .addInterceptor(new Interceptor() {

                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request request = chain.request()
                                .newBuilder()
//                                .addHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8")
//                                .addHeader("Accept-Encoding", "gzip, deflate")
//                                .addHeader("Connection", "keep-alive")
//                                .addHeader("Accept", "*/*")
//                                .addHeader("Cookie", "add cookies here")
                                .addHeader("jsessionid", jsessionid)
                                .build();
                        Logger.i("okhttp:method " + request.method().toString() + "\n okhttp: url-->  " + request.url().toString() + "\n okhttp: header--> " + request.headers().toString());
                        return chain.proceed(request);//jsessionid:D0924D8BD4C842AEAE4A25C320391169
                    }

                })
                .build();
        return httpClient;
    }



/*    public static  void ooooo(){
        OkHttpClient httpClient = new OkHttpClient();
        httpClient.interceptors().add(chain -> {

            Request request = chain.request();
            Request.Builder requestBuilder = request.newBuilder()
                    .addHeader("header-key", "value1")
                    .addHeader("header-key", "value2");

            Request request = requestBuilder.build();
            return chain.proceed(request);
        });
        return httpClient;

    }*/

}

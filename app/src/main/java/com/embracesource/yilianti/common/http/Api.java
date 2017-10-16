package com.embracesource.yilianti.common.http;

import android.util.Log;

import com.embracesource.yilianti.bean.ApplyDiagnosisGoalBean;
import com.embracesource.yilianti.bean.LoginBean;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import okhttp3.RequestBody;
import retrofit2.Response;

/**
 * Created by Administrator on 2017/10/16 0016.
 */

public class Api implements ApiInterface {
    @Inject
    public Api() {
    }

    @Override
    public Observable<LoginBean> login(String userName,String pwd) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("username",userName);
            jsonObject.put("pwd",pwd);
        } catch (JSONException e) {
            e.printStackTrace();
        }
/*        return RetrofitConfig.getInstance().login1("7")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());*/
//        String route= new Gson().toJson(jsonObject.toString());//通过Gson将Bean转化为Json字符串形式

//        RequestBody body=RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),route);

        return RetrofitConfig.getInstance().login(jsonObject.toString())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<ApplyDiagnosisGoalBean> getBaseData(String code) {
        return RetrofitConfig.getInstance().getBaseData(code)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).map(new Function<Response<ApplyDiagnosisGoalBean>, ApplyDiagnosisGoalBean>() {
                    @Override
                    public ApplyDiagnosisGoalBean apply(@NonNull Response<ApplyDiagnosisGoalBean> objectResponse) throws Exception {
                        Log.i("sss",objectResponse.toString());
                        return objectResponse.body();
                    }
                });
    }
}

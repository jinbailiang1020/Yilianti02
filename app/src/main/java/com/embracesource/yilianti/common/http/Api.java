package com.embracesource.yilianti.common.http;

import android.util.Log;

import com.embracesource.yilianti.bean.ApplyDiagnosisDetailBean;
import com.embracesource.yilianti.bean.ApplyDiagnosisGoalBean;
import com.embracesource.yilianti.bean.ApplyDiagnosisRequestBean;
import com.embracesource.yilianti.bean.CustomerServiceDiagnosisListBean;
import com.embracesource.yilianti.bean.DiagnosisTeamBean;
import com.embracesource.yilianti.bean.DoctorBean;
import com.embracesource.yilianti.bean.HospitalBean;
import com.embracesource.yilianti.bean.HospitalWaitHandleListBean;
import com.embracesource.yilianti.bean.LoginBean;
import com.embracesource.yilianti.bean.MyLaunchListBean;
import com.embracesource.yilianti.bean.SimpleBean;
import com.embracesource.yilianti.bean.UserInfoBean;
import com.embracesource.yilianti.bean.UserTypeBean;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Response;

/**
 * Created by Administrator on 2017/10/16 0016.
 * <p>
 * .map(new Function<Object, LoginBean>() {
 *
 * @Override public LoginBean apply(@NonNull Object jsonObject) throws Exception {
 * Log.i(TAG, String.valueOf(new JSONObject(jsonObject.toString())));
 * return null;
 * }
 * }
 */

public class Api implements ApiInterface {
    //    @Inject
    public Api() {
    }

    @Override
    public Observable<LoginBean> login(String userName, String pwd) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("username", userName);
            jsonObject.put("pwd", pwd);
//            jsonObject.put("username", "zq");
//            jsonObject.put("pwd", "123456");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), jsonObject.toString());//传递json需要加上这一句
        return RetrofitConfig.getInstance().login(body)
                .subscribeOn(Schedulers.io());
    }


    @Override
    public Observable<ApplyDiagnosisDetailBean> getApplyDiagnosisDetail(int id, String flag) {//ApplyDiagnosisDetailBean
        return RetrofitConfig.getInstance_afterLogin().getApplyDiagnosisDetail(id, flag)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<SimpleBean> submitApplyDiagnosis(ApplyDiagnosisRequestBean bean) {
        String gsonStr = new Gson().toJson(bean);
        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), gsonStr);//传递json需要加上这一句
        return RetrofitConfig.getInstance_afterLogin().submitApplyDiagnosis(body)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<DiagnosisTeamBean> getDiagnosisTeam() {
        return RetrofitConfig.getInstance_afterLogin().getDiagnosisTeam()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<HospitalBean> changeHospitalList() {
        return RetrofitConfig.getInstance_afterLogin().changeHospitalList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<ApplyDiagnosisGoalBean> getBaseData(String code) {
        return RetrofitConfig.getInstance_afterLogin().getBaseData(code)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).map(new Function<Response<ApplyDiagnosisGoalBean>, ApplyDiagnosisGoalBean>() {
                    @Override
                    public ApplyDiagnosisGoalBean apply(@NonNull Response<ApplyDiagnosisGoalBean> objectResponse) throws Exception {
                        Log.i("sss", objectResponse.toString());
                        return objectResponse.body();
                    }
                });
    }

    @Override
    public Observable<DoctorBean> changeDoctorList(int groupId) {
        return RetrofitConfig.getInstance_afterLogin().changeDoctorList(groupId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<SimpleBean> diagnosisDetailsendPass_2(int id, int available) {
        return RetrofitConfig.getInstance_afterLogin().diagnosisDetailsendPass_2(id, available)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable diagnosisDetailsendUnPass_2(int id, int available, String content) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("content", content);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), jsonObject.toString());//传递json需要加上这一句
        return RetrofitConfig.getInstance_afterLogin().diagnosisDetailsendUnPass_2(id, available, body)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<UserTypeBean> selectUserRole() {
        return RetrofitConfig.getInstance_afterLogin().selectUserRole()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<HospitalWaitHandleListBean> getNeedHandleList(int pageNum, int pageSize) {
        return RetrofitConfig.getInstance_afterLogin().getNeedHandleList(pageNum, pageSize)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<MyLaunchListBean> getMyParticipateList(int pageNum, int pageSize) {
        return RetrofitConfig.getInstance_afterLogin().getMyParticipateList(pageNum, pageSize)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<MyLaunchListBean> getMyLaunchList(int pageNum, int pageSize) {//MyLaunchListBean
        return RetrofitConfig.getInstance_afterLogin().getMyLaunchList(pageNum, pageSize)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<SimpleBean> updatePwd(String userId, String pwd1, String pwd2) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("oldPwd", pwd1);
            jsonObject.put("newPwd", pwd2);
            jsonObject.put("newPwd2", pwd2);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), jsonObject.toString());//传递json需要加上这一句
        return RetrofitConfig.getInstance_afterLogin().updatePwd(body, userId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<UserInfoBean> getUserInfo() {
        return RetrofitConfig.getInstance_afterLogin().getUserInfo()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<SimpleBean>  customerService_nextStep(int id, int available) {
        return RetrofitConfig.getInstance_afterLogin().customerService_nextStep(id,available)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<CustomerServiceDiagnosisListBean> getCustomerServiceList(int flagFinish) {
        return RetrofitConfig.getInstance_afterLogin().getCustomerServiceList(flagFinish)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<SimpleBean> huizhenSubmit(String diagnosisAdvice, int id) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("content", diagnosisAdvice);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), jsonObject.toString());//传递json需要加上这一句
        return RetrofitConfig.getInstance_afterLogin().huizhenSubmit(body,id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<SimpleBean> changeToDiagnosis(JSONObject jsonObject, int id) {
        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), jsonObject.toString());//传递json需要加上这一句
        return RetrofitConfig.getInstance_afterLogin().changeToDiagnosis(body,id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<SimpleBean> diagnosisDetailSendPass_expert(JSONObject jsonObject,int id) {
        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), jsonObject.toString());//传递json需要加上这一句
        return RetrofitConfig.getInstance_afterLogin().diagnosisDetailSendPass_expert(body,id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<SimpleBean> diagnosisDetailSendUnPass_expert(JSONObject jsonObject,int id) {
        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), jsonObject.toString());//传递json需要加上这一句
        return RetrofitConfig.getInstance_afterLogin().diagnosisDetailSendPass_expert(body,id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}

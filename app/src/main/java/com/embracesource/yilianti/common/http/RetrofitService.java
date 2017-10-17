package com.embracesource.yilianti.common.http;


import com.embracesource.yilianti.bean.ApplyDiagnosisGoalBean;
import com.embracesource.yilianti.bean.BaseBean;
import com.embracesource.yilianti.bean.LoginBean;
import com.embracesource.yilianti.bean.MyLaunchListBean;

import org.json.JSONObject;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by yess on 2017-05-02.
 */

public interface RetrofitService {

    //    @FormUrlEncoded
//    @Headers({"Content-Type: application/json", "Accept: application/json"})//需要添加头
    @POST("login")
    Observable<LoginBean> login(@Body RequestBody jsonObject);

    //    http://localhost:8002/dict/selectListByCode?code={code}
    @GET("dict/selectListByCode")
    Observable<Response<ApplyDiagnosisGoalBean>> getBaseData(@Query("code") String code);

    //我发起的会诊列表接口

    //    @Headers("jsessionid":"D0924D8BD4C842AEAE4A25C320391169")
    @GET("workbench/myConsultation/mysubmit/list")
    Observable<MyLaunchListBean> getMyLaunchList(@Query("pageNum") int pageNum, @Query("pageSize") int pageSize);

    @GET("referralAndConsultation/detail/{id}")
    Observable<Response<JSONObject>> getApplyDiagnosisDetail(@Path("id") String id, @Query("flag") String flag);//ApplyDiagnosisDetailBean

    @POST("referralAndConsultation/submit")//会诊、转诊提交
    Observable<BaseBean> submitApplyDiagnosis(@Body RequestBody body);


//    http://192.168.1.165:8002/referralAndConsultation/detail/{id}?flag={flag}


//    Observable<LoginBean> login(@Query("username") String username, @Query("pwd") String pwd);

/*
    *//**
     * 获取上会议题详情
     * @param issueApplyId
     *//*
    @GET("crm/mobile/issue/getIssueApplyDetail")
    Observable<Response<TopicDetailViewModel>> getTopicDetails(@Query("issueApplyId") String issueApplyId);

    *//**
     * 删除上会议题
     *
     * @param issueApplyId
     * @return
     *//*
    @GET("crm/mobile/issue/deleteIssueApply")
    Observable<SimpleCallBack> deleteTopic(@Query("issueApplyId") String issueApplyId);

    *//**
     * 保存  提交  上会议题
     * state 状态：1.保存；2.提交;
     * @param
     * @return
     *//*
    @FormUrlEncoded
    @POST("crm/mobile/issue/addIssueApply")
    Observable<SimpleCallBack> save_or_SubmitTopic(@FieldMap Map<String, String> params);

    @FormUrlEncoded
    @POST("crm/mobile/issue/updateIssueApply")
    Observable<SimpleCallBack> updateTopic(@FieldMap Map<String, String> params);


    //////////////////////////////////////////////
    //市场信息与报告需求
    //////////////////////////////////////////////
    *//**
     * 获取列表
     * 市场二期迭代二
     * @param userId
     * @return
     *//*
    @GET("crm/mobile/demand/queryDemandList")
    Observable<InfoAndReportListViewModel> getInfoReportListData(@Query("userId") String userId);*/


}

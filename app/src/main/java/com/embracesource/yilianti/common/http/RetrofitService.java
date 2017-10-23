package com.embracesource.yilianti.common.http;


import com.embracesource.yilianti.bean.ApplyDiagnosisDetailBean;
import com.embracesource.yilianti.bean.ApplyDiagnosisGoalBean;
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
    Observable<MyLaunchListBean> getMyLaunchList(@Query("pageNum") int pageNum, @Query("pageSize") int pageSize);//MyLaunchListBean

    @GET("workbench/myConsultation/myreceive/list")
    Observable<MyLaunchListBean> getMyParticipateList(@Query("pageNum") int pageNum, @Query("pageSize") int pageSize);

    @GET("referralAndConsultation/detail/{id}")
    Observable<ApplyDiagnosisDetailBean> getApplyDiagnosisDetail(@Path("id") int id, @Query("flag") String flag);//ApplyDiagnosisDetailBean

    //会诊、转诊提交
    @POST("referralAndConsultation/submit")
    Observable<SimpleBean> submitApplyDiagnosis(@Body RequestBody body);

    @GET("referralAndConsultation/selectTeamList")
    Observable<DiagnosisTeamBean> getDiagnosisTeam();

    @GET("group/search")
    Observable<HospitalBean> changeHospitalList();

    //http://192.168.1.165:8002/referralAndConsultation/selectDoctorList?groupId={groupId}
    @GET("referralAndConsultation/selectDoctorList")
    Observable<DoctorBean> changeDoctorList(@Query("groupId") int groupId);

    @GET("referralAndConsultation/audit/{id}")
    Observable<SimpleBean> diagnosisDetailsendPass_2(@Path("id") int id, @Query("available") int available);

    @POST("referralAndConsultation/audit/{id}")
    Observable<SimpleBean> diagnosisDetailsendUnPass_2(@Path("id") int id, @Query("available") int available, @Body RequestBody content);//// TODO: 2017/10/23 0023

    @GET("account/selectUserRole")
    Observable<UserTypeBean> selectUserRole();

    @GET("workbench/todo/list")
    Observable<HospitalWaitHandleListBean> getHospitalList(@Query("pageNum") int pageNum, @Query("pageSize") int pageSize);

    @POST("set/personal/update/userpwd/{userId}")
    Observable<SimpleBean> updatePwd( @Body RequestBody content,@Path("userId")String userId);

    @GET("account/selectUser")
    Observable<UserInfoBean> getUserInfo();
    @GET("set/personal/update/userpwd/{id}")
    Observable<SimpleBean> customerService_nextStep(@Path("id") int id, @Query("available") int available);

    @GET("workbench/medicalService/list")
    Observable<CustomerServiceDiagnosisListBean> getCustomerServiceList(@Query("flagFinish") int flagFinish);


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

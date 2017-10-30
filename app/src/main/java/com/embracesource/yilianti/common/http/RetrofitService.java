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

    //group/search 只显示湘雅的医院
    @GET("referralAndConsultation/selectReferralGroupList")
    Observable<HospitalBean> changeHospitalList();

    //http://192.168.1.165:8002/referralAndConsultation/selectDoctorList?groupId={groupId}

    @GET("referralAndConsultation/selectDoctorList")
    Observable<DoctorBean> changeDoctorList(@Query("groupId") int groupId);

    @GET("referralAndConsultation/audit/{id}")
    Observable<SimpleBean> diagnosisDetailsendPass_2(@Path("id") int id, @Query("available") int available);

    @POST("referralAndConsultation/auditRefuse/{id}")
    Observable<SimpleBean> diagnosisDetailsendUnPass_2(@Path("id") int id, @Query("available") int available, @Body RequestBody content);//// TODO: 2017/10/23 0023  405

    @GET("account/selectUserRole")
    Observable<UserTypeBean> selectUserRole();

    @GET("workbench/todo/list")
    Observable<HospitalWaitHandleListBean> getNeedHandleList(@Query("pageNum") int pageNum, @Query("pageSize") int pageSize);

    @POST("set/personal/update/userpwd/{userId}")
    Observable<SimpleBean> updatePwd( @Body RequestBody content,@Path("userId")String userId);

    @GET("account/selectUser")
    Observable<UserInfoBean> getUserInfo();

//    http://192.168.1.165:8002/workbench/medicalService/op/76?available=8
    @GET("workbench/medicalService/op/{id}")//// TODO: 2017/10/23 0023  405
    Observable<SimpleBean> customerService_nextStep(@Path("id") int id, @Query("available") int available);

    @GET("workbench/medicalService/list")
    Observable<CustomerServiceDiagnosisListBean> getCustomerServiceList(@Query("flagFinish") int flagFinish);

    @POST("referralAndConsultation/teamReply/consultation/pass/{id}")
    Observable<SimpleBean> huizhenSubmit(@Body RequestBody body,@Path("id") int id);

    @POST("referralAndConsultation/teamReply/consultation/toReferral/{id}")
    Observable<SimpleBean> changeToDiagnosis(@Body RequestBody body,@Path("id") int id);

    @POST("referralAndConsultation/teamReply/referral/pass/{id}")
    Observable<SimpleBean> diagnosisDetailSendPass_expert(@Body RequestBody body,@Path("id") int id);

    @POST("referralAndConsultation/teamReply/referral/refuse/{id}")
    Observable<SimpleBean> diagnosisDetailSendUnPass_expert(@Body RequestBody body,@Path("id") int id);




//    http://192.168.1.165:8002/referralAndConsultation/detail/{id}?flag={flag}


}

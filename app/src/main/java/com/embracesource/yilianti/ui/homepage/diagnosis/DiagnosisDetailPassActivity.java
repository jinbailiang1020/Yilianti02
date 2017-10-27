package com.embracesource.yilianti.ui.homepage.diagnosis;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.codbking.widget.DatePickDialog;
import com.codbking.widget.OnSureLisener;
import com.codbking.widget.bean.DateType;
import com.embracesource.yilianti.R;
import com.embracesource.yilianti.bean.ApplyDiagnosisGoalBean;
import com.embracesource.yilianti.bean.DiagnosisTeamBean;
import com.embracesource.yilianti.bean.DoctorBean;
import com.embracesource.yilianti.bean.HospitalBean;
import com.embracesource.yilianti.bean.SimpleBean;
import com.embracesource.yilianti.bean.eventbus.RefreshDiagnosisListBean;
import com.embracesource.yilianti.common.dialog.DialogAdapter;
import com.embracesource.yilianti.common.http.Api;
import com.embracesource.yilianti.databinding.ActivityDiagnosisDetailPassBinding;
import com.embracesource.yilianti.ui.base.AacBaseActivity;
import com.embracesource.yilianti.ui.base.BaseActivity;
import com.embracesource.yilianti.util.PhoneUtils;
import com.embracesource.yilianti.viewmodel.ApplyDiagnosisViewModelCallBack;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import io.reactivex.annotations.NonNull;

import static com.embracesource.yilianti.R.string.submit;
import static com.embracesource.yilianti.ui.homepage.diagnosis.ApplyDiagnosisActivity02.SIMPLE_DATE_FORMAT;

//转诊单审批通过（专家）
public class DiagnosisDetailPassActivity extends AacBaseActivity<ActivityDiagnosisDetailPassBinding> implements View.OnClickListener, ApplyDiagnosisViewModelCallBack {

    private int id;
    private int currentSelected_changeHospital = -1;
    private int currentSelected_changeDoctor = -1;

    private Date startdate;
    private Date enddate;
    private int currentSelectDate;//当前选择的时间按钮
    private int currentClickItem = -1;//当前点击的类型

    private List<HospitalBean.DataBean> hospitalList = new ArrayList<>();
    private List<DoctorBean.DataBean> doctorList = new ArrayList<>();

    private PopupWindow popupWindow;
    private DialogAdapter adapter;
    private TextView popTitle;
    private ApplyDiagnosisViewModel02 viewModel02;
    private String referralPlanDate;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_diagnosis_detail_pass;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();
        initTitleLayout(getString(R.string.diagnosis_pass));
        setTitleRightVisiable(getString(submit), new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submit();
            }
        });
    }

    private void submit() {
        if (startdate == null) {
            showToast(getString(R.string.msg_input_start_date));
            return;
        }
        if (enddate == null) {
            showToast(getString(R.string.msg_input_end_date));
            return;
        }
        if (currentSelected_changeHospital == -1) {
            showToast("请选择转诊医院");
            return;
        }
        if (currentSelected_changeDoctor == -1) {
            showToast("请选择转诊医生");
            return;
        }
        String diagnosisAdvice = binding.etDiagnosisExplain.getText().toString().trim();
        if (diagnosisAdvice.length()<10) {
            showToast(getString(R.string.msg_input_diagnosis_advice));
            return;
        }
        String strStartDate = binding.tvStartDate.getText().toString();
        String strEndDate = binding.tvEndDate.getText().toString();

        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("content", diagnosisAdvice);
            jsonObject.put("referralHospital", currentSelected_changeHospital);
            jsonObject.put("referralDoctor", currentSelected_changeDoctor);
            jsonObject.put("referralPlanDate", referralPlanDate);
            jsonObject.put("referralStartdate", strStartDate);
            jsonObject.put("referralEnddate", strEndDate);
            showDialog();
            //专家团队转诊审核通过
            new Api().diagnosisDetailSendPass_expert(jsonObject, id).subscribe(new BaseActivity.MyObserver<SimpleBean>() {
                @Override
                public void onNextUI(@NonNull SimpleBean bean) {
                    showToast(bean.getMessage());
                    if (bean.isSuccess()) {
                        EventBus.getDefault().post(new RefreshDiagnosisListBean(""));
                        startActivity(new Intent(DiagnosisDetailPassActivity.this,DiagnosisPictureActivity.class));
                        finish();
                    } else {

                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void initData() {
        super.initData();
        id = getIntent().getIntExtra("id", -1);
        referralPlanDate = getIntent().getStringExtra("referralPlanDate");

        viewModel02 = new ApplyDiagnosisViewModel02(this);
        binding.tvChangeHospital.setOnClickListener(this);
        binding.tvChangeDoctor.setOnClickListener(this);
        binding.tvStartDate.setOnClickListener(this);
        binding.tvEndDate.setOnClickListener(this);

        initPopWindow();
    }

    private void initPopWindow() {
        LayoutInflater inflater = LayoutInflater.from(this);
        View view = inflater.inflate(R.layout.fragment_dialog_list, null, false);//container
        popTitle = (TextView) view.findViewById(R.id.tv_title);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new DialogAdapter(this);
        adapter.setPopwindowOnClickListener(new DialogAdapter.PopwindowOnClickListener() {
            @Override
            public void onClick(View v, int position) {
                switch (currentClickItem) {
                    case R.id.tv_change_hospital:
                        clearDoctor();
                        currentSelected_changeHospital = position;
                        binding.tvChangeHospital.setText(hospitalList.get(position).getName());
                        break;
                    case R.id.tv_change_doctor:
                        currentSelected_changeDoctor = position;
                        binding.tvChangeDoctor.setText(doctorList.get(position).getFullname());
                        break;
                }
                popupWindow.dismiss();
            }
        });
        recyclerView.setAdapter(adapter);
        popupWindow = new PopupWindow(view, PhoneUtils.getPhoneWidth(this) * 2 / 3, PhoneUtils.getPhoneHeight(this) * 2 / 3, true);
        popupWindow.setBackgroundDrawable(new ColorDrawable(Color.GRAY));
    }

    private void clearDoctor() {
        currentSelected_changeDoctor = -1;
        binding.tvChangeDoctor.setText("");
    }

    @Override
    public void onClick(View v) {
        currentClickItem = v.getId();
        switch (v.getId()) {

            case R.id.tv_change_hospital://医院
                if (hospitalList.isEmpty()) {
                    dialog.show();
                    viewModel02.changeHospitalList();
                } else {
                    adapter.setList(hospitalList);
                    showFragmentDialog(hospitalList);
                }
                break;
            case R.id.tv_change_doctor:
                if (currentSelected_changeHospital == -1) {
                    showToast("请先选择转诊医院");
                    return;
                }
                dialog.show();
                viewModel02.changeDoctorList(hospitalList.get(currentSelected_changeHospital).getId());
                break;

            case R.id.tv_start_date:
            case R.id.tv_end_date:
//                TYPE_ALL--年、月、日、星期、时、分
//                TYPE_YMDHM--年、月、日、时、分
//                TYPE_YMDH--年、月、日、时
//                TYPE_YMD--年、月、日
//                TYPE_HM--时、分
                currentSelectDate = v.getId();
                DatePickDialog dialog = new DatePickDialog(this);
                //设置上下年分限制
                dialog.setYearLimt(8);
                dialog.setStartDate(new Date());
                //设置标题
                dialog.setTitle("选择日期");
                //设置类型
                dialog.setType(DateType.TYPE_YMD);
                //设置消息体的显示格式，日期格式
                dialog.setMessageFormat(SIMPLE_DATE_FORMAT);
                //设置选择回调
                dialog.setOnChangeLisener(null);
                //设置点击确定按钮回调
                dialog.setOnSureLisener(new OnSureLisener() {
                    @Override
                    public void onSure(Date date) {
                        switch (currentSelectDate) {
                            case R.id.tv_start_date:
                                DiagnosisDetailPassActivity.this.startdate = date;
                                binding.tvStartDate.setText(new SimpleDateFormat(SIMPLE_DATE_FORMAT).format(date));
                                break;
                            case R.id.tv_end_date:
                                if(DiagnosisDetailPassActivity.this.startdate == null){
                                    showToast("请先选择开始时间");
                                    return;
                                }
                                if(DiagnosisDetailPassActivity.this.startdate.compareTo(date)>0){
                                    showToast("结束时间必须大于开始时间");
                                    return;
                                }
                                DiagnosisDetailPassActivity.this.enddate = date;
                                binding.tvEndDate.setText(new SimpleDateFormat(SIMPLE_DATE_FORMAT).format(date));
                                break;
                        }
                    }
                });
                dialog.show();
                break;
        }
    }

    private void showFragmentDialog(List data) {
        if (data == null || data.isEmpty()) {
            showToast(getString(R.string.msg_no_data));
            return;
        }
        Object entity = data.get(0);
        if (entity instanceof ApplyDiagnosisGoalBean.DataBean) {
            popTitle.setText(((ApplyDiagnosisGoalBean.DataBean) entity).getMark());
        } else if (entity instanceof HospitalBean.DataBean) {
            popTitle.setText(getString(R.string.change_hospital));
        } else if (entity instanceof DoctorBean.DataBean) {
            popTitle.setText(getString(R.string.change_doctor));
        } else if (entity instanceof DiagnosisTeamBean.DataBean) {
            popTitle.setText(getString(R.string.diagnosis_team));
        }
        popupWindow.showAtLocation(this.getWindow().getDecorView(), Gravity.CENTER, 0, 0);
//        popupWindow.setAnimationStyle(R.style.popwin_anim_style);
    }

    @Override
    public void getBaseDataOK(ApplyDiagnosisGoalBean response) {
        //useless
    }

    @Override
    public void submitApplyDiagnosisOK(SimpleBean response) {
//useless
    }

    @Override
    public void getDiagnosisTeamOK(DiagnosisTeamBean response) {
//useless
    }

    @Override
    public void changeHospitalListOK(HospitalBean response) {
        hospitalList.clear();
        hospitalList.addAll(response.getData());
        adapter.setList(response.getData());
        showFragmentDialog(response.getData());
    }

    @Override
    public void changeDoctorListOK(DoctorBean response) {
        doctorList.clear();
        doctorList.addAll(response.getData());
        adapter.setList(response.getData());
        showFragmentDialog(response.getData());
    }
}

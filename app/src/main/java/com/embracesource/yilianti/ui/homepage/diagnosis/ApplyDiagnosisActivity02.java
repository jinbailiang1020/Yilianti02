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
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.codbking.widget.DatePickDialog;
import com.codbking.widget.OnSureLisener;
import com.codbking.widget.bean.DateType;
import com.embracesource.yilianti.R;
import com.embracesource.yilianti.bean.ApplyDiagnosisGoalBean;
import com.embracesource.yilianti.bean.ApplyDiagnosisRequestBean;
import com.embracesource.yilianti.bean.DiagnosisTeamBean;
import com.embracesource.yilianti.bean.DoctorBean;
import com.embracesource.yilianti.bean.HospitalBean;
import com.embracesource.yilianti.bean.SimpleBean;
import com.embracesource.yilianti.bean.eventbus.RefreshDiagnosisListBean;
import com.embracesource.yilianti.common.dialog.DialogAdapter;
import com.embracesource.yilianti.databinding.ActivityApplyDiagnosis02Binding;
import com.embracesource.yilianti.ui.base.AacBaseActivity;
import com.embracesource.yilianti.util.PhoneUtils;
import com.embracesource.yilianti.viewmodel.ApplyDiagnosisViewModelCallBack;

import org.greenrobot.eventbus.EventBus;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class ApplyDiagnosisActivity02 extends AacBaseActivity<ActivityApplyDiagnosis02Binding> implements View.OnClickListener, ApplyDiagnosisViewModelCallBack {

    private static final String SIMPLE_DATE_FORMAT = "yyyy-MM-dd";
    private List<ApplyDiagnosisGoalBean.DataBean> goalList = new ArrayList<>();
    private List<ApplyDiagnosisGoalBean.DataBean> typeList = new ArrayList<>();
    private List<DiagnosisTeamBean.DataBean> teamList = new ArrayList<>();
    private List<ApplyDiagnosisGoalBean.DataBean> emergencyDegreeList = new ArrayList<>();
    private List<HospitalBean.DataBean.ListBean> hospitalList = new ArrayList<>();
    private List<DoctorBean.DataBean> doctorList = new ArrayList<>();

    private int currentClickItem = -1;//当前点击的类型

    private int currentSelected_goal = -1;
    private int currentSelected_type = -1;
    private int currentSelected_team = -1;
    private int currentSelected_emergencyDegree = -1;
    private int currentSelected_changeHospital = -1;
    private int currentSelected_changeDoctor = -1;

    private PopupWindow popupWindow;
    private DialogAdapter adapter;
    //    @Inject
    private ApplyDiagnosisViewModel02 viewModel;
    private ApplyDiagnosisRequestBean bean = new ApplyDiagnosisRequestBean();
    private TextView popTitle;
    private Date date;
    private int int_Is_change_disgnosis = 2;//会诊目的，转诊，positiopn == 2


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        DaggerAppComponent.builder().appModule(new AppModule()).build().inject(this); //注入
        viewModel = new ApplyDiagnosisViewModel02(this);
        initTitleLayout(getString(R.string.diagnosis_detail));
        setTitleRightVisiable(getString(R.string.submit), new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submit();
            }
        });
        initView();
        initData();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_apply_diagnosis02;//
    }

    @Override
    protected void initView() {
        super.initView();
        ArrayList<Map<String, String>> list = new ArrayList<Map<String, String>>();
        //加载适配器
        binding.spGoal.setOnClickListener(this);
        binding.spType.setOnClickListener(this);
        binding.spTeam.setOnClickListener(this);
        binding.spEmergencyDegree.setOnClickListener(this);
        binding.spChangeDoctor.setOnClickListener(this);
        binding.spChangeHospital.setOnClickListener(this);
        binding.spChangeDate.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        super.initData();
        bean = (ApplyDiagnosisRequestBean) getIntent().getSerializableExtra(ApplyDiagnosisRequestBean.class.getName());
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
                    case R.id.sp_goal:
                        currentSelected_goal = position;
                        binding.spGoal.setText(goalList.get(position).getDescription());
                        showChangeDiagnosis(currentSelected_goal == int_Is_change_disgnosis);
                        break;
                    case R.id.sp_type:
                        currentSelected_type = position;
                        binding.spType.setText(typeList.get(position).getDescription());
                        break;
                    case R.id.sp_team:
                        currentSelected_team = position;
                        binding.spTeam.setText(teamList.get(position).getDescription());
                        break;
                    case R.id.sp_emergency_degree:
                        currentSelected_emergencyDegree = position;
                        binding.spEmergencyDegree.setText(emergencyDegreeList.get(position).getDescription());
                        break;
                    case R.id.sp_change_hospital:
                        clearDoctor();
                        currentSelected_changeHospital = position;
                        binding.spChangeHospital.setText(hospitalList.get(position).getDescription());
                        break;
                    case R.id.sp_change_doctor:
                        currentSelected_changeDoctor = position;
                        binding.spChangeDoctor.setText(doctorList.get(position).getFullname());
                        break;
                }
                popupWindow.dismiss();
            }

            private void showChangeDiagnosis(boolean b) {
                if (b) {
                    binding.llGone.setVisibility(View.VISIBLE);
                } else {
                    binding.llGone.setVisibility(View.GONE);
                }

            }
        });
        recyclerView.setAdapter(adapter);
        popupWindow = new PopupWindow(view, PhoneUtils.getPhoneWidth(this) * 2 / 3, LinearLayout.LayoutParams.WRAP_CONTENT, true);
        popupWindow.setBackgroundDrawable(new ColorDrawable(Color.GRAY));
    }

    private void clearDoctor() {
        currentSelected_changeDoctor = -1;
        binding.spChangeDoctor.setText("");
    }

    @Override
    public void onClick(View v) {
        currentClickItem = v.getId();
        switch (v.getId()) {
            case R.id.sp_goal:
                if (goalList.isEmpty()) {
                    dialog.show();
                    viewModel.getBaseData("consultation_objective");//目的
                } else {
                    adapter.setList(goalList);
                    showFragmentDialog(goalList);
                }

                break;
            case R.id.sp_type:
                if (typeList.isEmpty()) {
                    dialog.show();
                    viewModel.getBaseData("consultation_type");// consultation_type 会诊类型
                } else {
                    adapter.setList(typeList);
                    showFragmentDialog(typeList);
                }
                break;
            case R.id.sp_team://会诊团队
                if (teamList.isEmpty()) {
                    dialog.show();
                    viewModel.getDiagnosisTeam();//
                } else {
                    adapter.setList(teamList);
                    showFragmentDialog(teamList);
                }

                break;
            case R.id.sp_emergency_degree:
                if (emergencyDegreeList.isEmpty()) {
                    dialog.show();
                    viewModel.getBaseData("priority");//priority 紧急程度
                } else {
                    adapter.setList(emergencyDegreeList);
                    showFragmentDialog(emergencyDegreeList);
                }
                break;
            case R.id.sp_change_hospital://医院
                if (hospitalList.isEmpty()) {
                    dialog.show();
                    viewModel.changeHospitalList();
                } else {
                    adapter.setList(hospitalList);
                    showFragmentDialog(hospitalList);
                }

                break;
            case R.id.sp_change_doctor:
                if (currentSelected_changeHospital == -1) {
                    showToast("请先选择转诊医院");
                    return;
                }
                dialog.show();
                viewModel.changeDoctorList(hospitalList.get(currentSelected_changeHospital).getId());
                break;

            case R.id.sp_change_date:
//                TYPE_ALL--年、月、日、星期、时、分
//                TYPE_YMDHM--年、月、日、时、分
//                TYPE_YMDH--年、月、日、时
//                TYPE_YMD--年、月、日
//                TYPE_HM--时、分
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
                        ApplyDiagnosisActivity02.this.date = date;
                        binding.spChangeDate.setText(new SimpleDateFormat(SIMPLE_DATE_FORMAT).format(date));
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
        ;
        Object entity = data.get(0);
        if (entity instanceof ApplyDiagnosisGoalBean.DataBean) {
            popTitle.setText(((ApplyDiagnosisGoalBean.DataBean) entity).getMark());
        } else if (entity instanceof HospitalBean.DataBean.ListBean) {
            popTitle.setText(getString(R.string.change_hospital));
        }else if(entity instanceof DoctorBean.DataBean){
            popTitle.setText(getString(R.string.change_doctor));
        }else if(entity instanceof DiagnosisTeamBean.DataBean){
            popTitle.setText(getString(R.string.diagnosis_team));
        }
        popupWindow.showAtLocation(this.getWindow().getDecorView(), Gravity.CENTER, 0, 0);
//        popupWindow.setAnimationStyle(R.style.popwin_anim_style);
    }

    @Override
    public void getBaseDataOK(ApplyDiagnosisGoalBean response) {
        switch (currentClickItem) {
            case R.id.sp_goal:
                goalList.clear();
                goalList.addAll(response.getData());
                break;
            case R.id.sp_type:
                typeList.clear();
                typeList.addAll(response.getData());
                break;
            case R.id.sp_emergency_degree:
                emergencyDegreeList.clear();
                emergencyDegreeList.addAll(response.getData());
                break;

        }
//        spinearFragmentDialog.setList(response.getData());
        adapter.setList(response.getData());
        showFragmentDialog(response.getData());
    }

    @Override
    public void submitApplyDiagnosisOK(SimpleBean response) {
        showToast(response.getMessage());
        if(response.isSuccess()){
            EventBus.getDefault().post(new RefreshDiagnosisListBean());
            Intent intent = new Intent(ApplyDiagnosisActivity02.this,DiagnosisPictureActivity.class);
            startActivity(intent);
            finish();
        }
    }


    @Override
    public void getDiagnosisTeamOK(DiagnosisTeamBean response) {
        teamList.clear();
        teamList.addAll(response.getData());
        adapter.setList(response.getData());
        showFragmentDialog(response.getData());
    }

    @Override
    public void changeHospitalListOK(HospitalBean response) {
        hospitalList.clear();
        hospitalList.addAll(response.getData().getList());
        adapter.setList(response.getData().getList());
        showFragmentDialog(response.getData().getList());
    }

    @Override
    public void changeDoctorListOK(DoctorBean response) {
        doctorList.clear();
        doctorList.addAll(response.getData());
        adapter.setList(response.getData());
        showFragmentDialog(response.getData());
    }

    private void submit() {
        try {
            if (currentSelected_goal == -1) {
                showToast("请选择会诊目的");
                return;
            }
            if (currentSelected_type == -1) {
                showToast("请选择会诊类型");
                return;
            }
            if (currentSelected_team == -1) {
                showToast("请选择会诊团队");
                return;
            }
            if (currentSelected_emergencyDegree == -1) {
                showToast("请选择紧急程度");
                return;
            }
            if (currentSelected_goal == int_Is_change_disgnosis) {//是转诊
                if (currentSelected_changeHospital == -1) {
                    showToast("请选择转诊医院");
                    return;
                }
                if (currentSelected_changeDoctor == -1) {
                    showToast("请选择转诊医生");
                    return;
                }
                if (date == null) {
                    showToast("请选择转诊时间");
                    return;
                }
            }
            bean.getConsultationReferral().setConsultationObjective(goalList.get(currentSelected_goal).getId());//目的
//        bean.getConsultationReferral().setConsultationType();//咨询类型// TODO: 2017/10/17 0017
            bean.getConsultationReferral().setPriority(emergencyDegreeList.get(currentSelected_emergencyDegree).getId());//优先
//        bean.getConsultationReferral().setSaveKey();// TODO: 2017/10/17 0017
            bean.getConsultationReferral().setTeam(teamList.get(currentSelected_team).getId());
            bean.getConsultationReferral().setType(typeList.get(currentSelected_type).getId());//
            bean.getConsultationReferral().setReferralDoctor(doctorList.get(currentSelected_changeDoctor).getId());
            bean.getConsultationReferral().setReferralHospital(hospitalList.get(currentSelected_changeHospital).getId());
            bean.getConsultationReferral().setReferralPlanDate(new SimpleDateFormat(SIMPLE_DATE_FORMAT).format(date));
            viewModel.submit(bean);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}

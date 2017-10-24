package com.embracesource.yilianti.ui.homepage.diagnosis;

import android.app.AlertDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.design.widget.TabLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.codbking.widget.DatePickDialog;
import com.codbking.widget.OnSureLisener;
import com.codbking.widget.bean.DateType;
import com.embracesource.yilianti.R;
import com.embracesource.yilianti.bean.ApplyDiagnosisDetailBean;
import com.embracesource.yilianti.bean.ApplyDiagnosisGoalBean;
import com.embracesource.yilianti.bean.DiagnosisTeamBean;
import com.embracesource.yilianti.bean.DoctorBean;
import com.embracesource.yilianti.bean.HospitalBean;
import com.embracesource.yilianti.bean.SimpleBean;
import com.embracesource.yilianti.bean.UserType;
import com.embracesource.yilianti.bean.enums.ConsultationObjectiveType;
import com.embracesource.yilianti.bean.eventbus.RefreshDiagnosisListBean;
import com.embracesource.yilianti.common.dialog.DialogAdapter;
import com.embracesource.yilianti.common.memory.MyPrefrences;
import com.embracesource.yilianti.databinding.ActivityApplyDiagnosisDetailBinding;
import com.embracesource.yilianti.ui.base.AacBaseActivity;
import com.embracesource.yilianti.util.PhoneUtils;
import com.embracesource.yilianti.util.StringUtils;
import com.embracesource.yilianti.viewmodel.ApplyDiagnosisDetailCallBack;
import com.embracesource.yilianti.viewmodel.ApplyDiagnosisViewModelCallBack;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.embracesource.yilianti.R.id.rb_change_to_diagnosis_yes;
import static com.embracesource.yilianti.bean.enums.DiagnosisExaminationType.STATUS_Medical_Service;
import static com.embracesource.yilianti.bean.enums.DiagnosisExaminationType.STATUS_Top_Medical_Service;
import static com.embracesource.yilianti.ui.homepage.diagnosis.ApplyDiagnosisActivity02.SIMPLE_DATE_FORMAT;

//                        转诊会诊详情
//                        http://192.168.1.165:8002/referralAndConsultation/detail/{id}?flag={flag}
//                        flag：1 医务处审核，2 详情展示，3 专家回复
public class ApplyDiagnosisDetailActivity extends AacBaseActivity<ActivityApplyDiagnosisDetailBinding> implements ApplyDiagnosisDetailCallBack, ApplyDiagnosisViewModelCallBack, View.OnClickListener {
    public static final String IS_participate = "IS_participate";
    private ApplyDiagnosisDetailViewModel viewModel;

    private AlertDialog alertDialog;

    /**
     * 用户类型
     * 1 用户
     * 2 医生 、、、
     * 3 患者
     * 4 游客
     * 5 医务处、、、
     * 6 客服 、、、
     * 7 系统管理员
     */
    private int role;//
    private int id;
    private EditText et_upPass_msg;
    private boolean isParticipate;        //我的参与进来的，如果是医生，需要显示审批相关按钮
    private ApplyDiagnosisDetailBean bean;

    private int currentClickItem = -1;//当前点击的类型

    private int currentSelected_goal = -1;
    private int currentSelected_type = -1;
    private int currentSelected_team = -1;
    private int currentSelected_emergencyDegree = -1;
    private int currentSelected_changeHospital = -1;
    private int currentSelected_changeDoctor = -1;

    private List<HospitalBean.DataBean.ListBean> hospitalList = new ArrayList<>();
    private List<DoctorBean.DataBean> doctorList = new ArrayList<>();

    private PopupWindow popupWindow;
    private DialogAdapter adapter;
    private TextView popTitle;
    private ApplyDiagnosisViewModel02 viewModel02;
    private Date startdate;
    private Date enddate;
    private int currentSelectDate;//当前选择的时间按钮

    @Override
    protected int getLayoutId() {
        return R.layout.activity_apply_diagnosis_detail;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initTitleLayout("会诊详情");
        initView();
        initData();
    }

    @Override
    protected void initView() {
        super.initView();
        role = myPrefrences.getInt(MyPrefrences.Key.role);
        binding.btnExaminePass.setOnClickListener(this);
        binding.btnExamineUnpass.setOnClickListener(this);

        binding.tvChangeHospital.setOnClickListener(this);
        binding.tvChangeDoctor.setOnClickListener(this);
        binding.spEmergencyDegree.setOnClickListener(this);
        binding.spStartDate.setOnClickListener(this);
        binding.spEndDate.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        super.initData();
        viewModel = new ApplyDiagnosisDetailViewModel(this);
        viewModel02 = new ApplyDiagnosisViewModel02(this);
        id = getIntent().getIntExtra(ApplyDiagnosisDetailActivity.class.getName(), 0);
        isParticipate = R.id.rb_my_participate == getIntent().getIntExtra(IS_participate, Integer.MAX_VALUE);
        showDialog();
        viewModel.getDetail(id, "2");
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
          /*          case R.id.sp_goal:
                        currentSelected_goal = position;
                        binding.spGoal.setText(goalList.get(position).getDescription());
                        showChangeDiagnosis(currentSelected_goal == int_Is_change_disgnosis);
                        break;*/
            /*        case R.id.sp_type:
                        currentSelected_type = position;
                        binding.spType.setText(typeList.get(position).getDescription());
                        break;*/
                  /*  case R.id.sp_team:
                        currentSelected_team = position;
                        binding.spTeam.setText(teamList.get(position).getDescription());
                        break;*/
                /*    case R.id.sp_emergency_degree:
                        currentSelected_emergencyDegree = position;
                        binding.spEmergencyDegree.setText(emergencyDegreeList.get(position).getDescription());
                        break;*/
                    case R.id.sp_change_hospital:
                        clearDoctor();
                        currentSelected_changeHospital = position;
                        binding.tvChangeHospital.setText(hospitalList.get(position).getDescription());
                        break;
                    case R.id.sp_change_doctor:
                        currentSelected_changeDoctor = position;
                        binding.tvChangeDoctor.setText(doctorList.get(position).getFullname());
                        break;
                }
                popupWindow.dismiss();
            }
        });
        recyclerView.setAdapter(adapter);
        popupWindow = new PopupWindow(view, PhoneUtils.getPhoneWidth(this) * 2 / 3, LinearLayout.LayoutParams.WRAP_CONTENT, true);
        popupWindow.setBackgroundDrawable(new ColorDrawable(Color.GRAY));
    }

    private void clearDoctor() {
        currentSelected_changeDoctor = -1;
        binding.tvChangeDoctor.setText("");
    }

    @Override
    public void getApplyDiagnosisDetailOK(ApplyDiagnosisDetailBean applyDiagnosisDetailBean) {
        try {
            binding.setBean(applyDiagnosisDetailBean);
            this.bean = applyDiagnosisDetailBean;
            ApplyDiagnosisDetailBean.DataBean.ListBean itemBean = applyDiagnosisDetailBean.getData().getList().get(0);
            binding.setItemBean(itemBean);
            if (itemBean.getConsultationObjective() == ConsultationObjectiveType.ChangeDiagnosis.id) { //如果是转诊
                binding.llChangeDiagnosis.setVisibility(View.VISIBLE);
                binding.rgChangeToDiagnosis.setVisibility(View.GONE);

                binding.tvChangeHospital.setText(itemBean.getReferralHospitalName());
                binding.tvChangeDoctor.setText(itemBean.getReferralDoctorName());
                binding.spChangeDate.setText(itemBean.getReferralPlanDate());
                binding.spStartDate.setText(itemBean.getReferralStartdate());
                binding.spEndDate.setText(itemBean.getReferralEnddate());

            } else {//如果是会诊
                binding.llChangeDiagnosis.setVisibility(View.GONE);
                binding.rgChangeToDiagnosis.setVisibility(View.VISIBLE);
                binding.rgChangeToDiagnosis.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                        if (checkedId == rb_change_to_diagnosis_yes) {//会诊转转诊
                            binding.llChangeDiagnosis.setVisibility(View.VISIBLE);
                            binding.tvChangeHospital.setEnabled(true);
                            binding.tvChangeDoctor.setEnabled(true);
                            binding.spChangeDate.setEnabled(true);
                            binding.spEndDate.setEnabled(true);
                            binding.spStartDate.setEnabled(true);
                            Drawable drawableRight = getResources().getDrawable(
                                    R.drawable.down_blue_25);
                            binding.tvChangeHospital.setCompoundDrawablesWithIntrinsicBounds(null, null, drawableRight, null);
                            binding.tvChangeDoctor.setCompoundDrawablesWithIntrinsicBounds(null, null, drawableRight, null);
                            binding.spChangeDate.setCompoundDrawablesWithIntrinsicBounds(null, null, drawableRight, null);

                        } else {
                            binding.llChangeDiagnosis.setVisibility(View.GONE);
                            binding.tvChangeHospital.setEnabled(false);
                            binding.tvChangeDoctor.setEnabled(false);
                            binding.spEmergencyDegree.setEnabled(false);
                            binding.spEndDate.setEnabled(false);
                            binding.spStartDate.setEnabled(false);
                        }
                    }
                });
            }

            if (role == UserType.DOCTOR && isParticipate) {//是医生并且 是我参与(专家审批)，
                binding.llExamine.setVisibility(View.VISIBLE);

                if (itemBean.getConsultationObjective() == ConsultationObjectiveType.ChangeDiagnosis.id) {////如果是转诊
                    binding.btnHuizhenSubmit.setVisibility(View.GONE);
                    binding.btnExamineUnpass.setVisibility(View.VISIBLE);
                    binding.btnExaminePass.setVisibility(View.VISIBLE);
                } else {//如果是会诊
                    binding.btnHuizhenSubmit.setVisibility(View.VISIBLE);
                    binding.btnExamineUnpass.setVisibility(View.GONE);
                    binding.btnExaminePass.setVisibility(View.GONE);
                }
                initShowUnPassAlertDialog();//// TODO: 2017/10/24 0024
            } else if (role == UserType.Medical_Service) {//是医务处
                binding.llExamine.setVisibility(View.VISIBLE);
                binding.btnHuizhenSubmit.setVisibility(View.GONE);
                binding.btnExamineUnpass.setVisibility(View.VISIBLE);
                binding.btnExaminePass.setVisibility(View.VISIBLE);
                initShowUnPassAlertDialog();//// TODO: 2017/10/24 0024
            } else {
                binding.llExamine.setVisibility(View.GONE);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View v) {
        currentClickItem = v.getId();
        switch (v.getId()) {
            case R.id.btn_examine_pass:
                sendPass();
                break;
            case R.id.btn_examine_unpass:
                alertDialog.show();
                break;
            case R.id.btn_huizhen_submit:
                submitHuiZhen_or_changeToDiagnosis();
                break;

            case R.id.sp_change_hospital://医院
                if (hospitalList.isEmpty()) {
                    dialog.show();
                    viewModel02.changeHospitalList();
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
                viewModel02.changeDoctorList(hospitalList.get(currentSelected_changeHospital).getId());
                break;

            case R.id.sp_start_date:
            case R.id.sp_end_date:
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
                            case R.id.sp_start_date:
                                ApplyDiagnosisDetailActivity.this.startdate = date;
                                binding.spStartDate.setText(new SimpleDateFormat(SIMPLE_DATE_FORMAT).format(date));
                                break;
                            case R.id.sp_end_date:
                                ApplyDiagnosisDetailActivity.this.enddate = date;
                                binding.spEndDate.setText(new SimpleDateFormat(SIMPLE_DATE_FORMAT).format(date));
                                break;
                        }
                    }
                });
                dialog.show();
                break;
        }
    }

    private void submitHuiZhen_or_changeToDiagnosis() {
        String diagnosisAdvice = binding.etDiagnosisAdvice.getText().toString().trim();
        if (diagnosisAdvice.isEmpty()) {
            showToast(getString(R.string.msg_input_diagnosis_advice));
            return;
        }
        if (binding.rbChangeToDiagnosisYes.isChecked()) {//  会诊转转诊 加： 开始时间，结束时间
            try {
                //// TODO: 2017/10/24 0024  非空判断
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("content", diagnosisAdvice);
                jsonObject.put("referralHospital", hospitalList.get(currentSelected_changeHospital).getId());
                jsonObject.put("referralDoctor", doctorList.get(currentSelected_changeDoctor).getId());
                jsonObject.put("referralPlanDate", "");
                jsonObject.put("referralStartdate", new SimpleDateFormat(SIMPLE_DATE_FORMAT).format(startdate));
                jsonObject.put("referralEnddate", new SimpleDateFormat(SIMPLE_DATE_FORMAT).format(enddate));
                viewModel.changeToDiagnosis(id, jsonObject);//会诊转转诊
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            //会诊通过
            viewModel.huizhenSubmit(diagnosisAdvice, id);
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
        } else if (entity instanceof HospitalBean.DataBean.ListBean) {
            popTitle.setText(getString(R.string.change_hospital));
        } else if (entity instanceof DoctorBean.DataBean) {
            popTitle.setText(getString(R.string.change_doctor));
        } else if (entity instanceof DiagnosisTeamBean.DataBean) {
            popTitle.setText(getString(R.string.diagnosis_team));
        }
        popupWindow.showAtLocation(this.getWindow().getDecorView(), Gravity.CENTER, 0, 0);
//        popupWindow.setAnimationStyle(R.style.popwin_anim_style);
    }

    //审批通过
    private void sendPass() {
        showDialog();
        switch (role) {
            case UserType.Medical_Service:
                if (bean != null && bean.getData() != null && bean.getData().getList() != null && !bean.getData().getList().isEmpty()) {
                    int available = bean.getData().getList().get(0).getAvailable();
                    if (available == STATUS_Medical_Service.id) {//医务处  审批通过
                        viewModel.sendPass_2(id);
                    } else if (available == STATUS_Top_Medical_Service.id) {//上级医务处 审批通过
                        viewModel.sendPass_3(id);
                    } else {
                        //数据类型改了
                    }
                }
                break;

            case UserType.DOCTOR://医生  专家 审批通过
                viewModel.sendPass_4(id);
                break;
            default:
                hideDialog();
                break;
        }
    }

    //审批不通过
    private void sendUnPass() {
        String msg = et_upPass_msg.getText().toString();
        switch (role) {
            case UserType.Medical_Service:
                if (bean != null && bean.getData() != null && bean.getData().getList() != null && !bean.getData().getList().isEmpty()) {
                    int available = bean.getData().getList().get(0).getAvailable();

                    if (available == STATUS_Medical_Service.id) {//医务处 审批不通过
                        if (StringUtils.isNullorEmpty(msg)) {
                            showToast(getString(R.string.msg_input_unpass_reason));
                        } else {
                            showDialog();
                            viewModel.sendUnPass_2(id, msg);
                        }
                    } else if (available == STATUS_Top_Medical_Service.id) {//上级医务处 审批不通过

                        if (StringUtils.isNullorEmpty(msg)) {
                            showToast(getString(R.string.msg_input_unpass_reason));
                        } else {
                            showDialog();
                            viewModel.sendUnPass_3(id, msg);
                        }
                    } else {
                        //数据类型改了
                    }
                }
                break;

            case UserType.DOCTOR://医生  专家 审批不通过
                if (StringUtils.isNullorEmpty(msg)) {
                    showToast(getString(R.string.msg_input_unpass_reason));
                } else {
                    showDialog();
//                    viewModel.sendUnPass_4(id, msg);、、// TODO: 2017/10/20 0020 专家需求要确认
                }
                break;
            default:
                hideDialog();
                break;
        }
    }

    private void initShowUnPassAlertDialog() {
        View alertView = null;
        if (role == UserType.DOCTOR && isParticipate) {//专家审批
//            http://www.jianshu.com/p/7ee916e5fae7
            alertView = View.inflate(this, R.layout.dialog_input_expert, null);
            TabLayout title = (TabLayout) alertView.findViewById(R.id.tabs);
            title.addTab(title.newTab().setText("药物意见"), true);
            title.addTab(title.newTab().setText("手术意见"));
            title.addTab(title.newTab().setText("其他意见"));
            final View v1 = alertView.findViewById(R.id.ll_1);
            final View v2 = alertView.findViewById(R.id.ll_2);
            final View v3 = alertView.findViewById(R.id.ll_3);

            final EditText et_jisu = (EditText) alertView.findViewById(R.id.et_jisu);
            final EditText et_kangshengsu = (EditText) alertView.findViewById(R.id.et_kangshengsu);
            final EditText et_kangguomin = (EditText) alertView.findViewById(R.id.et_kangguomin);
            final EditText et_waiyongyao = (EditText) alertView.findViewById(R.id.et_waiyongyao);
            final EditText et_shoushu = (EditText) alertView.findViewById(R.id.et_shoushu);
            final EditText et_other = (EditText) alertView.findViewById(R.id.et_other);

            title.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
                @Override
                public void onTabSelected(TabLayout.Tab tab) {
                    switch (tab.getPosition()) {
                        case 0:
                            v1.setVisibility(View.VISIBLE);
                            v2.setVisibility(View.GONE);
                            v3.setVisibility(View.GONE);
                            break;
                        case 1:
                            v1.setVisibility(View.GONE);
                            v2.setVisibility(View.VISIBLE);
                            v3.setVisibility(View.GONE);
                            break;
                        case 2:
                            v1.setVisibility(View.GONE);
                            v2.setVisibility(View.GONE);
                            v3.setVisibility(View.VISIBLE);
                            break;
                    }
                }

                @Override
                public void onTabUnselected(TabLayout.Tab tab) {

                }

                @Override
                public void onTabReselected(TabLayout.Tab tab) {

                }
            });
            alertView.findViewById(R.id.btn_sure).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String jisu = et_jisu.getText().toString();
                    String kangshengsu = et_kangshengsu.getText().toString();
                    String kangguomin = et_kangguomin.getText().toString();
                    String waiyongyao = et_waiyongyao.getText().toString();
                    String shoushu = et_shoushu.getText().toString();
                    String other = et_other.getText().toString();
                    sendUnPassExpert();//// TODO: 2017/10/24 0024 拒绝
                }
            });

        } else if (role == UserType.Medical_Service) {//医务处审批
            alertView = View.inflate(this, R.layout.dialog_input, null);
            TextView tv_title = (TextView) alertView.findViewById(R.id.tv_title);
            et_upPass_msg = (EditText) alertView.findViewById(R.id.et_msg);
            alertView.findViewById(R.id.btn_sure).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    sendUnPass();
                }
            });
            tv_title.setText(getString(R.string.unpass_reason));
        } else {
            //
        }
        alertView.findViewById(R.id.btn_cancle).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });
        AlertDialog.Builder build = new AlertDialog.Builder(this).setView(alertView);
        alertDialog = build.create();
    }

    //专家审批不通过
    private void sendUnPassExpert() {

    }

    @Override
    public void sendPassOK(SimpleBean bean) {
        showToast(bean.getMessage());
        if (bean.isSuccess()) {
            EventBus.getDefault().post(new RefreshDiagnosisListBean());
            finish();
        } else {

        }
    }

    @Override
    public void sendUnPassOK(SimpleBean bean) {
        showToast(bean.getMessage());
        if (bean.isSuccess()) {
            EventBus.getDefault().post(new RefreshDiagnosisListBean());
            finish();
        } else {

        }
    }

    @Override
    public void huizhenSubmitOK(SimpleBean bean) {
        showToast(bean.getMessage());
        if (bean.isSuccess()) {
            EventBus.getDefault().post(new RefreshDiagnosisListBean());
            finish();
        }
    }

    @Override
    public void getBaseDataOK(ApplyDiagnosisGoalBean response) {

    }

    @Override
    public void submitApplyDiagnosisOK(SimpleBean response) {

    }

    @Override
    public void getDiagnosisTeamOK(DiagnosisTeamBean response) {

    }

    @Override
    public void changeHospitalListOK(HospitalBean response) {

    }

    @Override
    public void changeDoctorListOK(DoctorBean response) {

    }
}

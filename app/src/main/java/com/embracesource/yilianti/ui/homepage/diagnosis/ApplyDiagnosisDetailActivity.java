package com.embracesource.yilianti.ui.homepage.diagnosis;

import android.app.AlertDialog;
import android.content.Intent;
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
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
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
import com.embracesource.yilianti.bean.enums.DiagnosisExaminationType;
import com.embracesource.yilianti.bean.eventbus.RefreshDiagnosisListBean;
import com.embracesource.yilianti.common.adapter.CommonAdapter;
import com.embracesource.yilianti.common.adapter.ViewHolder;
import com.embracesource.yilianti.common.dialog.DialogAdapter;
import com.embracesource.yilianti.common.imagepicker.PicassoImageLoader;
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

/**
 * 转诊会诊详情
 * ////////////////////////////////////////////////////////
 * 角色  ：
 * 1 医务处：
 * 2 上级医务处：
 * 审批通过，审批不通过
 * 3 专家：
 * （会诊：）
 * 填写会诊意见，会诊提交；
 * 会诊转转诊 //////
 * （转诊：）转诊同意：跳转界面选择医院医生时间段，提交
 * 转诊不同意：激素，外用药等，提交
 * //////////////////////////////////////////////////////
 * http://192.168.1.165:8002/referralAndConsultation/detail/{id}?flag={flag}
 * //            flag：1 医务处审核，2 详情展示，3 专家回复
 */
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

    private Date startdate;
    private Date enddate;
    private Date plandate;
    private int currentSelectDate;//当前选择的时间按钮

    private List<HospitalBean.DataBean> hospitalList = new ArrayList<>();
    private List<DoctorBean.DataBean> doctorList = new ArrayList<>();

    private PopupWindow popupWindow;
    private DialogAdapter adapter;
    private TextView popTitle;
    private ApplyDiagnosisViewModel02 viewModel02;
    private ApplyDiagnosisDetailBean.DataBean.ListBean itemBean;


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
        binding.spChangeDate.setOnClickListener(this);
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
            itemBean = applyDiagnosisDetailBean.getData().getList().get(0);
            binding.setItemBean(itemBean);
            if (itemBean != null && itemBean.getPicIdcard() != null) {
                if (itemBean.getPicIdcard().size() > 0) {
                    Glide.with(ApplyDiagnosisDetailActivity.this)//
                            .load(itemBean.getPicIdcard().get(0))
                            .centerCrop()//
                            .into(binding.ivSelectIdcard01);
                }
                if (itemBean.getPicIdcard().size() > 1) {
                    Glide.with(ApplyDiagnosisDetailActivity.this)//
                            .load(itemBean.getPicIdcard().get(1))
                            .centerCrop()//
                            .into(binding.ivSelectIdcard01);
                }
            }
            if (itemBean != null && itemBean.getPicIllness() != null) {
                //患处图片adapter
                CommonAdapter symptomsAdapter = new CommonAdapter(mContext, R.layout.image_item, itemBean.getPicIllness()) {
                    @Override
                    protected void convert(ViewHolder holder, Object o, int position) {
                        String entity = (String) o;
                        new PicassoImageLoader().displayImage(ApplyDiagnosisDetailActivity.this,
                                entity,
                                (ImageView) holder.itemView.findViewById(R.id.iv),
                                binding.ivSelectIdcard01.getMeasuredWidth(),
                                binding.ivSelectIdcard01.getMeasuredWidth());
                    }
                };
                binding.recyclerView.setAdapter(symptomsAdapter);
                binding.recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
            }

            if (itemBean.getConsultationObjective() == ConsultationObjectiveType.ChangeDiagnosis.id) { //如果是转诊
                binding.llChangeDiagnosis.setVisibility(View.VISIBLE);
                binding.rgChangeToDiagnosis.setVisibility(View.GONE);

                binding.tvChangeHospital.setText(itemBean.getReferralHospitalName());
                binding.tvChangeDoctor.setText(itemBean.getReferralDoctorName());
                binding.spChangeDate.setText(itemBean.getReferralPlanDate());
                if (StringUtils.isNullorEmpty(itemBean.getReferralStartdate())) {
                    binding.llStartDate.setVisibility(View.GONE);
                } else {
                    binding.llStartDate.setVisibility(View.VISIBLE);
                    binding.spStartDate.setText(itemBean.getReferralStartdate());
                }
                if (StringUtils.isNullorEmpty(itemBean.getReferralEnddate())) {
                    binding.llEndDate.setVisibility(View.GONE);
                } else {
                    binding.llEndDate.setVisibility(View.VISIBLE);
                    binding.spEndDate.setText(itemBean.getReferralEnddate());
                }

            } else {//如果是会诊
                binding.llChangeDiagnosis.setVisibility(View.GONE);
                binding.rgChangeToDiagnosis.setVisibility(View.VISIBLE);
                binding.rgChangeToDiagnosis.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                        if (checkedId == rb_change_to_diagnosis_yes) {//会诊转转诊
                            binding.llChangeDiagnosis.setVisibility(View.VISIBLE);
                            binding.llHuizhenAdvice.setVisibility(View.GONE);
                            binding.tvChangeHospital.setEnabled(true);
                            binding.tvChangeDoctor.setEnabled(true);
                            binding.spChangeDate.setEnabled(true);
                            binding.spEndDate.setEnabled(true);
                            binding.spStartDate.setEnabled(true);
                            Drawable drawableRight = getResources().getDrawable(R.drawable.down_blue_25);
                            binding.tvChangeHospital.setCompoundDrawablesWithIntrinsicBounds(null, null, drawableRight, null);
                            binding.tvChangeDoctor.setCompoundDrawablesWithIntrinsicBounds(null, null, drawableRight, null);
                            binding.spChangeDate.setCompoundDrawablesWithIntrinsicBounds(null, null, drawableRight, null);

                        } else {
                            binding.llHuizhenAdvice.setVisibility(View.VISIBLE);
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
                    binding.llHuizhenAdvice.setVisibility(View.GONE);
                    binding.btnHuizhenSubmit.setVisibility(View.GONE);
                    binding.btnExamineUnpass.setVisibility(View.VISIBLE);
                    binding.btnExaminePass.setVisibility(View.VISIBLE);
                } else {//如果是会诊
                    binding.llHuizhenAdvice.setVisibility(View.VISIBLE);
                    binding.btnHuizhenSubmit.setVisibility(View.VISIBLE);
                    binding.btnExamineUnpass.setVisibility(View.GONE);
                    binding.btnExaminePass.setVisibility(View.GONE);
                }
                initShowUnPassAlertDialog();
            } else if (role == UserType.Medical_Service) {//是医务处
                binding.llExamine.setVisibility(View.VISIBLE);
                binding.btnHuizhenSubmit.setVisibility(View.GONE);
                binding.btnExamineUnpass.setVisibility(View.VISIBLE);
                binding.btnExaminePass.setVisibility(View.VISIBLE);
                initShowUnPassAlertDialog();
            } else {
                binding.llExamine.setVisibility(View.GONE);
            }
            //已完成，不需要操作
            if (DiagnosisExaminationType.getDiagnosisExaminationType(itemBean.getAvailable()) == DiagnosisExaminationType.STATUS_Finished) {
                binding.btnHuizhenSubmit.setVisibility(View.GONE);
                binding.btnExamineUnpass.setVisibility(View.GONE);
                binding.btnExaminePass.setVisibility(View.GONE);
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
                if(UserType.DOCTOR == role){
                    Intent intent = new Intent(ApplyDiagnosisDetailActivity.this, DiagnosisDetailUnPassActivity.class);
                    intent.putExtra("id", id);
                    startActivity(intent);
                }else{
                    alertDialog.show();
                }
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
                            case R.id.sp_change_date:
                                ApplyDiagnosisDetailActivity.this.plandate = date;
                                binding.spChangeDate.setText(new SimpleDateFormat(SIMPLE_DATE_FORMAT).format(date));
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
        if (diagnosisAdvice.length() < 10) {
            showToast(getString(R.string.msg_input_diagnosis_advice));
            return;
        }
        if (binding.rbChangeToDiagnosisYes.isChecked()) {//  会诊转转诊 加： 开始时间，结束时间
            try {
                if (currentSelected_changeHospital == -1) {
                    showToast("请先选择转诊医院");
                    return;
                }
                if (currentSelected_changeDoctor == -1) {
                    showToast("请先选择转诊医生");
                }
                if (plandate == null) {
                    showToast("请先选择转诊时间");
                }
                if (startdate == null) {
                    showToast("请先选择转诊开始时间");
                }
                if (enddate == null) {
                    showToast("请先选择转诊结束时间");
                }

                JSONObject jsonObject = new JSONObject();
                jsonObject.put("content", diagnosisAdvice);
                jsonObject.put("referralHospital", hospitalList.get(currentSelected_changeHospital).getId());
                jsonObject.put("referralDoctor", doctorList.get(currentSelected_changeDoctor).getId());
                jsonObject.put("referralPlanDate", new SimpleDateFormat(SIMPLE_DATE_FORMAT).format(plandate));
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

    //审批通过
    private void sendPass() {
        switch (role) {
            case UserType.Medical_Service:
                if (bean != null && bean.getData() != null && bean.getData().getList() != null && !bean.getData().getList().isEmpty()) {
                    int available = bean.getData().getList().get(0).getAvailable();
                    if (available == STATUS_Medical_Service.id) {//医务处  审批通过
                        showDialog();
                        viewModel.sendPass_2(id);
                    } else if (available == STATUS_Top_Medical_Service.id) {//上级医务处 审批通过
                        showDialog();
                        viewModel.sendPass_3(id);
                    } else {
                        //数据类型改了
                    }
                }
                break;

            case UserType.DOCTOR://医生  专家 审批通过
                Intent intent = new Intent(ApplyDiagnosisDetailActivity.this, DiagnosisDetailPassActivity.class);
                intent.putExtra("id", id);
                try {
                    intent.putExtra("referralPlanDate", bean.getData().getList().get(0).getReferralPlanDate());
                } catch (Exception e) {
                    e.printStackTrace();
                    intent.putExtra("referralPlanDate", "");
                }
                startActivity(intent);
                break;
            default:
                hideDialog();
                break;
        }
    }

    //审批不通过 (专家的会诊审批只有 给出会诊意见并提交  没有通过不通过)
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

            case UserType.DOCTOR://医生  专家 审批不通过  (专家的会诊审批只有 给出会诊意见并提交  没有通过不通过)，此处只会是转诊
//                alertDialog.show();//以弹alertDialog的方式（专家审批不通过）
                Intent intent = new Intent(ApplyDiagnosisDetailActivity.this, DiagnosisDetailUnPassActivity.class);
                intent.putExtra("id", id);
                startActivity(intent);
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
                            v2.setVisibility(View.INVISIBLE);//GONE
                            v3.setVisibility(View.INVISIBLE);
                            break;
                        case 1:
                            v1.setVisibility(View.INVISIBLE);
                            v2.setVisibility(View.VISIBLE);
                            v3.setVisibility(View.INVISIBLE);
                            break;
                        case 2:
                            v1.setVisibility(View.INVISIBLE);
                            v2.setVisibility(View.INVISIBLE);
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
                    sendUnPassExpert(jisu, kangshengsu, kangguomin, waiyongyao, shoushu, other);
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
        WindowManager.LayoutParams p = alertDialog.getWindow().getAttributes();
        p.width = (int) (PhoneUtils.getPhoneWidth(this) * 0.8);
        p.width = (int) (PhoneUtils.getPhoneHeight(this) * 0.6);
        alertDialog.getWindow().setAttributes(p);     //设置生效
    }

    //专家审批转诊不通过
    private void sendUnPassExpert(String jisu, String kangshengsu, String kangguomin, String waiyongyao, String shoushu, String other) {
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("content", other);//其他  转诊详情不需要会诊意见
            jsonObject.put("medicineHormone", jisu);
            jsonObject.put("medicineAntibiotic", kangshengsu);
            jsonObject.put("medicineAntiallergic", kangguomin);
            jsonObject.put("medicineExternal", waiyongyao);
            jsonObject.put("operation", shoushu);
            showDialog();
            viewModel.sendUnPass_expert(jsonObject, id);
        } catch (Exception e) {
            e.printStackTrace();
        }
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}

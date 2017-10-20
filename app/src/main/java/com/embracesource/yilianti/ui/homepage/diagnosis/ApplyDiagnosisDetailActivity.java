package com.embracesource.yilianti.ui.homepage.diagnosis;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.embracesource.yilianti.R;
import com.embracesource.yilianti.bean.ApplyDiagnosisDetailBean;
import com.embracesource.yilianti.bean.SimpleBean;
import com.embracesource.yilianti.bean.eventbus.RefreshDiagnosisListBean;
import com.embracesource.yilianti.databinding.ActivityApplyDiagnosisDetailBinding;
import com.embracesource.yilianti.ui.base.AacBaseActivity;
import com.embracesource.yilianti.util.StringUtils;
import com.embracesource.yilianti.viewmodel.ApplyDiagnosisDetailCallBack;

import org.greenrobot.eventbus.EventBus;


//                        转诊会诊详情
//                        http://192.168.1.165:8002/referralAndConsultation/detail/{id}?flag={flag}
//                        flag：1 医务处审核，2 详情展示，3 专家回复

public class ApplyDiagnosisDetailActivity extends AacBaseActivity<ActivityApplyDiagnosisDetailBinding> implements ApplyDiagnosisDetailCallBack, View.OnClickListener {
    private ApplyDiagnosisDetailViewModel viewModel;
    private AlertDialog alertDialog;
    private int role;//登陆用户角色等级   1医生  2医务处  3医院  4 专家
    private int id;
    private EditText et_upPass_msg;

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
        if (true) {//// TODO: 2017/10/20 0020   不同角色  存储在偏好设置中
            binding.llExamine.setVisibility(View.VISIBLE);
            binding.btnExaminePass.setOnClickListener(this);
            binding.btnExamineUnpass.setOnClickListener(this);
            initShowUnPassAlertDialog();
        } else {
            binding.llExamine.setVisibility(View.GONE);
        }

    }

    @Override
    protected void initData() {
        super.initData();
        viewModel = new ApplyDiagnosisDetailViewModel(this);
        id = getIntent().getIntExtra(ApplyDiagnosisDetailActivity.class.getName(), 0);
        showDialog();
        viewModel.getDetail(id, "2");
    }

    @Override
    public void getApplyDiagnosisDetailOK(ApplyDiagnosisDetailBean applyDiagnosisDetailBean) {
        try {
            binding.setBean(applyDiagnosisDetailBean);
            binding.setItemBean(applyDiagnosisDetailBean.getData().getList().get(0));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_examine_pass:
                sendPass();
                break;
            case R.id.btn_examine_unpass:
                alertDialog.show();
                break;
        }
    }

    //审批通过
    private void sendPass() {
        showDialog();
        switch (role) {
            case 2://医务处
                viewModel.sendPass_2(id);
                break;
            case 3://医院
                viewModel.sendPass_3(id);
                break;
            case 4://专家
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
            case 2://医务处
                if (StringUtils.isNullorEmpty(msg)) {
                    showToast(getString(R.string.msg_input_unpass_reason));
                } else {
                    showDialog();
                    viewModel.sendUnPass_2(id, msg);
                }
                break;
            case 3://医院
                if (StringUtils.isNullorEmpty(msg)) {
                    showToast(getString(R.string.msg_input_unpass_reason));
                } else {
                    showDialog();
                    viewModel.sendUnPass_3(id, msg);
                }
                break;
            case 4://专家
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
        View alertView = View.inflate(this, R.layout.dialog_input, null);
        TextView tv_title = (TextView) alertView.findViewById(R.id.tv_title);
        et_upPass_msg = (EditText) alertView.findViewById(R.id.et_msg);
        alertView.findViewById(R.id.btn_sure).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendUnPass();
            }
        });
        alertView.findViewById(R.id.btn_cancle).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });
        tv_title.setText(getString(R.string.unpass_reason));

        AlertDialog.Builder build = new AlertDialog.Builder(this).setView(alertView);
        alertDialog = build.create();
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
}

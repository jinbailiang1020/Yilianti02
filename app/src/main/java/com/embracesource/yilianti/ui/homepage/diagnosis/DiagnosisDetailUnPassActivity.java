package com.embracesource.yilianti.ui.homepage.diagnosis;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.view.View;

import com.embracesource.yilianti.R;
import com.embracesource.yilianti.bean.SimpleBean;
import com.embracesource.yilianti.bean.eventbus.RefreshDiagnosisListBean;
import com.embracesource.yilianti.common.http.Api;
import com.embracesource.yilianti.databinding.ActivityDiagnosisDetailUnpassBinding;
import com.embracesource.yilianti.ui.base.AacBaseActivity;
import com.embracesource.yilianti.ui.base.BaseActivity;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONObject;

import io.reactivex.annotations.NonNull;

import static com.embracesource.yilianti.R.string.submit;

//转诊单审批通过（专家）
public class DiagnosisDetailUnPassActivity extends AacBaseActivity<ActivityDiagnosisDetailUnpassBinding> implements View.OnClickListener {


    private int id;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_diagnosis_detail_unpass;
    }//

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initData();
        initTitleLayout(getString(R.string.give_diagnosis_unpass_advice));
        setTitleRightVisiable(getString(submit), new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submit();
            }
        });
    }

    private void submit() {
        String jisu = binding.etJisu.getText().toString();
        String kangshengsu = binding.etKangshengsu.getText().toString();
        String kangguomin = binding.etKangguomin.getText().toString();
        String waiyongyao = binding.etWaiyongyao.getText().toString();
        String shoushu = binding.etShoushu.getText().toString();
        String other = binding.etOther.getText().toString();
        sendUnPassExpert(jisu, kangshengsu, kangguomin, waiyongyao, shoushu, other);
    }

    @Override
    protected void initView() {
        super.initView();
        binding.tabs.addTab( binding.tabs.newTab().setText("药物意见"), true);
        binding.tabs.addTab( binding.tabs.newTab().setText("手术意见"));
        binding.tabs.addTab( binding.tabs.newTab().setText("其他意见"));
        binding.tabs.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()) {
                    case 0:
                        binding.ll1.setVisibility(View.VISIBLE);
                        binding.ll2.setVisibility(View.INVISIBLE);//GONE
                        binding.ll3.setVisibility(View.INVISIBLE);
                        break;
                    case 1:
                        binding.ll1.setVisibility(View.INVISIBLE);
                        binding.ll2.setVisibility(View.VISIBLE);
                        binding.ll3.setVisibility(View.INVISIBLE);
                        break;
                    case 2:
                        binding.ll1.setVisibility(View.INVISIBLE);
                        binding.ll2.setVisibility(View.INVISIBLE);
                        binding.ll3.setVisibility(View.VISIBLE);
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
            new Api().diagnosisDetailSendUnPass_expert(jsonObject,id).subscribe(new BaseActivity.MyObserver<SimpleBean>() {
                @Override
                public void onNextUI(@NonNull SimpleBean bean) {
                    showToast(bean.getMessage());
                    if (bean.isSuccess()) {
                        EventBus.getDefault().post(new RefreshDiagnosisListBean(""));
                        startActivity(new Intent(DiagnosisDetailUnPassActivity.this,DiagnosisPictureActivity.class));
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
    }


    @Override
    public void onClick(View v) {

    }
}

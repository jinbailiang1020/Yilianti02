package com.embracesource.yilianti.ui.homepage.diagnosis;

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

import com.embracesource.yilianti.R;
import com.embracesource.yilianti.bean.ApplyDiagnosisGoalBean;
import com.embracesource.yilianti.common.dialog.DialogAdapter;
import com.embracesource.yilianti.databinding.ActivityApplyDiagnosis02Binding;
import com.embracesource.yilianti.ui.base.AacBaseActivity;
import com.embracesource.yilianti.util.PhoneUtils;
import com.embracesource.yilianti.viewmodel.ApplyDiagnosisViewModelCallBack;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ApplyDiagnosisActivity02 extends AacBaseActivity<ActivityApplyDiagnosis02Binding> implements View.OnClickListener, ApplyDiagnosisViewModelCallBack {

    private List<ApplyDiagnosisGoalBean.DataBean> goalList = new ArrayList<>();
    private List<ApplyDiagnosisGoalBean.DataBean> typeList = new ArrayList<>();
    private List<ApplyDiagnosisGoalBean.DataBean> teamList = new ArrayList<>();
    private List<ApplyDiagnosisGoalBean.DataBean> emergencyDegreeList = new ArrayList<>();
    private int currentClickItem;
    private int currentSelected_goal;
    private int currentSelected_type;
    private int currentSelected_team;
    private int currentSelected_emergencyDegree;

    private ApplyDiagnosisViewModel02 viewModel;

    private PopupWindow popupWindow;
    private DialogAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initTitleLayout(getString(R.string.diagnosis_detail));
        setTitleRightVisiable(getString(R.string.submit), new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//submit();

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
    }

    @Override
    protected void initData() {
        super.initData();
        viewModel = new ApplyDiagnosisViewModel02(this);
        LayoutInflater inflater = LayoutInflater.from(this);
        View view = inflater.inflate(R.layout.fragment_dialog_list, null, false);//container
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
                }
                popupWindow.dismiss();
            }
        });
        recyclerView.setAdapter(adapter);
        popupWindow = new PopupWindow(view, PhoneUtils.getPhoneWidth(this) * 2 / 3, LinearLayout.LayoutParams.WRAP_CONTENT, true);
        popupWindow.setBackgroundDrawable(new ColorDrawable(Color.GRAY));

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
                    showFragmentDialog();
                }

                break;
            case R.id.sp_type:
                if (typeList.isEmpty()) {
                    dialog.show();
                    viewModel.getBaseData("consultation_type");// consultation_type 会诊类型
                } else {
                    adapter.setList(typeList);
                    showFragmentDialog();
                }
                break;
            case R.id.sp_team:
                if (teamList.isEmpty()) {
                    dialog.show();
                    viewModel.getBaseData("priority");//priority 紧急程度
                } else {
                    adapter.setList(teamList);
                    showFragmentDialog();
                }
                break;
            case R.id.sp_emergency_degree:
                if (emergencyDegreeList.isEmpty()) {
                    dialog.show();
                    viewModel.getBaseData("medicare_type"); //medicare_type 医保类型
                } else {
                    adapter.setList(emergencyDegreeList);
                    showFragmentDialog();
                }
                break;
        }
    }

    private void showFragmentDialog() {
//        spinearFragmentDialog.show(getSupportFragmentManager(), "SpinearFragmentDialog");
        //创建PopupWindow实例，同时传入弹出窗口的显示高度和宽度以及是否设置焦点
        popupWindow.showAtLocation(this.getWindow().getDecorView(), Gravity.CENTER, 0, 0);
        //设置显示的动画
//        popupWindow.setAnimationStyle(R.style.popwin_anim_style);
    }


    @Override
    public void getBaseDataOK(ApplyDiagnosisGoalBean response) {
        dialog.show();
        switch (currentClickItem) {
            case R.id.sp_goal:
                goalList.clear();
                goalList.addAll(response.getData());
                break;
            case R.id.sp_type:
                typeList.clear();
                typeList.addAll(response.getData());
                break;
            case R.id.sp_team:
                teamList.clear();
                teamList.addAll(response.getData());
                break;
            case R.id.sp_emergency_degree:
                emergencyDegreeList.clear();
                emergencyDegreeList.addAll(response.getData());
                break;
        }
//        spinearFragmentDialog.setList(response.getData());
        adapter.setList(response.getData());
        showFragmentDialog();
    }

    //// TODO: 2017/10/16 0016
    private void submit() {
        ApplyDiagnosisGoalBean.DataBean item = goalList.get(currentSelected_goal);
        teamList.get(currentSelected_team);
        emergencyDegreeList.get(currentSelected_emergencyDegree);
        typeList.get(currentSelected_type);
    }
}

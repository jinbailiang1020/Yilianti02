package com.embracesource.yilianti.ui.homepage.diagnosis;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.embracesource.yilianti.R;
import com.embracesource.yilianti.bean.CustomerServiceDiagnosisListBean;
import com.embracesource.yilianti.bean.DiagnosisItemBean;
import com.embracesource.yilianti.bean.UserType;
import com.embracesource.yilianti.bean.enums.DiagnosisExaminationType;
import com.embracesource.yilianti.ui.base.BaseActivity;
import com.embracesource.yilianti.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

import me.zhouzhuo.zzhorizontalprogressbar.ZzHorizontalProgressBar;

/**
 * Created by Administrator on 2017/10/27 0027.
 */

public class DiagnosisPicAdapter extends RecyclerView.Adapter {

    private int role;
    private DiagnosisPictureViewModel viewModel;
    private List<Object> mList = new ArrayList<>();
    private OnItemClickListener onItemClickListener;
    private Context context;

    public DiagnosisPicAdapter(int role, DiagnosisPictureViewModel viewModel, Context context) {
        this.role = role;
        this.viewModel = viewModel;
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        int layout;
        if (role == UserType.Customer_Service) {//客服
            layout = R.layout.diagnosis_item_customer_service;
        } else {
            layout = R.layout.diagnosis_item;
        }

        return  new RecyclerView.ViewHolder(LayoutInflater.from(context).inflate(layout ,viewGroup,false)) {
            @Override
            public String toString() {
                return super.toString();
            }
        };
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, final int position) {

        if (role == UserType.Customer_Service) {//客服
            final CustomerServiceDiagnosisListBean.DataBean bean = (CustomerServiceDiagnosisListBean.DataBean) mList.get(position);
            ZzHorizontalProgressBar progressBar = (ZzHorizontalProgressBar) viewHolder.itemView.findViewById(R.id.progressBar);
            View tv1 = viewHolder.itemView.findViewById(R.id.tv1);
            View tv2 = viewHolder.itemView.findViewById(R.id.tv2);
            View tv3 = viewHolder.itemView.findViewById(R.id.tv3);
            View tv4 = viewHolder.itemView.findViewById(R.id.tv4);
            TextView tv_name = (TextView) viewHolder.itemView.findViewById(R.id.tv_name);
            Button btn = (Button) viewHolder.itemView.findViewById(R.id.next_step);
            tv_name.setText(bean.getPatientName());
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {//下一步
//                            操作
//                                    短信通知
//                            http://192.168.1.165:8002/workbench/medicalService/op/{id}?available=8
//                            医生就诊
//                            http://192.168.1.165:8002/workbench/medicalService/op/{id}?available=9
//                            就诊结果
//                            http://192.168.1.165:8002/workbench/medicalService/op/{id}?available=10
//                            平台随访
//                            http://192.168.1.165:8002/workbench/medicalService/op/{id}?available=11
                    ((BaseActivity)context).showDialog();
                    viewModel.nextStep(bean.getId(), bean.getAvailable()+1);
                }
            });

            GradientDrawable drawable = (GradientDrawable) btn.getBackground();
            drawable.setColor(context.getResources().getColor(R.color.green));
            btn.setEnabled(true);
            btn.setText(context.getString(R.string.next_step));
            switch (bean.getAvailable()) {
                case 6:
                    GradientDrawable drawable01 = (GradientDrawable) tv1.getBackground();
                    drawable01.setColor(context.getResources().getColor(R.color.color_a1a1a1));
                    GradientDrawable drawable02 = (GradientDrawable) tv2.getBackground();
                    drawable02.setColor(context.getResources().getColor(R.color.color_a1a1a1));
                    GradientDrawable drawable03 = (GradientDrawable) tv3.getBackground();
                    drawable03.setColor(context.getResources().getColor(R.color.color_a1a1a1));
                    GradientDrawable drawable04 = (GradientDrawable) tv4.getBackground();
                    drawable04.setColor(context.getResources().getColor(R.color.color_a1a1a1));
                    progressBar.setProgress(0);
                    break;
                case 8:
                    GradientDrawable drawable11 = (GradientDrawable) tv1.getBackground();
                    drawable11.setColor(context.getResources().getColor(R.color.green));
                    GradientDrawable drawable12 = (GradientDrawable) tv2.getBackground();
                    drawable12.setColor(context.getResources().getColor(R.color.color_a1a1a1));
                    GradientDrawable drawable13 = (GradientDrawable) tv3.getBackground();
                    drawable13.setColor(context.getResources().getColor(R.color.color_a1a1a1));
                    GradientDrawable drawable14 = (GradientDrawable) tv4.getBackground();
                    drawable14.setColor(context.getResources().getColor(R.color.color_a1a1a1));
                    progressBar.setProgress(1);
                    break;
                case 9:
                    GradientDrawable drawable21 = (GradientDrawable) tv1.getBackground();
                    drawable21.setColor(context.getResources().getColor(R.color.green));
                    GradientDrawable drawable22 = (GradientDrawable) tv2.getBackground();
                    drawable22.setColor(context.getResources().getColor(R.color.green));
                    GradientDrawable drawable23 = (GradientDrawable) tv3.getBackground();
                    drawable23.setColor(context.getResources().getColor(R.color.color_a1a1a1));
                    GradientDrawable drawable24 = (GradientDrawable) tv4.getBackground();
                    drawable24.setColor(context.getResources().getColor(R.color.color_a1a1a1));
                    progressBar.setProgress(3);
                    break;
                case 10:
                    GradientDrawable drawable31 = (GradientDrawable) tv1.getBackground();
                    drawable31.setColor(context.getResources().getColor(R.color.green));
                    GradientDrawable drawable32 = (GradientDrawable) tv2.getBackground();
                    drawable32.setColor(context.getResources().getColor(R.color.green));
                    GradientDrawable drawable33 = (GradientDrawable) tv3.getBackground();
                    drawable33.setColor(context.getResources().getColor(R.color.green));
                    GradientDrawable drawable34 = (GradientDrawable) tv4.getBackground();
                    drawable34.setColor(context.getResources().getColor(R.color.color_a1a1a1));
                    progressBar.setProgress(5);
                    break;
                case 11:
                    GradientDrawable drawable41 = (GradientDrawable) tv1.getBackground();
                    drawable41.setColor(context.getResources().getColor(R.color.green));
                    GradientDrawable drawable42 = (GradientDrawable) tv2.getBackground();
                    drawable42.setColor(context.getResources().getColor(R.color.green));
                    GradientDrawable drawable43 = (GradientDrawable) tv3.getBackground();
                    drawable43.setColor(context.getResources().getColor(R.color.green));
                    GradientDrawable drawable44 = (GradientDrawable) tv4.getBackground();
                    drawable44.setColor(context.getResources().getColor(R.color.green));
                    progressBar.setProgress(8);

                    GradientDrawable drawable5 = (GradientDrawable) btn.getBackground();
                    drawable5.setColor(context.getResources().getColor(R.color.color_a1a1a1));
                    btn.setEnabled(false);
                    btn.setText(context.getString(R.string.finish));
                    break;
                default:
                    GradientDrawable drawableD = (GradientDrawable) btn.getBackground();
                    drawableD.setColor(context.getResources().getColor(R.color.color_a1a1a1));
                    btn.setEnabled(false);
                    btn.setText(context.getString(R.string.finish));
                    break;
            }
        } else {//非客服
            final DiagnosisItemBean entity = (DiagnosisItemBean) mList.get(position);
            TextView tv_name = (TextView) viewHolder.itemView.findViewById(R.id.tv_name);
            TextView tv_status = (TextView) viewHolder.itemView.findViewById(R.id.tv_status);
            TextView tv_content = (TextView) viewHolder.itemView.findViewById(R.id.tv_content);
            TextView tv_date = (TextView) viewHolder.itemView.findViewById(R.id.tv_date);
            switch (role) {
                case UserType.DOCTOR://医生
                    tv_name.setText(entity.getPatientName());
                    break;
                case UserType.Medical_Service://医务处，上级医院
                    tv_name.setText(entity.getChiefComplaint());
                    break;
                case UserType.Customer_Service://客服
                    tv_name.setText(entity.getPatientName());
                    break;
            }
            String type = DiagnosisExaminationType.getDiagnosisExaminationTypeString(entity.getAvailable());
            tv_status.setText(type);
            if (StringUtils.isNullorEmpty(entity.getIllnessDescription())) {
                tv_content.setVisibility(View.GONE);
            } else {
                tv_content.setVisibility(View.VISIBLE);
            }
            tv_content.setText(entity.getIllnessDescription());
            tv_date.setText(entity.getCreatedTime());
            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.onItemClickListener(v, position,entity);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public void setDatas(List<DiagnosisItemBean> mList) {
        this.mList.clear();
        this.mList.addAll(mList);
        notifyDataSetChanged();
    }


    public void addDatas(List<DiagnosisItemBean> mList) {
        this.mList.addAll(mList);
        notifyDataSetChanged();
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public void setDatas_CustomerServiceDiagnosisListBean(List<CustomerServiceDiagnosisListBean.DataBean> mList) {
        this.mList.clear();
        this.mList.addAll(mList);
        notifyDataSetChanged();
    }

    public interface OnItemClickListener {
        void onItemClickListener(View view, int position,Object obj);
    }
}

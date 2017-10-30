package com.embracesource.yilianti.ui.homepage.needhandle;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.embracesource.yilianti.R;
import com.embracesource.yilianti.bean.CustomerServiceDiagnosisListBean;
import com.embracesource.yilianti.bean.DiagnosisItemBean;
import com.embracesource.yilianti.bean.UserType;
import com.embracesource.yilianti.bean.enums.DiagnosisExaminationType;
import com.embracesource.yilianti.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/10/27 0027.
 */

public class NeedHandleAdapter extends RecyclerView.Adapter {

    private int role;
    private NeedHandleViewModel viewModel;
    private List<Object> mList = new ArrayList<>();
    private OnItemClickListener onItemClickListener;
    private Context context;

    public NeedHandleAdapter(int role, NeedHandleViewModel viewModel, Context context) {
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

        return new RecyclerView.ViewHolder(LayoutInflater.from(context).inflate(layout, viewGroup, false)) {
            @Override
            public String toString() {
                return super.toString();
            }
        };
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, final int position) {

        final DiagnosisItemBean entity = (DiagnosisItemBean) mList.get(position);
        TextView tv_name = (TextView) viewHolder.itemView.findViewById(R.id.tv_name);
        TextView tv_status = (TextView) viewHolder.itemView.findViewById(R.id.tv_status);
        TextView tv_content = (TextView) viewHolder.itemView.findViewById(R.id.tv_content);
        TextView tv_date = (TextView) viewHolder.itemView.findViewById(R.id.tv_date);
        tv_name.setText(entity.getChiefComplaint());
 /*       switch (role) {
            case UserType.DOCTOR://医生
                tv_name.setText(entity.getPatientName());
                break;
            case UserType.Medical_Service://医务处，上级医院
                tv_name.setText(entity.getChiefComplaint());
                break;
            case UserType.Customer_Service://客服
//                tv_name.setText(entity.getPatientName());
//                useless
                break;
        }*/
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
                onItemClickListener.onItemClickListener(v, position, entity);
            }
        });
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
        void onItemClickListener(View view, int position, Object obj);
    }
}

package com.embracesource.yilianti.common.dialog;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.embracesource.yilianti.R;
import com.embracesource.yilianti.bean.ApplyDiagnosisGoalBean;
import com.embracesource.yilianti.bean.DiagnosisTeamBean;
import com.embracesource.yilianti.bean.DoctorBean;
import com.embracesource.yilianti.bean.HospitalBean;

import java.util.List;

/**
 * Created by Administrator on 2017/10/16 0016.
 */

public class DialogAdapter extends RecyclerView.Adapter<DialogAdapter.DialogViewHolder> {
    public void setPopwindowOnClickListener(PopwindowOnClickListener popwindowOnClickListener) {
        this.popwindowOnClickListener = popwindowOnClickListener;
    }

    private PopwindowOnClickListener popwindowOnClickListener;

    public void setList(List list) {
        this.list = list;
        notifyDataSetChanged();
    }

    private List<Object> list;
    private Context context;

    public DialogAdapter(List<Object> list, Context context) {
        this.list = list;
        this.context = context;
    }

    public DialogAdapter(Context context) {
        this.context = context;
    }

    @Override
    public DialogAdapter.DialogViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        LayoutInflater inf = LayoutInflater.from(context);
        return new DialogAdapter.DialogViewHolder(inf.inflate(R.layout.item_textview, null));//viewGroup 会报错
    }

    @Override
    public void onBindViewHolder(DialogAdapter.DialogViewHolder viewHolder, final int position) {
        Object entity = list.get(position);
        TextView tv = (TextView) viewHolder.itemView.findViewById(R.id.tv);

        if (entity instanceof ApplyDiagnosisGoalBean.DataBean) {
            tv.setText(((ApplyDiagnosisGoalBean.DataBean) entity).getDescription());
        } else if (entity instanceof HospitalBean.DataBean.ListBean) {
            tv.setText(((HospitalBean.DataBean.ListBean) entity).getDescription());
        } else if (entity instanceof DoctorBean.DataBean) {
            tv.setText(((DoctorBean.DataBean) entity).getFullname());
        } else if (entity instanceof DiagnosisTeamBean.DataBean) {
            tv.setText(((DiagnosisTeamBean.DataBean) entity).getDescription());
        }

        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popwindowOnClickListener.onClick(v, position);

            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    static class DialogViewHolder extends RecyclerView.ViewHolder {

        public DialogViewHolder(View itemView) {
            super(itemView);
        }
    }

    public interface PopwindowOnClickListener {
        void onClick(View v, int position);
    }
}

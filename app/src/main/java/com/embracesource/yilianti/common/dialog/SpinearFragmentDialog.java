package com.embracesource.yilianti.common.dialog;

import android.app.Dialog;
import android.arch.lifecycle.ViewModel;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.embracesource.yilianti.R;
import com.embracesource.yilianti.bean.ApplyDiagnosisGoalBean;
import com.embracesource.yilianti.util.PhoneUtils;

import java.util.List;

/**
 * Created by Administrator on 2017/10/16 0016.
 */

public class SpinearFragmentDialog extends DialogFragment {

    private Context context;
    private List<ApplyDiagnosisGoalBean.DataBean> list;
    private DialogAdapter adapter;

    public void setList(List<ApplyDiagnosisGoalBean.DataBean> list) {
        this.list = list;
        adapter.setList(list);
    }

    public SpinearFragmentDialog(Context context) {
        this.context = context;
        adapter = new DialogAdapter(list, context);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);//去掉标题

//        WindowManager.LayoutParams attributes = getDialog().getWindow().getAttributes();
//        attributes.width = PhoneUtils.getPhoneWidth(context)/3;
//        attributes.height = PhoneUtils.getPhoneHeight(context)*3/5;
//        getDialog().getWindow().setAttributes(attributes);

//        setStyle(DialogFragment.STYLE_NO_TITLE, android.R.style.Theme_Holo_Light_Dialog_MinWidth);
        View view = inflater.inflate(R.layout.fragment_dialog_list, container, false);//container
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
//    <!--关键点1-->
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        super.onActivityCreated(savedInstanceState);
//    <!--关键点2-->
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(0x00000000));
        getDialog().getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
    }


//    @Override
//    public void onStart() {
//        super.onStart();
//        Dialog dialog = getDialog();
//        if (dialog != null) {
//            DisplayMetrics dm = new DisplayMetrics();
//            //设置弹框的占屏宽        getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
//            dialog.getWindow().setLayout((int) (dm.widthPixels * 0.8), ViewGroup.LayoutParams.WRAP_CONTENT);
//        }
//    }


    static class DialogAdapter extends RecyclerView.Adapter<DialogViewHolder> {
        public void setList(List<ApplyDiagnosisGoalBean.DataBean> list) {
            this.list = list;
            notifyDataSetChanged();
        }

        private List<ApplyDiagnosisGoalBean.DataBean> list;
        private Context context;

        public DialogAdapter(List<ApplyDiagnosisGoalBean.DataBean> list, Context context) {
            this.list = list;
            this.context = context;
        }

        @Override
        public DialogViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            LayoutInflater inf = LayoutInflater.from(context);
            return new DialogViewHolder(inf.inflate(R.layout.item_textview, null));//viewGroup 会报错
        }

        @Override
        public void onBindViewHolder(DialogViewHolder viewHolder, int i) {
            ((TextView) viewHolder.itemView.findViewById(R.id.tv)).setText(list.get(i).getDescription());
        }

        @Override
        public int getItemCount() {
            return list.size();
        }
    }

    static class DialogViewHolder extends RecyclerView.ViewHolder {

        public DialogViewHolder(View itemView) {
            super(itemView);
        }
    }

}

package com.embracesource.yilianti.ui.homepage.diagnosis;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.Toast;

import com.bigkoo.pickerview.OptionsPickerView;
import com.embracesource.yilianti.R;
import com.embracesource.yilianti.common.imagepicker.PicassoImageLoader;
import com.embracesource.yilianti.common.imagepicker.SelectDialog;
import com.embracesource.yilianti.common.pickerview.JsonBean;
import com.embracesource.yilianti.databinding.ActivityApplyDiagnosis01Binding;
import com.embracesource.yilianti.databinding.ActivityApplyDiagnosis02Binding;
import com.embracesource.yilianti.ui.base.AacBaseActivity;
import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.bean.ImageItem;
import com.lzy.imagepicker.ui.ImageGridActivity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.schedulers.Schedulers;

public class ApplyDiagnosisActivity02 extends AacBaseActivity<ActivityApplyDiagnosis02Binding> implements View.OnClickListener {

    private static final int IMAGE_PICKER = 1004;//1004b不能改
    private ArrayList<JsonBean> options1Items = new ArrayList<>();
    private ArrayList<ArrayList<String>> options2Items = new ArrayList<>();
    private ArrayList<ArrayList<ArrayList<String>>> options3Items = new ArrayList<>();

    private int currentSelectPhotoTypeId;//选择的照片类型分下面三种：：身份证正面 身份证反面 患处图片  通过viewId识别
    //    private static final int ID_CARD_01 = 101;//身份证正面
//    private static final int ID_CARD_02 = 102;//身份证反面
//    private static final int SYMPTOMS  =103;//患处图片

    private boolean isLoaded;
    private PicassoImageLoader imageLoader;

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
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_apply_diagnosis02;//
    }


    @Override
    public void onClick(View v) {

    }
}

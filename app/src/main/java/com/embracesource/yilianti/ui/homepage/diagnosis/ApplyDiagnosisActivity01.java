package com.embracesource.yilianti.ui.homepage.diagnosis;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.Toast;

import com.bigkoo.pickerview.OptionsPickerView;
import com.embracesource.yilianti.R;
import com.embracesource.yilianti.bean.ApplyDiagnosisRequestBean;
import com.embracesource.yilianti.common.imagepicker.PicassoImageLoader;
import com.embracesource.yilianti.common.imagepicker.SelectDialog;
import com.embracesource.yilianti.common.pickerview.JsonBean;
import com.embracesource.yilianti.databinding.ActivityApplyDiagnosis01Binding;
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

public class ApplyDiagnosisActivity01 extends AacBaseActivity<ActivityApplyDiagnosis01Binding> implements View.OnClickListener {

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
    private ApplyDiagnosisRequestBean bean = new ApplyDiagnosisRequestBean();
    //    @Inject
    ApplyDiagnosisViewModel01 ApplyDiagnosisViewModel01;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_apply_diagnosis01;//
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        DaggerAppComponent.builder().appModule(new AppModule()).build().inject(this); //dragger注入
        initTitleLayout(getString(R.string.diagnosis_detail));
        setTitleRightVisiable(getString(R.string.next_step), new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nextStep();
            }
        });
        ApplyDiagnosisViewModel01 = new ApplyDiagnosisViewModel01();
        initView();
    }

    private void nextStep() {
        String preliminaryDiagnosis = binding.etPreliminaryDiagnosis.getText().toString();
        String chiefComplaint = binding.etChiefComplaint.getText().toString();//非必填
        String illnessDesc = binding.etIllnessDesc.getText().toString();
        String patientInfo = binding.etPatientInfo.getText().toString();
        String idcardNumber = binding.etIdcardNumber.getText().toString();
        String name = binding.etName.getText().toString();
        String age = binding.etAge.getText().toString();
        String jiguan = binding.etJiguan.getText().toString();
        String phone = binding.etPhone.getText().toString();
//// TODO: 2017/10/17 0017  //还剩医保类型：
        String msg0 = "请填写";
        String msg = "";
/*        if (preliminaryDiagnosis.isEmpty()) {
            msg = "初步诊断";
            showToast( msg0+msg);
            return;
        }
        if (illnessDesc.isEmpty()) {
            msg = "病情描述";
            showToast( msg0+msg);
            return;
        }
        if (patientInfo.isEmpty()) {
            msg = "患者信息";
            showToast( msg0+msg);
            return;
        }
        if (idcardNumber.isEmpty()) {
            msg = "身份证号";
            showToast( msg0+msg);
            return;
        }
        if (name.isEmpty()) {
            msg = "名字";
            showToast( msg0+msg);
            return;
        }
        if (age.isEmpty()) {
            msg = "年龄";
            showToast( msg0+msg);
            return;
        }
        if (jiguan.isEmpty()) {
            msg = "籍贯";
            showToast( msg0+msg);
            return;
        }
        if (phone.isEmpty()) {
            msg = "手机号";
            showToast( msg0+msg);
            return;
        }*/

//       //todo// if(医保类型)
        int sex = 0;
        if (binding.rbMan.isChecked()) {// 性别
            sex = 0;//男
        } else {
            sex = 1;//    女
        }
        bean.setConsultationReferral(new ApplyDiagnosisRequestBean.ConsultationReferralBean());
        bean.setPatientIllnessBasicinfo(new ApplyDiagnosisRequestBean.PatientIllnessBasicinfoBean());
        bean.setPatientInfo(new ApplyDiagnosisRequestBean.PatientInfoBean());

        bean.getPatientIllnessBasicinfo().setPreliminaryDiagnosis(preliminaryDiagnosis);//初步诊断
        bean.getPatientIllnessBasicinfo().setChiefComplaint(chiefComplaint);
        bean.getPatientIllnessBasicinfo().setIllnessDescription(illnessDesc);

//        bean.getConsultationReferral().setConsultationObjective();//目的// 下一步
//        bean.getConsultationReferral().setConsultationType();//咨询类型//
//        bean.getConsultationReferral().setPriority();//优先//
//        bean.getConsultationReferral().setSaveKey();//
//        bean.getConsultationReferral().setTeam();//
//        bean.getConsultationReferral().setType();//

        if (age.isEmpty()) {
            age = "0";
        }
        bean.getPatientInfo().setAge(Integer.valueOf(age));
        bean.getPatientInfo().setFullname(name);
        bean.getPatientInfo().setIdnumber(idcardNumber);
//        bean.getPatientInfo().setMedicareType();//医疗保险类型、、// TODO: 2017/10/17 0017
        bean.getPatientInfo().setNativeplace(jiguan);
        bean.getPatientInfo().setPhonenum(phone);
        bean.getPatientInfo().setSex(sex);
        //patientInfo??

        Intent intent = new Intent(ApplyDiagnosisActivity01.this, ApplyDiagnosisActivity02.class);
        intent.putExtra(ApplyDiagnosisRequestBean.class.getName(), bean);
        startActivity(intent);

    }

    @Override
    protected void initView() {
        super.initView();
        imageLoader = new PicassoImageLoader();
        binding.rbMan.setChecked(true);
        ApplyDiagnosisViewModel01.initBaseData(this)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MyObserver<List>() {
                    @Override
                    public void onNext(@NonNull List list) {
                        options1Items.clear();
                        options1Items.addAll((Collection<? extends JsonBean>) list.get(0));
                        options2Items.clear();
                        options2Items.addAll((Collection<? extends ArrayList<String>>) list.get(1));
                        options3Items.clear();
                        options3Items.addAll((Collection<? extends ArrayList<ArrayList<String>>>) list.get(2));
                        isLoaded = true;
                    }
                });

        ApplyDiagnosisViewModel01.initImagePicker(imageLoader);

        binding.ivSelectIdcard01.setOnClickListener(this);
        binding.ivSelectIdcard02.setOnClickListener(this);
        binding.ivSymptoms01.setOnClickListener(this);
        binding.ivSymptoms02.setOnClickListener(this);
        binding.etJiguan.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_select_idcard01:
            case R.id.iv_select_idcard02:
            case R.id.iv_Symptoms01:
            case R.id.iv_Symptoms02:
                showSelectPhotoDialog(v.getId());
                break;

            case R.id.et_jiguan:
                if (isLoaded) {
                    ShowPickerView();
                } else {
                    showToast("数据未加载");
                }
                break;
        }
    }

    private void ShowPickerView() {// 弹出选择器
        OptionsPickerView pvOptions = new OptionsPickerView.Builder(this, new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                //返回的分别是三个级别的选中位置
                String tx = options1Items.get(options1).getPickerViewText() +
                        options2Items.get(options1).get(options2) +
                        options3Items.get(options1).get(options2).get(options3);

                binding.etJiguan.setText(options1Items.get(options1).getPickerViewText() +
                        options2Items.get(options1).get(options2));
//                Toast.makeText(ApplyDiagnosisActivity01.this, tx, Toast.LENGTH_SHORT).show();
            }
        })
                .setTitleText("选择籍贯")
                .setDividerColor(Color.BLACK)
                .setTextColorCenter(Color.BLACK) //设置选中项文字颜色
                .setContentTextSize(20)
                .build();
//        pvOptions.setPicker(options1Items);//一级选择器
        pvOptions.setPicker(options1Items, options2Items);//二级选择器
//        pvOptions.setPicker(options1Items, options2Items,options3Items);//三级选择器
        pvOptions.show();
    }

    private void showSelectPhotoDialog(int typeId) {
        currentSelectPhotoTypeId = typeId;
        List<String> names = new ArrayList<>();
        names.add("拍照");
        names.add("相册");
        showDialog(new SelectDialog.SelectDialogListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0: // 直接调起相机
                        /**
                         * 0.4.7 目前直接调起相机不支持裁剪，如果开启裁剪后不会返回图片，请注意，后续版本会解决
                         * 但是当前直接依赖的版本已经解决，考虑到版本改动很少，所以这次没有上传到远程仓库
                         * 如果实在有所需要，请直接下载源码引用。
                         */
                        //打开选择,本次允许选择的数量
//                        ImagePicker.getInstance().setSelectLimit(maxImgCount - selImageList.size());
                        Intent intent = new Intent(ApplyDiagnosisActivity01.this, ImageGridActivity.class);
                        intent.putExtra(ImageGridActivity.EXTRAS_TAKE_PICKERS, true); // 是否是直接打开相机
                        startActivityForResult(intent, ImagePicker.RESULT_CODE_ITEMS);
                        break;
                    case 1:
                        //打开选择,本次允许选择的数量
//                        ImagePicker.getInstance().setSelectLimit(maxImgCount - selImageList.size());
                        Intent intent1 = new Intent(ApplyDiagnosisActivity01.this, ImageGridActivity.class);
                                /* 如果需要进入选择的时候显示已经选中的图片，
                                 * 详情请查看ImagePickerActivity
                                 * */
//                                intent1.putExtra(ImageGridActivity.EXTRAS_IMAGES,images);
                        startActivityForResult(intent1, ImagePicker.RESULT_CODE_ITEMS);
                        break;
                    default:
                        break;
                }

            }
        }, names);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == ImagePicker.RESULT_CODE_ITEMS) {
            if (data != null && requestCode == IMAGE_PICKER) {
                ArrayList<ImageItem> images = (ArrayList<ImageItem>) data.getSerializableExtra(ImagePicker.EXTRA_RESULT_ITEMS);
//                MyAdapter adapter = new MyAdapter(images);
//                gridView.setAdapter(adapter);
                if (images == null || images.size() == 0) {
                    Toast.makeText(this, "没有数据", Toast.LENGTH_SHORT).show();
                } else {
                    imageLoader.displayImage(ApplyDiagnosisActivity01.this,
                            images.get(0).path,
                            (ImageView) findViewById(currentSelectPhotoTypeId),
                            binding.ivSelectIdcard01.getMeasuredWidth(),
                            binding.ivSelectIdcard01.getMeasuredWidth());

                }

            } else {
                Toast.makeText(this, "没有数据", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private SelectDialog showDialog(SelectDialog.SelectDialogListener listener, List<String> names) {
        SelectDialog dialog = new SelectDialog(this, R.style
                .transparentFrameWindowStyle,
                listener, names);
        if (!this.isFinishing()) {
            dialog.show();
        }
        return dialog;
    }

}

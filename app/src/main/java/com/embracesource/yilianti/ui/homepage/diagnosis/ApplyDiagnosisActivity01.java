package com.embracesource.yilianti.ui.homepage.diagnosis;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.pickerview.OptionsPickerView;
import com.bumptech.glide.Glide;
import com.embracesource.yilianti.R;
import com.embracesource.yilianti.bean.ApplyDiagnosisGoalBean;
import com.embracesource.yilianti.bean.ApplyDiagnosisRequestBean;
import com.embracesource.yilianti.common.adapter.CommonAdapter;
import com.embracesource.yilianti.common.adapter.MultiItemTypeAdapter;
import com.embracesource.yilianti.common.adapter.ViewHolder;
import com.embracesource.yilianti.common.dialog.DialogAdapter;
import com.embracesource.yilianti.common.http.RetrofitConfig;
import com.embracesource.yilianti.common.imagepicker.PicassoImageLoader;
import com.embracesource.yilianti.common.imagepicker.SelectDialog;
import com.embracesource.yilianti.common.pickerview.JsonBean;
import com.embracesource.yilianti.databinding.ActivityApplyDiagnosis01Binding;
import com.embracesource.yilianti.ui.base.AacBaseActivity;
import com.embracesource.yilianti.util.PhoneUtils;
import com.embracesource.yilianti.viewmodel.ApplyDiagnosis01CallBack;
import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.bean.ImageItem;
import com.lzy.imagepicker.ui.ImageGridActivity;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.convert.StringConvert;
import com.lzy.okgo.model.Progress;
import com.lzy.okgo.request.PostRequest;
import com.lzy.okserver.OkUpload;
import com.lzy.okserver.upload.UploadListener;
import com.lzy.okserver.upload.UploadTask;
import com.orhanobut.logger.Logger;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.schedulers.Schedulers;

import static android.R.attr.path;

public class ApplyDiagnosisActivity01 extends AacBaseActivity<ActivityApplyDiagnosis01Binding> implements View.OnClickListener, ApplyDiagnosis01CallBack {

    private static final int IMAGE_PICKER = 1004;//1004b不能改
    private ArrayList<JsonBean> options1Items = new ArrayList<>();
    private ArrayList<ArrayList<String>> options2Items = new ArrayList<>();
    private ArrayList<ArrayList<ArrayList<String>>> options3Items = new ArrayList<>();
    private int currentSelectPhotoTypeId;//选择的照片类型分下面三种：：身份证正面 身份证反面 患处图片  通过viewId识别
    //    private static final int ID_CARD_01 = 101;//身份证正面
//    private static final int ID_CARD_02 = 102;//身份证反面
//    private static final int SYMPTOMS  =103;//患处图片
    private PopupWindow popupWindow;
    private boolean isLoaded;
    private PicassoImageLoader imageLoader;
    private ApplyDiagnosisRequestBean bean = new ApplyDiagnosisRequestBean();
    //    @Inject
    ApplyDiagnosisViewModel01 viewModel;
    private DialogAdapter adapter;
    private TextView popTitle;
    private int currentSelected_medicalInsuranceType = -1;//当前选择的医保类型位置

    private List<ApplyDiagnosisGoalBean.DataBean> medicalInsuranceTypeList = new ArrayList<>();//医保类型数据
    private List<ImageItem> symptomsList = new ArrayList<>();//患处图片集合
    private CommonAdapter symptomsAdapter;
    public static final int Max_Pic_Size = 9;

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
        viewModel = new ApplyDiagnosisViewModel01(this);
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

        String msg0 = "请填写";
        String msg = "";
        if (preliminaryDiagnosis.isEmpty()) {
            msg = "初步诊断";
            showToast(msg0 + msg);
            return;
        }
        if (illnessDesc.isEmpty()) {
            msg = "病情描述";
            showToast(msg0 + msg);
            return;
        }
        if (patientInfo.isEmpty()) {
            msg = "患者信息";
            showToast(msg0 + msg);
            return;
        }
        if (idcardNumber.isEmpty()) {
            msg = "身份证号";
            showToast(msg0 + msg);
            return;
        }
        if (name.isEmpty()) {
            msg = "名字";
            showToast(msg0 + msg);
            return;
        }
        if (age.isEmpty()) {
            msg = "年龄";
            showToast(msg0 + msg);
            return;
        }
        if (jiguan.isEmpty()) {
            msg = "籍贯";
            showToast(msg0 + msg);
            return;
        }
        if (phone.isEmpty()) {
            msg = "手机号";
            showToast(msg0 + msg);
            return;
        }
        if (medicalInsuranceTypeList.size() > 0 && currentSelected_medicalInsuranceType >= 0) {
        } else {
            msg = "医保类型";
            showToast(msg0 + msg);
            return;
        }

        int sex = 0;
        if (binding.rbMan.isChecked()) {// 性别
            sex = 1;//男
        } else {
            sex = 0;//    女
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
        if (medicalInsuranceTypeList != null && !medicalInsuranceTypeList.isEmpty())
            bean.getPatientInfo().setMedicareType(medicalInsuranceTypeList.get(currentSelected_medicalInsuranceType).getId());//医疗保险类型
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
        initPopWindow();
        imageLoader = new PicassoImageLoader();
        binding.rbMan.setChecked(true);
        viewModel.initBaseData(this)
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

        viewModel.initImagePicker(imageLoader);

        binding.ivSelectIdcard01.setOnClickListener(this);
        binding.ivSelectIdcard02.setOnClickListener(this);
        binding.ivSymptoms01.setOnClickListener(this);
        binding.ivSymptoms02.setOnClickListener(this);
        binding.etJiguan.setOnClickListener(this);
        binding.spMedicalInsuranceType.setOnClickListener(this);

        //患处图片adapter
        symptomsAdapter = new CommonAdapter(mContext, R.layout.image_item, symptomsList) {
            @Override
            protected void convert(ViewHolder holder, Object o, int position) {
                ImageItem entity = (ImageItem) o;
                imageLoader.displayImage(ApplyDiagnosisActivity01.this,
                        entity.path,
                        (ImageView) holder.itemView.findViewById(R.id.iv),
                        binding.ivSelectIdcard01.getMeasuredWidth(),
                        binding.ivSelectIdcard01.getMeasuredWidth());
            }
        };
        symptomsAdapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {

            }

            @Override
            public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
                symptomsList.remove(position);
                symptomsAdapter.notifyDataSetChanged();
                return false;
            }
        });
        binding.recyclerView.setAdapter(symptomsAdapter);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_select_idcard01:
            case R.id.iv_select_idcard02:
                viewModel.initImagePicker(imageLoader);
                showSelectPhotoDialog(v.getId());
                break;
            case R.id.iv_Symptoms01:
            case R.id.iv_Symptoms02:
                viewModel.initImagePicker_Mult(imageLoader);
                showSelectPhotoDialog(v.getId());
                break;

            case R.id.et_jiguan:
                if (isLoaded) {
                    ShowPickerView();
                } else {
                    showToast("数据未加载");
                }
                break;
            case R.id.sp_medical_insurance_type://医保类型
                if (medicalInsuranceTypeList.isEmpty()) {
                    showDialog();
                    viewModel.getBaseData("medicare_type"); //medicare_type 医保类型
                } else {
                    adapter.setList(medicalInsuranceTypeList);
                    showFragmentDialog(medicalInsuranceTypeList);
                }
                break;
        }
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
                currentSelected_medicalInsuranceType = position;
                binding.spMedicalInsuranceType.setText(medicalInsuranceTypeList.get(position).getDescription());
                popupWindow.dismiss();
            }
        });
        recyclerView.setAdapter(adapter);
        popupWindow = new PopupWindow(view, PhoneUtils.getPhoneWidth(this) * 2 / 3, LinearLayout.LayoutParams.WRAP_CONTENT, true);
        popupWindow.setBackgroundDrawable(new ColorDrawable(Color.GRAY));
    }

    private void showFragmentDialog(List<ApplyDiagnosisGoalBean.DataBean> data) {
        if (data != null && !data.isEmpty())
            popTitle.setText(data.get(0).getMark());
        popupWindow.showAtLocation(this.getWindow().getDecorView(), Gravity.CENTER, 0, 0);
//        popupWindow.setAnimationStyle(R.style.popwin_anim_style);
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
                final ArrayList<ImageItem> images = (ArrayList<ImageItem>) data.getSerializableExtra(ImagePicker.EXTRA_RESULT_ITEMS);
//                MyAdapter adapter = new MyAdapter(images);
//                gridView.setAdapter(adapter);
                if (images == null || images.size() == 0) {
                    Toast.makeText(this, "没有数据", Toast.LENGTH_SHORT).show();
                } else {
                    for (int i = 0; i < images.size(); i++) {
                        uploadFile(images.get(i));
                    }
                }
            } else {
                Toast.makeText(this, "没有数据", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void uploadFile(final ImageItem imageItem) {
        PostRequest<String> postRequest = OkGo.<String>post(RetrofitConfig.BASE_URL + "referralAndConsultation/upload")//
//                .headers("aaa", "111")//
                .params("bbb", "222")//
                .params("saveKey", "7a890690-52f7-454d-886f-7a71e12b107e")
                .params("type", "2")
                .params("file", path)
                .params("submit", "上传")//" enctype="multipart/form-data"
                .converter(new StringConvert());
        final UploadTask<String> task = OkUpload.request(imageItem.path, postRequest)//
                .priority(new Random().nextInt(100))//
                .extra1(imageItem)//
                .save();

        task.register(new UploadListener<String>("tag") {
            @Override
            public void onStart(Progress progress) {
                Logger.d("HAHA onStart" + progress);
            }

            @Override
            public void onProgress(Progress progress) {
                Logger.d("HAHA onProgress" + progress);
            }

            @Override
            public void onError(Progress progress) {
                Logger.d("HAHA onError" + progress);
                task.remove();
            }

            @Override
            public void onFinish(String s, Progress progress) {
                // HAHA onFinish:{"code":"0000","message":"type参数不正确","data":null,"traceInfo":["0026"],"sessionid":null,"success":false,"fail":true}
                Logger.d("HAHA onFinish:" + s + " ; " + progress);
                try {
                    JSONObject jsonObject = new JSONObject(s);
                    boolean success = jsonObject.getBoolean("success");
                    String msg = jsonObject.getString("message");
//                    if (success) {
                    if (true) { //// TODO: 2017/10/19 0019
                        if (currentSelectPhotoTypeId == R.id.iv_Symptoms01) {//患处图片，多选
                            if(symptomsList.size()>Max_Pic_Size){
                                showToast(getString(R.string.Max_Pic_Size));
                                return;
                            }
                            symptomsList.add(imageItem);
                            symptomsAdapter.notifyDataSetChanged();
                        } else {
                            imageLoader.displayImage(ApplyDiagnosisActivity01.this,
                                    imageItem.path,
                                    (ImageView) findViewById(currentSelectPhotoTypeId),
                                    binding.ivSelectIdcard01.getMeasuredWidth(),
                                    binding.ivSelectIdcard01.getMeasuredWidth());
                        }
                    } else {
                        showToast(msg);
                        Glide.with(ApplyDiagnosisActivity01.this)//
                                .load(R.drawable.default_image)
                                .centerCrop()//
                                .into((ImageView) findViewById(currentSelectPhotoTypeId));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                task.remove();
            }

            @Override
            public void onRemove(Progress progress) {

            }
        });
        task.start();
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

    @Override
    public void getBaseDataOK(ApplyDiagnosisGoalBean response) {
        medicalInsuranceTypeList.clear();
        medicalInsuranceTypeList.addAll(response.getData());
        adapter.setList(response.getData());
        showFragmentDialog(response.getData());
    }
}

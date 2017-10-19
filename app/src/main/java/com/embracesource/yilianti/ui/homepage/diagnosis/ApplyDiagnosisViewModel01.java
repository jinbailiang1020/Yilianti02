package com.embracesource.yilianti.ui.homepage.diagnosis;

import android.content.Context;

import com.embracesource.yilianti.bean.ApplyDiagnosisGoalBean;
import com.embracesource.yilianti.common.imagepicker.PicassoImageLoader;
import com.embracesource.yilianti.common.pickerview.GetJsonDataUtil;
import com.embracesource.yilianti.common.pickerview.JsonBean;
import com.embracesource.yilianti.ui.base.BaseActivity;
import com.embracesource.yilianti.viewmodel.ApplyDiagnosis01CallBack;
import com.embracesource.yilianti.viewmodel.BaseViewModel;
import com.google.gson.Gson;
import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.loader.ImageLoader;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.annotations.NonNull;

import static com.embracesource.yilianti.ui.homepage.diagnosis.ApplyDiagnosisActivity01.Max_Pic_Size;

/**
 * Created by Administrator on 2017/10/13 0013.
 */

public class ApplyDiagnosisViewModel01 extends BaseViewModel {
    private  ApplyDiagnosis01CallBack callBack;

//    @Inject
    public ApplyDiagnosisViewModel01() {
    }

    private ArrayList<JsonBean> options1Items = new ArrayList<>();
    private ArrayList<ArrayList<String>> options2Items = new ArrayList<>();
    private ArrayList<ArrayList<ArrayList<String>>> options3Items = new ArrayList<>();

    public ApplyDiagnosisViewModel01(ApplyDiagnosis01CallBack callBack) {
    this.callBack = callBack;
    }

    void initImagePicker(ImageLoader imageLoader) {
        ImagePicker imagePicker = ImagePicker.getInstance();
        imageLoader = new PicassoImageLoader();
/*        imagePicker.setImageLoader(new PicassoImageLoader());   //设置图片加载器
        imagePicker.setShowCamera(true);  //显示拍照按钮
        imagePicker.setCrop(true);        //允许裁剪（单选才有效）
        imagePicker.setSaveRectangle(true); //是否按矩形区域保存
        imagePicker.setSelectLimit(9);    //选中数量限制
        imagePicker.setStyle(CropImageView.Style.RECTANGLE);  //裁剪框的形状
        imagePicker.setFocusWidth(800);   //裁剪框的宽度。单位像素（圆形自动取宽高最小值）
        imagePicker.setFocusHeight(800);  //裁剪框的高度。单位像素（圆形自动取宽高最小值）
        imagePicker.setOutPutX(1000);//保存文件的宽度。单位像素
        imagePicker.setOutPutY(1000);//保存文件的高度。单位像素*/

        imagePicker.setImageLoader(imageLoader);   //设置图片加载器
        imagePicker.setShowCamera(true);  //显示拍照按钮
        imagePicker.setCrop(false);        //允许裁剪（单选才有效）
        imagePicker.setSelectLimit(1);    //选中数量限制
        imagePicker.setMultiMode(false);
        imagePicker.setOutPutX(1000);//保存文件的宽度。单位像素
        imagePicker.setOutPutY(1000);//保存文件的高度。单位像素*/
    }
    void initImagePicker_Mult(ImageLoader imageLoader) {
        ImagePicker imagePicker = ImagePicker.getInstance();
        imageLoader = new PicassoImageLoader();
/*        imagePicker.setImageLoader(new PicassoImageLoader());   //设置图片加载器
        imagePicker.setShowCamera(true);  //显示拍照按钮
        imagePicker.setCrop(true);        //允许裁剪（单选才有效）
        imagePicker.setSaveRectangle(true); //是否按矩形区域保存
        imagePicker.setSelectLimit(9);    //选中数量限制
        imagePicker.setStyle(CropImageView.Style.RECTANGLE);  //裁剪框的形状
        imagePicker.setFocusWidth(800);   //裁剪框的宽度。单位像素（圆形自动取宽高最小值）
        imagePicker.setFocusHeight(800);  //裁剪框的高度。单位像素（圆形自动取宽高最小值）
        imagePicker.setOutPutX(1000);//保存文件的宽度。单位像素
        imagePicker.setOutPutY(1000);//保存文件的高度。单位像素*/

        imagePicker.setImageLoader(imageLoader);   //设置图片加载器
        imagePicker.setShowCamera(true);  //显示拍照按钮
        imagePicker.setCrop(false);        //允许裁剪（单选才有效）
        imagePicker.setSelectLimit(Max_Pic_Size);    //选中数量限制
        imagePicker.setMultiMode(true);
        imagePicker.setOutPutX(1000);//保存文件的宽度。单位像素
        imagePicker.setOutPutY(1000);//保存文件的高度。单位像素*/
    }


    private void initJsonData(Context context, ObservableEmitter<List> e) {//解析数据
        /**
         * 注意：assets 目录下的Json文件仅供参考，实际使用可自行替换文件
         * 关键逻辑在于循环体
         * */
        String JsonData = new GetJsonDataUtil().getJson(context, "province.json");//获取assets目录下的json文件数据
        ArrayList<JsonBean> jsonBean = parseData(JsonData);//用Gson 转成实体
        /**
         * 添加省份数据
         * 注意：如果是添加的JavaBean实体，则实体类需要实现 IPickerViewData 接口，
         * PickerView会通过getPickerViewText方法获取字符串显示出来。
         */
        options1Items = jsonBean;
        for (int i = 0; i < jsonBean.size(); i++) {//遍历省份
            ArrayList<String> CityList = new ArrayList<>();//该省的城市列表（第二级）
            ArrayList<ArrayList<String>> Province_AreaList = new ArrayList<>();//该省的所有地区列表（第三极）
            for (int c = 0; c < jsonBean.get(i).getCityList().size(); c++) {//遍历该省份的所有城市
                String CityName = jsonBean.get(i).getCityList().get(c).getName();
                CityList.add(CityName);//添加城市
                ArrayList<String> City_AreaList = new ArrayList<>();//该城市的所有地区列表
                //如果无地区数据，建议添加空字符串，防止数据为null 导致三个选项长度不匹配造成崩溃
                if (jsonBean.get(i).getCityList().get(c).getArea() == null
                        || jsonBean.get(i).getCityList().get(c).getArea().size() == 0) {
                    City_AreaList.add("");
                } else {
                    for (int d = 0; d < jsonBean.get(i).getCityList().get(c).getArea().size(); d++) {//该城市对应地区所有数据
                        String AreaName = jsonBean.get(i).getCityList().get(c).getArea().get(d);
                        City_AreaList.add(AreaName);//添加该城市所有地区数据
                    }
                }
                Province_AreaList.add(City_AreaList);//添加该省所有地区数据
            }
            /**
             * 添加城市数据
             */
            options2Items.add(CityList);
            /**
             * 添加地区数据
             */
            options3Items.add(Province_AreaList);
        }
        List list = new ArrayList<>();
        list.add(options1Items);
        list.add(options2Items);
        list.add(options3Items);
        e.onNext(list);
    }

    ArrayList<JsonBean> parseData(String result) {//Gson 解析
        ArrayList<JsonBean> detail = new ArrayList<>();
        try {
            JSONArray data = new JSONArray(result);
            Gson gson = new Gson();
            for (int i = 0; i < data.length(); i++) {
                JsonBean entity = gson.fromJson(data.optJSONObject(i).toString(), JsonBean.class);
                detail.add(entity);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return detail;
    }

/*    public void initBaseData() {
        mHandler.sendEmptyMessage(MSG_LOAD_DATA);
    }*/

    Observable initBaseData(final Context context) {
        return Observable.create(new ObservableOnSubscribe<List>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<List> e) throws Exception {
                initJsonData(context, e);
            }
        });
    }

    public void getBaseData(String code) {
        api.getBaseData(code).subscribe(new BaseActivity.MyObserver<ApplyDiagnosisGoalBean>() {
            @Override
            public void onNext(@NonNull ApplyDiagnosisGoalBean response) {
                super.onNext(response);
                callBack.getBaseDataOK(response);
            }

        });
    }
}

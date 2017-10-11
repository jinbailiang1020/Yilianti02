package com.embracesource.yilianti.ui.homepage;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.holder.Holder;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.embracesource.yilianti.R;
import com.embracesource.yilianti.biz.api.ZhihuApi;
import com.embracesource.yilianti.biz.pojo.request.ext.GetThemeRequest;
import com.embracesource.yilianti.biz.pojo.response.ext.GetThemeResponse;
import com.embracesource.yilianti.util.SimpleObserver;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * OtherThemeViewModel <br/>
 * Created by xiaqiulei on 2017-05-26.
 */
public class HomeViewModel extends ViewModel {

    private final ZhihuApi zhihuApi;
    private final MutableLiveData<Object> themeResponse = new MutableLiveData<>();

    @Inject
    HomeViewModel(ZhihuApi zhihuApi) {
        this.zhihuApi = zhihuApi;
    }

    public void initConvenientBanner(ConvenientBanner convenientBanner, List localImages) {
        //开始自动翻页
        convenientBanner.setPages(new CBViewHolderCreator() {
            @Override
            public Object createHolder() {
                return new LocalImageHolderView();
            }
        },localImages)
                //设置指示器是否可见
                .setPointViewVisible(true)
                //设置自动切换（同时设置了切换时间间隔）
                .startTurning(2000)
                //设置两个点图片作为翻页指示器，不设置则没有指示器，可以根据自己需求自行配合自己的指示器,不需要圆点指示器可用不设
                .setPageIndicator(new int[]{R.drawable.dian, R.drawable.dian_1})
                //设置指示器的方向（左、中、右）
                .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.ALIGN_PARENT_RIGHT)
                //设置点击监听事件
                .setOnItemClickListener(new OnItemClickListener() {
                    @Override
                    public void onItemClick(int i) {
//                        Toast.makeText(getActivity(), "position:" + position, Toast.LENGTH_SHORT).show();
                    }
                })
                //设置手动影响（设置了该项无法手动切换）
                .setManualPageable(true);
        //设置翻页的效果，不需要翻页效果可用不设
        //setPageTransformer(Transformer.DefaultTransformer);   // 集成特效之后会有白屏现象，新版已经分离，如果要集成特效的例子可以看Demo的点击响应。

    }

    //为了方便改写，来实现复杂布局的切换
    private class LocalImageHolderView implements Holder<Integer> {
        private ImageView imageView;

        @Override
        public View createView(Context context) {
            //你可以通过layout文件来创建，不一定是Image，任何控件都可以进行翻页
            imageView = new ImageView(context);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            return imageView;
        }

        @Override
        public void UpdateUI(Context context, int position, Integer data) {
            imageView.setImageResource(data);
        }
    }

    public MutableLiveData<Object> clickBannerCallBack(int id) {
       new MutableLiveData<Object>().observeForever(new SimpleObserver<>(themeResponse));
//        zhihuApi.getThemeResponse(new GetThemeRequest(id)).observeForever(new SimpleObserver<>(themeResponse));
        return themeResponse;
    }
}
package com.embracesource.yilianti.app;

import com.embracesource.yilianti.ui.MainViewModel;
import com.embracesource.yilianti.ui.homepage.HomeViewModel;

import dagger.Subcomponent;

/**
 * ViewModelSubComponent <br/>
 * Created by xiaqiulei on 2017-05-26.
 */
@Subcomponent
public interface ViewModelSubComponent {

    @Subcomponent.Builder
    interface Builder {
        ViewModelSubComponent build();
    }
    MainViewModel mainViewModel();
    HomeViewModel homeViewModel();//// TODO: 2017/10/12 0012  
}
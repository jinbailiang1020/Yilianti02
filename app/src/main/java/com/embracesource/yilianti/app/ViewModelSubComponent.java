package com.embracesource.yilianti.app;

import com.embracesource.yilianti.ui.MainViewModel;

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
}
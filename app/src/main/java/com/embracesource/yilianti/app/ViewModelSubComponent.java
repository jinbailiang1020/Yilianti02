package com.embracesource.yilianti.app;

import android.arch.lifecycle.ViewModel;

import com.embracesource.yilianti.ui.MainViewModel;
import com.embracesource.yilianti.ui.homepage.HomeViewModel;
import com.embracesource.yilianti.ui.homepage.diagnosis.ApplyDiagnosisViewModel;

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
    HomeViewModel homeViewModel();
    ApplyDiagnosisViewModel applyDiagnosisViewModel();
}
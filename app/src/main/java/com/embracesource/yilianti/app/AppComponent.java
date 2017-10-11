package com.embracesource.yilianti.app;

import com.google.gson.Gson;

import javax.inject.Singleton;

import com.embracesource.yilianti.ui.MainActivity;
import com.embracesource.yilianti.ui.MainViewModel;

import dagger.Component;

/**
 * AppCompoent <br/>
 * Created by xiaqiulei on 2016-06-29.
 */
@Singleton
@Component(modules = {AppModule.class})
public interface AppComponent {

    Gson gson();

    void inject(AppContext context);

//    void inject(StartViewModel model);

    void inject(MainViewModel model);

//    void inject(HotNewsViewModel model);

//    void inject(OtherThemeViewModel model);

//    void inject(DetailViewModel model);

//    void inject(CommentsViewModel model);

    void inject(MainActivity mainActivity);

    ////////////////////////////////
}
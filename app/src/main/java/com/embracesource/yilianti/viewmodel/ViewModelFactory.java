package com.embracesource.yilianti.viewmodel;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.v4.util.ArrayMap;

import java.util.Map;
import java.util.concurrent.Callable;

import javax.inject.Inject;
import javax.inject.Singleton;

import com.embracesource.yilianti.app.ViewModelSubComponent;
import com.embracesource.yilianti.ui.MainViewModel;

/**
 * ViewModelFactory <br/>
 * Created by xiaqiulei on 2017-05-26.
 */
@Singleton
public class ViewModelFactory implements ViewModelProvider.Factory {

    private ArrayMap<Class, Callable<? extends ViewModel>> creators;

    @Inject
    public ViewModelFactory(final ViewModelSubComponent component) {
        creators = new ArrayMap<>();
        creators.put(MainViewModel.class, new Callable<ViewModel>() {
            @Override
            public ViewModel call() throws Exception {
                return component.mainViewModel();
            }
        });

    }

    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        Callable<? extends ViewModel> creator = creators.get(modelClass);
        if (creator == null) {
            for (Map.Entry<Class, Callable<? extends ViewModel>> entry : creators.entrySet()) {
                if (modelClass.isAssignableFrom(entry.getKey())) {
                    creator = entry.getValue();
                    break;
                }
            }
        }
        if (creator == null) {
            throw new IllegalArgumentException("unknown model class " + modelClass);
        }
        try {
            return (T) creator.call();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
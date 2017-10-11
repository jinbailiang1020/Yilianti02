package com.embracesource.yilianti.biz.api.impl.retrofit;

import com.embracesource.yilianti.core.net.AppException;

/**
 * RetrofitAdapter <br/>
 * Created by xiaqiulei on 2016-01-10.
 */
abstract class RetrofitAdapter<T> {

    abstract T call() throws Exception;

    protected T get() throws AppException {
        T t;
        try {
            t = call();
        } catch (Exception e) {
            throw new AppException(e);
        }
        return t;
    }
}
package com.embracesource.yilianti.util;

/**
 * Created by Administrator on 2017/10/18 0018.
 */

public class StringUtils {
    public static Boolean isNullorEmpty(String s){
        return s==null|| s.equals("null")||s.isEmpty();
    }
}

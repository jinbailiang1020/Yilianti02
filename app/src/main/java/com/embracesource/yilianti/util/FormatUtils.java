package com.embracesource.yilianti.util;

/**
 * Created by Administrator on 2017/10/23 0023.
 */

public class FormatUtils {

    public static Boolean isInValidPwd(String pwd) {
        return pwd != null && pwd.length() < 6;
    }
}

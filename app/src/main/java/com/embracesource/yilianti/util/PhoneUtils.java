package com.embracesource.yilianti.util;

import android.content.Context;
import android.view.WindowManager;

/**
 * Created by Administrator on 2017/10/16 0016.
 */

public class PhoneUtils {

   public static int getPhoneWidth(Context context){
        WindowManager wm = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
       return  wm.getDefaultDisplay().getWidth();
//        int height = wm.getDefaultDisplay().getHeight();
    }

    public static int getPhoneHeight(Context context){
        WindowManager wm = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        return  wm.getDefaultDisplay().getHeight();
    }

}

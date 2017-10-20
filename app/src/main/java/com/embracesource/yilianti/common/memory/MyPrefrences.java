package com.embracesource.yilianti.common.memory;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Administrator on 2017/10/17 0017.
 */

public class MyPrefrences {
    private final static String fileName = "Yilianti";
    //定义一个SharePreference对象
    SharedPreferences sharedPreferences;

    public MyPrefrences(Context context) {
        sharedPreferences = context.getSharedPreferences(fileName, Context.MODE_PRIVATE);
    }

    public static class ContentValue {
        String key;
        Object value;

        public ContentValue(String key, Object value) {
            this.key = key;
            this.value = value;
        }
    }

    public void putValues(ContentValue... contentValues) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        for (ContentValue contentValue : contentValues) {
            if (contentValue.value instanceof String) {
                editor.putString(contentValue.key, contentValue.value.toString()).commit();
            }
            if (contentValue.value instanceof Integer) {
                editor.putInt(contentValue.key, Integer.parseInt(contentValue.value.toString())).commit();
            }
            if (contentValue.value instanceof Long) {
                editor.putLong(contentValue.key, Long.parseLong(contentValue.value.toString())).commit();
            }
            if (contentValue.value instanceof Boolean) {
                editor.putBoolean(contentValue.key, Boolean.parseBoolean(contentValue.value.toString())).commit();
            }
        }
    }

    //获取数据的方法
    public String getString(String key) {
        return sharedPreferences.getString(key, null);
    }

    public boolean getBoolean(String key) {
        return sharedPreferences.getBoolean(key, false);
    }

    public int getInt(String key) {
        return sharedPreferences.getInt(key, -1);
    }

    public long getLong(String key) {
        return sharedPreferences.getLong(key, -1);
    }



    //清除当前文件的所有的数据
    public void clear() {
        sharedPreferences.edit().clear().commit();
    }



    public interface Key {
        String userName = "userName";
        String pwd = "pwd";
        String loginBean = "LoginBean";
        String sessionid = "sessionid";
        String role = "role";
    }


}

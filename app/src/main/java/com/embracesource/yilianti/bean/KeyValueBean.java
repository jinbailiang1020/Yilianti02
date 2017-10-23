package com.embracesource.yilianti.bean;

/**
 * Created by Administrator on 2017/10/23 0023.
 */
public class KeyValueBean {
    private  int key;
    private  String value;

    public KeyValueBean(int key, String value) {
        this.key = key;
        this.value = value;
    }

    public KeyValueBean() {
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}

package com.embracesource.yilianti.bean.enums;

/**
 * Created by Administrator on 2017/10/23 0023.
 * 会诊转诊的审核状态。。Available
 */

public enum DiagnosisExaminationType {
    STATUS_Medical_Service(1, "等待医务处审核"),
    STATUS_Top_Medical_Service(2, "等待上级医院审核"),
    STATUS_Expert_Reply(3, "等待专家团队回复"),
    STATUS_Finished(100, "已完成");

/*    public static final idValueBean(1, "等待医务处审核")STATUS_Medical_Service = new idValueBean(1,"等待医务处审核"int i, String 等待医务处审核);
    public static final idValueBean STATUS_Top_Medical_Service = new idValueBean(2, "等待上级医院审核");
    public static final idValueBean STATUS_Expert_Reply = new idValueBean(3, "等待专家团队回复");

    public static final idValueBean STATUS_Finished = new idValueBean(100, "已完成");*/


    public final String value;
    public final int id;

     DiagnosisExaminationType(int id, String value) {
        this.id = id;
        this.value = value;
    }

    public static  String getDiagnosisExaminationTypeString(int id) {
        for (DiagnosisExaminationType type : DiagnosisExaminationType.values()) {
            if (id == type.id) {
                return type.value;
            }
        }
        return STATUS_Finished.value;
    }
}
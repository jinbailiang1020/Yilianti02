package com.embracesource.yilianti.bean.enums;


/**
 * Created by Administrator on 2017/10/24 0024.
 * 会诊目的
 * {"id":1,"createdTime":1504771845000,"hVersion":1,"available":1,"code":"consultation_objective","value":1,"description":"明确诊断","order":1,"parentid":null,"mark":"会诊目的"},
 * {"id":2,"createdTime":1504771780000,"hVersion":1,"available":1,"code":"consultation_objective","value":2,"description":"协助治疗","order":2,"parentid":null,"mark":"会诊目的"},
 * {"id":3,"createdTime":1504771811000,"hVersion":1,"available":1,"code":"consultation_objective","value":3,"description":"要求转诊","order":3,"parentid":null,"mark":"会诊目的"},
 * {"id":4,"createdTime":1504771817000,"hVersion":1,"available":1,"code":"consultation_objective","value":4,"description":"其他","order":4,"parentid":null,"mark":"会诊目的"}]
 */

public enum ConsultationObjectiveType {

    ExplicitDiagnosis(1, "明确诊断"),
    Assist(2, "协助治疗"),
    ChangeDiagnosis(3, "要求转诊"),
    Other(4, "其他");

    public final int id;
    public final String value;

    ConsultationObjectiveType(int id, String value) {
        this.id = id;
        this.value = value;
    }

    public static String getValueById(int id) {
        for (ConsultationObjectiveType type : ConsultationObjectiveType.values()) {
            if (id == type.id) {
                return type.value;
            }
        }
        return Other.value;
    }

    public static ConsultationObjectiveType getTypeById(int id) {
        for (ConsultationObjectiveType type : ConsultationObjectiveType.values()) {
            if (id == type.id) {
                return type;
            }
        }
        return Other;
    }


}

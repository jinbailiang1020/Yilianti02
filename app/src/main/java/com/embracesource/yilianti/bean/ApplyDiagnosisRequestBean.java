package com.embracesource.yilianti.bean;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/10/17 0017.
 */

public class ApplyDiagnosisRequestBean implements Serializable {

    /**
     * PatientInfo : {"idnumber":"4303XXX","fullname":"周琦2","age":10,"sex":1,"phonenum":"139","medicareType":1,"nativeplace":"湘潭"}
     * PatientIllnessBasicinfo : {"preliminaryDiagnosis":"初步诊断","illnessDescription":"病情描述","chiefComplaint":"主诉"}
     * ConsultationReferral : {"type":1,"consultationObjective":1,"consultationType":1,"team":1,"priority":1,"saveKey":"7a890690-52f7-454d-886f-7a71e12b107e"}
     */

    private PatientInfoBean PatientInfo;
    private PatientIllnessBasicinfoBean PatientIllnessBasicinfo;
    private ConsultationReferralBean ConsultationReferral;

    public PatientInfoBean getPatientInfo() {
        return PatientInfo;
    }

    public void setPatientInfo(PatientInfoBean PatientInfo) {
        this.PatientInfo = PatientInfo;
    }

    public PatientIllnessBasicinfoBean getPatientIllnessBasicinfo() {
        return PatientIllnessBasicinfo;
    }

    public void setPatientIllnessBasicinfo(PatientIllnessBasicinfoBean PatientIllnessBasicinfo) {
        this.PatientIllnessBasicinfo = PatientIllnessBasicinfo;
    }

    public ConsultationReferralBean getConsultationReferral() {
        return ConsultationReferral;
    }

    public void setConsultationReferral(ConsultationReferralBean ConsultationReferral) {
        this.ConsultationReferral = ConsultationReferral;
    }

    public static class PatientInfoBean implements Serializable{
        /**
         * idnumber : 4303XXX
         * fullname : 周琦2
         * age : 10
         * sex : 1
         * phonenum : 139
         * medicareType : 1
         * nativeplace : 湘潭
         */

        private String idnumber;
        private String fullname;
        private int age;
        private int sex;
        private String phonenum;
        private int medicareType;
        private String nativeplace;

        public String getIdnumber() {
            return idnumber;
        }

        public void setIdnumber(String idnumber) {
            this.idnumber = idnumber;
        }

        public String getFullname() {
            return fullname;
        }

        public void setFullname(String fullname) {
            this.fullname = fullname;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public int getSex() {
            return sex;
        }

        public void setSex(int sex) {
            this.sex = sex;
        }

        public String getPhonenum() {
            return phonenum;
        }

        public void setPhonenum(String phonenum) {
            this.phonenum = phonenum;
        }

        public int getMedicareType() {
            return medicareType;
        }

        public void setMedicareType(int medicareType) {
            this.medicareType = medicareType;
        }

        public String getNativeplace() {
            return nativeplace;
        }

        public void setNativeplace(String nativeplace) {
            this.nativeplace = nativeplace;
        }
    }

    public static class PatientIllnessBasicinfoBean implements Serializable {
        /**
         * preliminaryDiagnosis : 初步诊断
         * illnessDescription : 病情描述
         * chiefComplaint : 主诉
         */

        private String preliminaryDiagnosis;
        private String illnessDescription;
        private String chiefComplaint;

        public String getPreliminaryDiagnosis() {
            return preliminaryDiagnosis;
        }

        public void setPreliminaryDiagnosis(String preliminaryDiagnosis) {
            this.preliminaryDiagnosis = preliminaryDiagnosis;
        }

        public String getIllnessDescription() {
            return illnessDescription;
        }

        public void setIllnessDescription(String illnessDescription) {
            this.illnessDescription = illnessDescription;
        }

        public String getChiefComplaint() {
            return chiefComplaint;
        }

        public void setChiefComplaint(String chiefComplaint) {
            this.chiefComplaint = chiefComplaint;
        }
    }

    public static class ConsultationReferralBean implements Serializable {
        /**
         * type : 1
         * consultationObjective : 1
         * consultationType : 1
         * team : 1
         * priority : 1
         * saveKey : 7a890690-52f7-454d-886f-7a71e12b107e
         * <p>
         * "referralDoctor":1,
         * "priority":1,
         * "referralPlanDate":"2017-09-04 08:00:00"
         */

        private int type;
        private int consultationObjective;//咨询目的
        private int consultationType;//咨询类型
        private int team;//
        private int priority;//优先
        private String saveKey;

        public int getReferralDoctor() {
            return referralDoctor;
        }

        public void setReferralDoctor(int referralDoctor) {
            this.referralDoctor = referralDoctor;
        }

        public int getReferralHospital() {
            return referralHospital;
        }

        public void setReferralHospital(int referralHospital) {
            this.referralHospital = referralHospital;
        }

        public int getReferralPlanDate() {
            return referralPlanDate;
        }

        public void setReferralPlanDate(int referralPlanDate) {
            this.referralPlanDate = referralPlanDate;
        }

        private int referralDoctor;
        private int referralHospital;
        private int referralPlanDate;

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getConsultationObjective() {
            return consultationObjective;
        }

        public void setConsultationObjective(int consultationObjective) {
            this.consultationObjective = consultationObjective;
        }

        public int getConsultationType() {
            return consultationType;
        }

        public void setConsultationType(int consultationType) {
            this.consultationType = consultationType;
        }

        public int getTeam() {
            return team;
        }

        public void setTeam(int team) {
            this.team = team;
        }

        public int getPriority() {
            return priority;
        }

        public void setPriority(int priority) {
            this.priority = priority;
        }

        public String getSaveKey() {
            return saveKey;
        }

        public void setSaveKey(String saveKey) {
            this.saveKey = saveKey;
        }
    }
}

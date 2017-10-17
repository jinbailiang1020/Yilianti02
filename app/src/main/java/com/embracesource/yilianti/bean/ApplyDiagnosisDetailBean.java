package com.embracesource.yilianti.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/10/17 0017.
 */

public class ApplyDiagnosisDetailBean extends BaseBean {


    /**
     * code : 1111
     * message : success
     * data : {"PatientInfo":{"id":39,"createdTime":1508144351000,"hVersion":1,"available":1,"userId":null,"fullname":"陈先生","sex":1,"age":31,"idnumber":"430101199901011234","address":"","phonenum":"13117513421","medicareType":2,"nativeplace":"湖南长沙"},"list":[{"id":65,"createdTime":"2017-10-16 16:59:11","hVersion":4,"available":7,"type":2,"userId":null,"patientId":39,"patientIllnessBasicinfoId":65,"doctorId":2,"consultationType":1,"consultationObjective":3,"priority":1,"team":1,"referralHospital":1,"referralDoctor":32,"referralPlanDate":"2017-10-16 00:00:00","referralStartdate":null,"referralEnddate":null,"saveKey":"6542ad79-a609-4ae2-aad9-4e3ac9fe99b3","chiefComplaint":"皮炎湿疹","preliminaryDiagnosis":"皮炎湿疹","illnessDescription":"皮炎湿疹皮炎湿疹皮炎湿疹","consultationTypeName":"普通会诊","consultationObjectiveName":"要求转诊","teamName":"银屑病等红斑鳞屑性疾病团队","referralHospitalName":"湘雅二医院","referralDoctorName":null,"content":"","medicineHormone":"A激素100ML；B激素500ML；A激素100ML；B激素500ML；A激素100ML；B激素500ML；","medicineAntibiotic":"","medicineAntiallergic":"A激素100ML；B激素500ML；A激素100ML；B激素500ML；","medicineExternal":"","operation":"","picIdcard":["http://192.168.1.165/imgs/idcard/6542ad79-a609-4ae2-aad9-4e3ac9fe99b3/20171016165848-4f878929-0fc6-4355-8c6e-2a400e3099df.jpg"],"picIllness":["http://192.168.1.165/imgs/illness/6542ad79-a609-4ae2-aad9-4e3ac9fe99b3/20171016165845-80518c9f-c104-43b0-b1bb-6111a1b15e17.jpg"],"video":null,"patientName":null}]}
     * traceInfo : []
     * sessionid : null
     * fail : false
     * success : true
     */

    private String code;
    private String message;
    private DataBean data;
    private Object sessionid;
    private boolean fail;
    private boolean success;
    private List<?> traceInfo;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public Object getSessionid() {
        return sessionid;
    }

    public void setSessionid(Object sessionid) {
        this.sessionid = sessionid;
    }

    public boolean isFail() {
        return fail;
    }

    public void setFail(boolean fail) {
        this.fail = fail;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public List<?> getTraceInfo() {
        return traceInfo;
    }

    public void setTraceInfo(List<?> traceInfo) {
        this.traceInfo = traceInfo;
    }

    public static class DataBean {
        /**
         * PatientInfo : {"id":39,"createdTime":1508144351000,"hVersion":1,"available":1,"userId":null,"fullname":"陈先生","sex":1,"age":31,"idnumber":"430101199901011234","address":"","phonenum":"13117513421","medicareType":2,"nativeplace":"湖南长沙"}
         * list : [{"id":65,"createdTime":"2017-10-16 16:59:11","hVersion":4,"available":7,"type":2,"userId":null,"patientId":39,"patientIllnessBasicinfoId":65,"doctorId":2,"consultationType":1,"consultationObjective":3,"priority":1,"team":1,"referralHospital":1,"referralDoctor":32,"referralPlanDate":"2017-10-16 00:00:00","referralStartdate":null,"referralEnddate":null,"saveKey":"6542ad79-a609-4ae2-aad9-4e3ac9fe99b3","chiefComplaint":"皮炎湿疹","preliminaryDiagnosis":"皮炎湿疹","illnessDescription":"皮炎湿疹皮炎湿疹皮炎湿疹","consultationTypeName":"普通会诊","consultationObjectiveName":"要求转诊","teamName":"银屑病等红斑鳞屑性疾病团队","referralHospitalName":"湘雅二医院","referralDoctorName":null,"content":"","medicineHormone":"A激素100ML；B激素500ML；A激素100ML；B激素500ML；A激素100ML；B激素500ML；","medicineAntibiotic":"","medicineAntiallergic":"A激素100ML；B激素500ML；A激素100ML；B激素500ML；","medicineExternal":"","operation":"","picIdcard":["http://192.168.1.165/imgs/idcard/6542ad79-a609-4ae2-aad9-4e3ac9fe99b3/20171016165848-4f878929-0fc6-4355-8c6e-2a400e3099df.jpg"],"picIllness":["http://192.168.1.165/imgs/illness/6542ad79-a609-4ae2-aad9-4e3ac9fe99b3/20171016165845-80518c9f-c104-43b0-b1bb-6111a1b15e17.jpg"],"video":null,"patientName":null}]
         */

        private PatientInfoBean PatientInfo;
        private List<ListBean> list;

        public PatientInfoBean getPatientInfo() {
            return PatientInfo;
        }

        public void setPatientInfo(PatientInfoBean PatientInfo) {
            this.PatientInfo = PatientInfo;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class PatientInfoBean {
            /**
             * id : 39
             * createdTime : 1508144351000
             * hVersion : 1
             * available : 1
             * userId : null
             * fullname : 陈先生
             * sex : 1
             * age : 31
             * idnumber : 430101199901011234
             * address :
             * phonenum : 13117513421
             * medicareType : 2
             * nativeplace : 湖南长沙
             */

            private int id;
            private long createdTime;
            private int hVersion;
            private int available;
            private Object userId;
            private String fullname;
            private int sex;
            private int age;
            private String idnumber;
            private String address;
            private String phonenum;
            private int medicareType;
            private String nativeplace;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public long getCreatedTime() {
                return createdTime;
            }

            public void setCreatedTime(long createdTime) {
                this.createdTime = createdTime;
            }

            public int getHVersion() {
                return hVersion;
            }

            public void setHVersion(int hVersion) {
                this.hVersion = hVersion;
            }

            public int getAvailable() {
                return available;
            }

            public void setAvailable(int available) {
                this.available = available;
            }

            public Object getUserId() {
                return userId;
            }

            public void setUserId(Object userId) {
                this.userId = userId;
            }

            public String getFullname() {
                return fullname;
            }

            public void setFullname(String fullname) {
                this.fullname = fullname;
            }

            public int getSex() {
                return sex;
            }

            public void setSex(int sex) {
                this.sex = sex;
            }

            public int getAge() {
                return age;
            }

            public void setAge(int age) {
                this.age = age;
            }

            public String getIdnumber() {
                return idnumber;
            }

            public void setIdnumber(String idnumber) {
                this.idnumber = idnumber;
            }

            public String getAddress() {
                return address;
            }

            public void setAddress(String address) {
                this.address = address;
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

        public static class ListBean {
            /**
             * id : 65
             * createdTime : 2017-10-16 16:59:11
             * hVersion : 4
             * available : 7
             * type : 2
             * userId : null
             * patientId : 39
             * patientIllnessBasicinfoId : 65
             * doctorId : 2
             * consultationType : 1
             * consultationObjective : 3
             * priority : 1
             * team : 1
             * referralHospital : 1
             * referralDoctor : 32
             * referralPlanDate : 2017-10-16 00:00:00
             * referralStartdate : null
             * referralEnddate : null
             * saveKey : 6542ad79-a609-4ae2-aad9-4e3ac9fe99b3
             * chiefComplaint : 皮炎湿疹
             * preliminaryDiagnosis : 皮炎湿疹
             * illnessDescription : 皮炎湿疹皮炎湿疹皮炎湿疹
             * consultationTypeName : 普通会诊
             * consultationObjectiveName : 要求转诊
             * teamName : 银屑病等红斑鳞屑性疾病团队
             * referralHospitalName : 湘雅二医院
             * referralDoctorName : null
             * content :
             * medicineHormone : A激素100ML；B激素500ML；A激素100ML；B激素500ML；A激素100ML；B激素500ML；
             * medicineAntibiotic :
             * medicineAntiallergic : A激素100ML；B激素500ML；A激素100ML；B激素500ML；
             * medicineExternal :
             * operation :
             * picIdcard : ["http://192.168.1.165/imgs/idcard/6542ad79-a609-4ae2-aad9-4e3ac9fe99b3/20171016165848-4f878929-0fc6-4355-8c6e-2a400e3099df.jpg"]
             * picIllness : ["http://192.168.1.165/imgs/illness/6542ad79-a609-4ae2-aad9-4e3ac9fe99b3/20171016165845-80518c9f-c104-43b0-b1bb-6111a1b15e17.jpg"]
             * video : null
             * patientName : null
             */

            private int id;
            private String createdTime;
            private int hVersion;
            private int available;
            private int type;
            private Object userId;
            private int patientId;
            private int patientIllnessBasicinfoId;
            private int doctorId;
            private int consultationType;
            private int consultationObjective;
            private int priority;
            private int team;
            private int referralHospital;
            private int referralDoctor;
            private String referralPlanDate;
            private Object referralStartdate;
            private Object referralEnddate;
            private String saveKey;
            private String chiefComplaint;
            private String preliminaryDiagnosis;
            private String illnessDescription;
            private String consultationTypeName;
            private String consultationObjectiveName;
            private String teamName;
            private String referralHospitalName;
            private Object referralDoctorName;
            private String content;
            private String medicineHormone;
            private String medicineAntibiotic;
            private String medicineAntiallergic;
            private String medicineExternal;
            private String operation;
            private Object video;
            private Object patientName;
            private List<String> picIdcard;
            private List<String> picIllness;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getCreatedTime() {
                return createdTime;
            }

            public void setCreatedTime(String createdTime) {
                this.createdTime = createdTime;
            }

            public int getHVersion() {
                return hVersion;
            }

            public void setHVersion(int hVersion) {
                this.hVersion = hVersion;
            }

            public int getAvailable() {
                return available;
            }

            public void setAvailable(int available) {
                this.available = available;
            }

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }

            public Object getUserId() {
                return userId;
            }

            public void setUserId(Object userId) {
                this.userId = userId;
            }

            public int getPatientId() {
                return patientId;
            }

            public void setPatientId(int patientId) {
                this.patientId = patientId;
            }

            public int getPatientIllnessBasicinfoId() {
                return patientIllnessBasicinfoId;
            }

            public void setPatientIllnessBasicinfoId(int patientIllnessBasicinfoId) {
                this.patientIllnessBasicinfoId = patientIllnessBasicinfoId;
            }

            public int getDoctorId() {
                return doctorId;
            }

            public void setDoctorId(int doctorId) {
                this.doctorId = doctorId;
            }

            public int getConsultationType() {
                return consultationType;
            }

            public void setConsultationType(int consultationType) {
                this.consultationType = consultationType;
            }

            public int getConsultationObjective() {
                return consultationObjective;
            }

            public void setConsultationObjective(int consultationObjective) {
                this.consultationObjective = consultationObjective;
            }

            public int getPriority() {
                return priority;
            }

            public void setPriority(int priority) {
                this.priority = priority;
            }

            public int getTeam() {
                return team;
            }

            public void setTeam(int team) {
                this.team = team;
            }

            public int getReferralHospital() {
                return referralHospital;
            }

            public void setReferralHospital(int referralHospital) {
                this.referralHospital = referralHospital;
            }

            public int getReferralDoctor() {
                return referralDoctor;
            }

            public void setReferralDoctor(int referralDoctor) {
                this.referralDoctor = referralDoctor;
            }

            public String getReferralPlanDate() {
                return referralPlanDate;
            }

            public void setReferralPlanDate(String referralPlanDate) {
                this.referralPlanDate = referralPlanDate;
            }

            public Object getReferralStartdate() {
                return referralStartdate;
            }

            public void setReferralStartdate(Object referralStartdate) {
                this.referralStartdate = referralStartdate;
            }

            public Object getReferralEnddate() {
                return referralEnddate;
            }

            public void setReferralEnddate(Object referralEnddate) {
                this.referralEnddate = referralEnddate;
            }

            public String getSaveKey() {
                return saveKey;
            }

            public void setSaveKey(String saveKey) {
                this.saveKey = saveKey;
            }

            public String getChiefComplaint() {
                return chiefComplaint;
            }

            public void setChiefComplaint(String chiefComplaint) {
                this.chiefComplaint = chiefComplaint;
            }

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

            public String getConsultationTypeName() {
                return consultationTypeName;
            }

            public void setConsultationTypeName(String consultationTypeName) {
                this.consultationTypeName = consultationTypeName;
            }

            public String getConsultationObjectiveName() {
                return consultationObjectiveName;
            }

            public void setConsultationObjectiveName(String consultationObjectiveName) {
                this.consultationObjectiveName = consultationObjectiveName;
            }

            public String getTeamName() {
                return teamName;
            }

            public void setTeamName(String teamName) {
                this.teamName = teamName;
            }

            public String getReferralHospitalName() {
                return referralHospitalName;
            }

            public void setReferralHospitalName(String referralHospitalName) {
                this.referralHospitalName = referralHospitalName;
            }

            public Object getReferralDoctorName() {
                return referralDoctorName;
            }

            public void setReferralDoctorName(Object referralDoctorName) {
                this.referralDoctorName = referralDoctorName;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public String getMedicineHormone() {
                return medicineHormone;
            }

            public void setMedicineHormone(String medicineHormone) {
                this.medicineHormone = medicineHormone;
            }

            public String getMedicineAntibiotic() {
                return medicineAntibiotic;
            }

            public void setMedicineAntibiotic(String medicineAntibiotic) {
                this.medicineAntibiotic = medicineAntibiotic;
            }

            public String getMedicineAntiallergic() {
                return medicineAntiallergic;
            }

            public void setMedicineAntiallergic(String medicineAntiallergic) {
                this.medicineAntiallergic = medicineAntiallergic;
            }

            public String getMedicineExternal() {
                return medicineExternal;
            }

            public void setMedicineExternal(String medicineExternal) {
                this.medicineExternal = medicineExternal;
            }

            public String getOperation() {
                return operation;
            }

            public void setOperation(String operation) {
                this.operation = operation;
            }

            public Object getVideo() {
                return video;
            }

            public void setVideo(Object video) {
                this.video = video;
            }

            public Object getPatientName() {
                return patientName;
            }

            public void setPatientName(Object patientName) {
                this.patientName = patientName;
            }

            public List<String> getPicIdcard() {
                return picIdcard;
            }

            public void setPicIdcard(List<String> picIdcard) {
                this.picIdcard = picIdcard;
            }

            public List<String> getPicIllness() {
                return picIllness;
            }

            public void setPicIllness(List<String> picIllness) {
                this.picIllness = picIllness;
            }
        }
    }
}

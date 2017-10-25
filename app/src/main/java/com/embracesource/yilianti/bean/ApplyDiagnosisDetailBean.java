package com.embracesource.yilianti.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/10/17 0017.
 */

public class ApplyDiagnosisDetailBean extends BaseBean {


    /**
     * code : 1111
     * message : success
     * data : {"PatientInfo":{"id":48,"createdTime":1508378116000,"hVersion":1,"available":1,"userId":null,"fullname":"李白","sex":0,"age":28,"idnumber":"430386198811187766","address":null,"phonenum":"13546463434","medicareType":12,"nativeplace":"海南省琼海市"},"list":[{"id":75,"createdTime":"2017-10-19 09:55:16","hVersion":1,"available":1,"type":6,"userId":null,"patientId":48,"patientIllnessBasicinfoId":75,"doctorId":2,"consultationType":0,"consultationObjective":3,"priority":9,"team":2,"referralHospital":1,"referralDoctor":6,"referralPlanDate":null,"referralStartdate":null,"referralEnddate":null,"saveKey":null,"chiefComplaint":"住宿哈","preliminaryDiagnosis":"avbuzheme初步诊断h","illnessDescription":"病情描述什么额","consultationTypeName":null,"consultationObjectiveName":"要求转诊","teamName":"荨麻疹等过敏性疾病团队","referralHospitalName":"湘雅二医院","referralDoctorName":"谢红付","content":null,"medicineHormone":null,"medicineAntibiotic":null,"medicineAntiallergic":null,"medicineExternal":null,"operation":null,"picIdcard":null,"picIllness":null,"video":null,"patientName":null}]}
     * traceInfo : []
     * sessionid : null
     * success : true
     * fail : false
     */

    private String code;
    private String message;
    private DataBean data;
    private Object sessionid;
    private boolean success;
    private boolean fail;
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

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public boolean isFail() {
        return fail;
    }

    public void setFail(boolean fail) {
        this.fail = fail;
    }

    public List<?> getTraceInfo() {
        return traceInfo;
    }

    public void setTraceInfo(List<?> traceInfo) {
        this.traceInfo = traceInfo;
    }

    public static class DataBean {
        /**
         * PatientInfo : {"id":48,"createdTime":1508378116000,"hVersion":1,"available":1,"userId":null,"fullname":"李白","sex":0,"age":28,"idnumber":"430386198811187766","address":null,"phonenum":"13546463434","medicareType":12,"nativeplace":"海南省琼海市"}
         * list : [{"id":75,"createdTime":"2017-10-19 09:55:16","hVersion":1,"available":1,"type":6,"userId":null,"patientId":48,"patientIllnessBasicinfoId":75,"doctorId":2,"consultationType":0,"consultationObjective":3,"priority":9,"team":2,"referralHospital":1,"referralDoctor":6,"referralPlanDate":null,"referralStartdate":null,"referralEnddate":null,"saveKey":null,"chiefComplaint":"住宿哈","preliminaryDiagnosis":"avbuzheme初步诊断h","illnessDescription":"病情描述什么额","consultationTypeName":null,"consultationObjectiveName":"要求转诊","teamName":"荨麻疹等过敏性疾病团队","referralHospitalName":"湘雅二医院","referralDoctorName":"谢红付","content":null,"medicineHormone":null,"medicineAntibiotic":null,"medicineAntiallergic":null,"medicineExternal":null,"operation":null,"picIdcard":null,"picIllness":null,"video":null,"patientName":null}]
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
             * id : 48
             * createdTime : 1508378116000
             * hVersion : 1
             * available : 1
             * userId : null
             * fullname : 李白
             * sex : 0
             * age : 28
             * idnumber : 430386198811187766
             * address : null
             * phonenum : 13546463434
             * medicareType : 12
             * nativeplace : 海南省琼海市
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
            private Object address;
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

            public Object getAddress() {
                return address;
            }

            public void setAddress(Object address) {
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
             * id : 75
             * createdTime : 2017-10-19 09:55:16
             * hVersion : 1
             * available : 1
             * type : 6
             * userId : null
             * patientId : 48
             * patientIllnessBasicinfoId : 75
             * doctorId : 2
             * consultationType : 0
             * consultationObjective : 3
             * priority : 9
             * team : 2
             * referralHospital : 1
             * referralDoctor : 6
             * referralPlanDate : null
             * referralStartdate : null
             * referralEnddate : null
             * saveKey : null
             * chiefComplaint : 住宿哈
             * preliminaryDiagnosis : avbuzheme初步诊断h
             * illnessDescription : 病情描述什么额
             * consultationTypeName : null
             * consultationObjectiveName : 要求转诊
             * teamName : 荨麻疹等过敏性疾病团队
             * referralHospitalName : 湘雅二医院
             * referralDoctorName : 谢红付
             * content : null
             * medicineHormone : null
             * medicineAntibiotic : null
             * medicineAntiallergic : null
             * medicineExternal : null
             * operation : null
             * picIdcard : null
             * picIllness : null
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
            private String referralStartdate;
            private String referralEnddate;
            private Object saveKey;
            private String chiefComplaint;
            private String preliminaryDiagnosis;
            private String illnessDescription;
            private Object consultationTypeName;
            private String consultationObjectiveName;
            private String teamName;
            private String referralHospitalName;
            private String referralDoctorName;
            private Object content;
            private Object medicineHormone;
            private Object medicineAntibiotic;
            private Object medicineAntiallergic;
            private Object medicineExternal;
            private Object operation;
            private List<String> picIdcard;
            private List<String> picIllness;
            private Object video;
            private Object patientName;

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

            public String getReferralStartdate() {
                return referralStartdate;
            }

            public void setReferralStartdate(String referralStartdate) {
                this.referralStartdate = referralStartdate;
            }

            public String getReferralEnddate() {
                return referralEnddate;
            }

            public void setReferralEnddate(String referralEnddate) {
                this.referralEnddate = referralEnddate;
            }

            public Object getSaveKey() {
                return saveKey;
            }

            public void setSaveKey(Object saveKey) {
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

            public Object getConsultationTypeName() {
                return consultationTypeName;
            }

            public void setConsultationTypeName(Object consultationTypeName) {
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

            public String getReferralDoctorName() {
                return referralDoctorName;
            }

            public void setReferralDoctorName(String referralDoctorName) {
                this.referralDoctorName = referralDoctorName;
            }

            public Object getContent() {
                return content;
            }

            public void setContent(Object content) {
                this.content = content;
            }

            public Object getMedicineHormone() {
                return medicineHormone;
            }

            public void setMedicineHormone(Object medicineHormone) {
                this.medicineHormone = medicineHormone;
            }

            public Object getMedicineAntibiotic() {
                return medicineAntibiotic;
            }

            public void setMedicineAntibiotic(Object medicineAntibiotic) {
                this.medicineAntibiotic = medicineAntibiotic;
            }

            public Object getMedicineAntiallergic() {
                return medicineAntiallergic;
            }

            public void setMedicineAntiallergic(Object medicineAntiallergic) {
                this.medicineAntiallergic = medicineAntiallergic;
            }

            public Object getMedicineExternal() {
                return medicineExternal;
            }

            public void setMedicineExternal(Object medicineExternal) {
                this.medicineExternal = medicineExternal;
            }

            public Object getOperation() {
                return operation;
            }

            public void setOperation(Object operation) {
                this.operation = operation;
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
        }
    }
}

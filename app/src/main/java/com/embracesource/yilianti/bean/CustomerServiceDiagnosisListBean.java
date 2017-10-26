package com.embracesource.yilianti.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/10/23 0023.
 */

public class CustomerServiceDiagnosisListBean {

    /**
     * code : 1111
     * message : success
     * data : [{"id":76,"createdTime":"2017-10-20 14:47:12","hVersion":4,"available":6,"type":2,"userId":null,"patientId":49,"patientIllnessBasicinfoId":76,"doctorId":2,"consultationType":2,"consultationObjective":3,"priority":1,"team":1,"referralHospital":1,"referralDoctor":23,"referralPlanDate":"2017-10-28 00:00:00","referralStartdate":null,"referralEnddate":null,"saveKey":"7414d011-5585-40cd-adcd-6f7a777f366b","chiefComplaint":null,"preliminaryDiagnosis":null,"illnessDescription":null,"consultationTypeName":null,"consultationObjectiveName":null,"teamName":null,"referralHospitalName":null,"referralDoctorName":null,"content":null,"medicineHormone":null,"medicineAntibiotic":null,"medicineAntiallergic":null,"medicineExternal":null,"operation":null,"picIdcard":null,"picIllness":null,"video":null,"patientName":"赵爽测试","doctorName":null,"doctorGroup":null}]
     * traceInfo : []
     * sessionid : null
     * success : true
     * fail : false
     */

    private String code;
    private String message;
    private Object sessionid;
    private boolean success;
    private boolean fail;
    private List<DataBean> data;
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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public List<?> getTraceInfo() {
        return traceInfo;
    }

    public void setTraceInfo(List<?> traceInfo) {
        this.traceInfo = traceInfo;
    }

    public static class DataBean {
        /**
         * id : 76
         * createdTime : 2017-10-20 14:47:12
         * hVersion : 4
         * available : 6
         * type : 2
         * userId : null
         * patientId : 49
         * patientIllnessBasicinfoId : 76
         * doctorId : 2
         * consultationType : 2
         * consultationObjective : 3
         * priority : 1
         * team : 1
         * referralHospital : 1
         * referralDoctor : 23
         * referralPlanDate : 2017-10-28 00:00:00
         * referralStartdate : null
         * referralEnddate : null
         * saveKey : 7414d011-5585-40cd-adcd-6f7a777f366b
         * chiefComplaint : null
         * preliminaryDiagnosis : null
         * illnessDescription : null
         * consultationTypeName : null
         * consultationObjectiveName : null
         * teamName : null
         * referralHospitalName : null
         * referralDoctorName : null
         * content : null
         * medicineHormone : null
         * medicineAntibiotic : null
         * medicineAntiallergic : null
         * medicineExternal : null
         * operation : null
         * picIdcard : null
         * picIllness : null
         * video : null
         * patientName : 赵爽测试
         * doctorName : null
         * doctorGroup : null
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
        private Object chiefComplaint;
        private Object preliminaryDiagnosis;
        private Object illnessDescription;
        private Object consultationTypeName;
        private Object consultationObjectiveName;
        private Object teamName;
        private Object referralHospitalName;
        private Object referralDoctorName;
        private Object content;
        private Object medicineHormone;
        private Object medicineAntibiotic;
        private Object medicineAntiallergic;
        private Object medicineExternal;
        private Object operation;
        private Object picIdcard;
        private Object picIllness;
        private Object video;
        private String patientName;
        private Object doctorName;
        private Object doctorGroup;

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

        public Object getChiefComplaint() {
            return chiefComplaint;
        }

        public void setChiefComplaint(Object chiefComplaint) {
            this.chiefComplaint = chiefComplaint;
        }

        public Object getPreliminaryDiagnosis() {
            return preliminaryDiagnosis;
        }

        public void setPreliminaryDiagnosis(Object preliminaryDiagnosis) {
            this.preliminaryDiagnosis = preliminaryDiagnosis;
        }

        public Object getIllnessDescription() {
            return illnessDescription;
        }

        public void setIllnessDescription(Object illnessDescription) {
            this.illnessDescription = illnessDescription;
        }

        public Object getConsultationTypeName() {
            return consultationTypeName;
        }

        public void setConsultationTypeName(Object consultationTypeName) {
            this.consultationTypeName = consultationTypeName;
        }

        public Object getConsultationObjectiveName() {
            return consultationObjectiveName;
        }

        public void setConsultationObjectiveName(Object consultationObjectiveName) {
            this.consultationObjectiveName = consultationObjectiveName;
        }

        public Object getTeamName() {
            return teamName;
        }

        public void setTeamName(Object teamName) {
            this.teamName = teamName;
        }

        public Object getReferralHospitalName() {
            return referralHospitalName;
        }

        public void setReferralHospitalName(Object referralHospitalName) {
            this.referralHospitalName = referralHospitalName;
        }

        public Object getReferralDoctorName() {
            return referralDoctorName;
        }

        public void setReferralDoctorName(Object referralDoctorName) {
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

        public Object getPicIdcard() {
            return picIdcard;
        }

        public void setPicIdcard(Object picIdcard) {
            this.picIdcard = picIdcard;
        }

        public Object getPicIllness() {
            return picIllness;
        }

        public void setPicIllness(Object picIllness) {
            this.picIllness = picIllness;
        }

        public Object getVideo() {
            return video;
        }

        public void setVideo(Object video) {
            this.video = video;
        }

        public String getPatientName() {
            return patientName;
        }

        public void setPatientName(String patientName) {
            this.patientName = patientName;
        }

        public Object getDoctorName() {
            return doctorName;
        }

        public void setDoctorName(Object doctorName) {
            this.doctorName = doctorName;
        }

        public Object getDoctorGroup() {
            return doctorGroup;
        }

        public void setDoctorGroup(Object doctorGroup) {
            this.doctorGroup = doctorGroup;
        }
    }
}
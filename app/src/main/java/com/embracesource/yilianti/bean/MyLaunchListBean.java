package com.embracesource.yilianti.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/10/17 0017.
 */

public class MyLaunchListBean extends BaseBean {


    /**
     * code : 1111
     * message : success
     * data : {"pageNum":1,"pageSize":2,"size":2,"orderBy":null,"startRow":1,"endRow":2,"total":18,"pages":9,"list":[{"id":74,"createdTime":"2017-10-18 14:34:20","hVersion":1,"available":1,"type":6,"userId":null,"patientId":47,"patientIllnessBasicinfoId":74,"doctorId":2,"consultationType":0,"consultationStringive":3,"priority":10,"team":3,"referralHospital":1,"referralDoctor":10,"referralPlanDate":null,"referralStartdate":null,"referralEnddate":null,"saveKey":null,"chiefComplaint":null,"preliminaryDiagnosis":null,"illnessDescription":"麻婆热破","consultationTypeName":null,"consultationStringiveName":null,"teamName":null,"referralHospitalName":null,"referralDoctorName":null,"content":null,"medicineHormone":null,"medicineAntibiotic":null,"medicineAntiallergic":null,"medicineExternal":null,"operation":null,"picIdcard":null,"picIllness":null,"video":null,"patientName":"你宿舍"},{"id":73,"createdTime":"2017-10-18 14:34:12","hVersion":1,"available":1,"type":6,"userId":null,"patientId":47,"patientIllnessBasicinfoId":73,"doctorId":2,"consultationType":0,"consultationStringive":3,"priority":10,"team":3,"referralHospital":1,"referralDoctor":10,"referralPlanDate":null,"referralStartdate":null,"referralEnddate":null,"saveKey":null,"chiefComplaint":null,"preliminaryDiagnosis":null,"illnessDescription":"麻婆热破","consultationTypeName":null,"consultationStringiveName":null,"teamName":null,"referralHospitalName":null,"referralDoctorName":null,"content":null,"medicineHormone":null,"medicineAntibiotic":null,"medicineAntiallergic":null,"medicineExternal":null,"operation":null,"picIdcard":null,"picIllness":null,"video":null,"patientName":"你宿舍"}],"firstPage":1,"prePage":0,"nextPage":2,"lastPage":8,"isFirstPage":true,"isLastPage":false,"hasPreviousPage":false,"hasNextPage":true,"navigatePages":8,"navigatepageNums":[1,2,3,4,5,6,7,8]}
     * traceInfo : []
     * sessionid : null
     * success : true
     * fail : false
     */

    private String code;
    private String message;
    private DataBean data;
    private String sessionid;
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

    public String getSessionid() {
        return sessionid;
    }

    public void setSessionid(String sessionid) {
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
         * pageNum : 1
         * pageSize : 2
         * size : 2
         * orderBy : null
         * startRow : 1
         * endRow : 2
         * total : 18
         * pages : 9
         * list : [{"id":74,"createdTime":"2017-10-18 14:34:20","hVersion":1,"available":1,"type":6,"userId":null,"patientId":47,"patientIllnessBasicinfoId":74,"doctorId":2,"consultationType":0,"consultationStringive":3,"priority":10,"team":3,"referralHospital":1,"referralDoctor":10,"referralPlanDate":null,"referralStartdate":null,"referralEnddate":null,"saveKey":null,"chiefComplaint":null,"preliminaryDiagnosis":null,"illnessDescription":"麻婆热破","consultationTypeName":null,"consultationStringiveName":null,"teamName":null,"referralHospitalName":null,"referralDoctorName":null,"content":null,"medicineHormone":null,"medicineAntibiotic":null,"medicineAntiallergic":null,"medicineExternal":null,"operation":null,"picIdcard":null,"picIllness":null,"video":null,"patientName":"你宿舍"},{"id":73,"createdTime":"2017-10-18 14:34:12","hVersion":1,"available":1,"type":6,"userId":null,"patientId":47,"patientIllnessBasicinfoId":73,"doctorId":2,"consultationType":0,"consultationStringive":3,"priority":10,"team":3,"referralHospital":1,"referralDoctor":10,"referralPlanDate":null,"referralStartdate":null,"referralEnddate":null,"saveKey":null,"chiefComplaint":null,"preliminaryDiagnosis":null,"illnessDescription":"麻婆热破","consultationTypeName":null,"consultationStringiveName":null,"teamName":null,"referralHospitalName":null,"referralDoctorName":null,"content":null,"medicineHormone":null,"medicineAntibiotic":null,"medicineAntiallergic":null,"medicineExternal":null,"operation":null,"picIdcard":null,"picIllness":null,"video":null,"patientName":"你宿舍"}]
         * firstPage : 1
         * prePage : 0
         * nextPage : 2
         * lastPage : 8
         * isFirstPage : true
         * isLastPage : false
         * hasPreviousPage : false
         * hasNextPage : true
         * navigatePages : 8
         * navigatepageNums : [1,2,3,4,5,6,7,8]
         */

        private int pageNum;
        private int pageSize;
        private int size;
        private String orderBy;
        private int startRow;
        private int endRow;
        private int total;
        private int pages;
        private int firstPage;
        private int prePage;
        private int nextPage;
        private int lastPage;
        private boolean isFirstPage;
        private boolean isLastPage;
        private boolean hasPreviousPage;
        private boolean hasNextPage;
        private int navigatePages;
        private List<ListBean> list;
        private List<Integer> navigatepageNums;

        public int getPageNum() {
            return pageNum;
        }

        public void setPageNum(int pageNum) {
            this.pageNum = pageNum;
        }

        public int getPageSize() {
            return pageSize;
        }

        public void setPageSize(int pageSize) {
            this.pageSize = pageSize;
        }

        public int getSize() {
            return size;
        }

        public void setSize(int size) {
            this.size = size;
        }

        public String getOrderBy() {
            return orderBy;
        }

        public void setOrderBy(String orderBy) {
            this.orderBy = orderBy;
        }

        public int getStartRow() {
            return startRow;
        }

        public void setStartRow(int startRow) {
            this.startRow = startRow;
        }

        public int getEndRow() {
            return endRow;
        }

        public void setEndRow(int endRow) {
            this.endRow = endRow;
        }

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public int getPages() {
            return pages;
        }

        public void setPages(int pages) {
            this.pages = pages;
        }

        public int getFirstPage() {
            return firstPage;
        }

        public void setFirstPage(int firstPage) {
            this.firstPage = firstPage;
        }

        public int getPrePage() {
            return prePage;
        }

        public void setPrePage(int prePage) {
            this.prePage = prePage;
        }

        public int getNextPage() {
            return nextPage;
        }

        public void setNextPage(int nextPage) {
            this.nextPage = nextPage;
        }

        public int getLastPage() {
            return lastPage;
        }

        public void setLastPage(int lastPage) {
            this.lastPage = lastPage;
        }

        public boolean isIsFirstPage() {
            return isFirstPage;
        }

        public void setIsFirstPage(boolean isFirstPage) {
            this.isFirstPage = isFirstPage;
        }

        public boolean isIsLastPage() {
            return isLastPage;
        }

        public void setIsLastPage(boolean isLastPage) {
            this.isLastPage = isLastPage;
        }

        public boolean isHasPreviousPage() {
            return hasPreviousPage;
        }

        public void setHasPreviousPage(boolean hasPreviousPage) {
            this.hasPreviousPage = hasPreviousPage;
        }

        public boolean isHasNextPage() {
            return hasNextPage;
        }

        public void setHasNextPage(boolean hasNextPage) {
            this.hasNextPage = hasNextPage;
        }

        public int getNavigatePages() {
            return navigatePages;
        }

        public void setNavigatePages(int navigatePages) {
            this.navigatePages = navigatePages;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public List<Integer> getNavigatepageNums() {
            return navigatepageNums;
        }

        public void setNavigatepageNums(List<Integer> navigatepageNums) {
            this.navigatepageNums = navigatepageNums;
        }

        public static class ListBean {
            /**
             * id : 74
             * createdTime : 2017-10-18 14:34:20
             * hVersion : 1
             * available : 1
             * type : 6
             * userId : null
             * patientId : 47
             * patientIllnessBasicinfoId : 74
             * doctorId : 2
             * consultationType : 0
             * consultationStringive : 3
             * priority : 10
             * team : 3
             * referralHospital : 1
             * referralDoctor : 10
             * referralPlanDate : null
             * referralStartdate : null
             * referralEnddate : null
             * saveKey : null
             * chiefComplaint : null
             * preliminaryDiagnosis : null
             * illnessDescription : 麻婆热破
             * consultationTypeName : null
             * consultationStringiveName : null
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
             * patientName : 你宿舍
             */

            private int id;
            private String createdTime;
            private int hVersion;
            private int available;
            private int type;
            private String userId;
            private int patientId;
            private int patientIllnessBasicinfoId;
            private int doctorId;
            private int consultationType;
            private int consultationStringive;
            private int priority;
            private int team;
            private int referralHospital;
            private int referralDoctor;
            private String referralPlanDate;
            private String referralStartdate;
            private String referralEnddate;
            private String saveKey;
            private String chiefComplaint;
            private String preliminaryDiagnosis;
            private String illnessDescription;
            private String consultationTypeName;
            private String consultationStringiveName;
            private String teamName;
            private String referralHospitalName;
            private String referralDoctorName;
            private String content;
            private String medicineHormone;
            private String medicineAntibiotic;
            private String medicineAntiallergic;
            private String medicineExternal;
            private String operation;
            private String picIdcard;
            private String picIllness;
            private String video;
            private String patientName;

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

            public String getUserId() {
                return userId;
            }

            public void setUserId(String userId) {
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

            public int getConsultationStringive() {
                return consultationStringive;
            }

            public void setConsultationStringive(int consultationStringive) {
                this.consultationStringive = consultationStringive;
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

            public String getConsultationStringiveName() {
                return consultationStringiveName;
            }

            public void setConsultationStringiveName(String consultationStringiveName) {
                this.consultationStringiveName = consultationStringiveName;
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

            public String getPicIdcard() {
                return picIdcard;
            }

            public void setPicIdcard(String picIdcard) {
                this.picIdcard = picIdcard;
            }

            public String getPicIllness() {
                return picIllness;
            }

            public void setPicIllness(String picIllness) {
                this.picIllness = picIllness;
            }

            public String getVideo() {
                return video;
            }

            public void setVideo(String video) {
                this.video = video;
            }

            public String getPatientName() {
                return patientName;
            }

            public void setPatientName(String patientName) {
                this.patientName = patientName;
            }
        }
    }

    @Override
    public String toString() {
        return "MyLaunchListBean{" +
                "code='" + code + '\'' +
                ", message='" + message + '\'' +
                ", data=" + data +
                ", sessionid='" + sessionid + '\'' +
                ", success=" + success +
                ", fail=" + fail +
                ", traceInfo=" + traceInfo +
                '}';
    }
}

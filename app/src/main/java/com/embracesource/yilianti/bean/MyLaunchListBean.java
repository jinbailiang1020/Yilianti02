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
        private List<DiagnosisItemBean> list;
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

        public List<DiagnosisItemBean> getList() {
            return list;
        }

        public void setList(List<DiagnosisItemBean> list) {
            this.list = list;
        }

        public List<Integer> getNavigatepageNums() {
            return navigatepageNums;
        }

        public void setNavigatepageNums(List<Integer> navigatepageNums) {
            this.navigatepageNums = navigatepageNums;
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

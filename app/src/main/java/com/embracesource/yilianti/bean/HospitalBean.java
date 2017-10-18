package com.embracesource.yilianti.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/10/18 0018.
 */

public class HospitalBean   {


    /**
     * code : 1111
     * message : success
     * data : {"pageNum":1,"pageSize":10,"size":10,"orderBy":null,"startRow":1,"endRow":10,"total":192,"pages":20,"list":[{"id":1,"createdTime":"2017-09-11 14:43:02","hVersion":1,"available":1,"name":"湘雅二医院","code":"","description":"湘雅附二","level":1,"parentId":0,"parentName":null},{"id":2,"createdTime":"2017-09-11 15:22:02","hVersion":1,"available":1,"name":"长沙市中心医院","code":null,"description":"长沙市中心医院","level":1,"parentId":1,"parentName":"湘雅二医院"},{"id":3,"createdTime":"2017-09-11 15:22:23","hVersion":1,"available":1,"name":"长沙市三医院","code":null,"description":"长沙市三医院","level":1,"parentId":1,"parentName":"湘雅二医院"},{"id":4,"createdTime":"2017-09-07 00:55:38","hVersion":1,"available":1,"name":"附二医务处","code":null,"description":"湘雅附二医务处","level":2,"parentId":1,"parentName":"湘雅二医院"},{"id":5,"createdTime":"2017-09-07 00:55:44","hVersion":1,"available":1,"name":"附二皮肤科","code":null,"description":"湘雅附二皮肤科","level":2,"parentId":1,"parentName":"湘雅二医院"},{"id":6,"createdTime":"2017-09-11 15:36:38","hVersion":1,"available":1,"name":"三医院皮肤科","code":null,"description":"三医院皮肤科","level":2,"parentId":3,"parentName":"长沙市三医院"},{"id":7,"createdTime":"2017-09-12 01:02:04","hVersion":1,"available":1,"name":"三医院医务处","code":null,"description":"三医院医务处","level":2,"parentId":3,"parentName":"长沙市三医院"},{"id":8,"createdTime":"2017-10-16 17:45:06","hVersion":1,"available":1,"name":"安仁县人民医院","code":"","description":"安仁县人民医院","level":1,"parentId":1,"parentName":"湘雅二医院"},{"id":9,"createdTime":"2017-10-16 17:45:06","hVersion":1,"available":1,"name":"安仁县中医医院","code":"","description":"安仁县中医医院","level":1,"parentId":1,"parentName":"湘雅二医院"},{"id":10,"createdTime":"2017-10-16 17:45:06","hVersion":1,"available":1,"name":"安乡县潺陵医院","code":"","description":"安乡县潺陵医院","level":1,"parentId":1,"parentName":"湘雅二医院"}],"firstPage":1,"prePage":0,"nextPage":2,"lastPage":8,"isFirstPage":true,"isLastPage":false,"hasPreviousPage":false,"hasNextPage":true,"navigatePages":8,"navigatepageNums":[1,2,3,4,5,6,7,8]}
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
         * pageNum : 1
         * pageSize : 10
         * size : 10
         * orderBy : null
         * startRow : 1
         * endRow : 10
         * total : 192
         * pages : 20
         * list : [{"id":1,"createdTime":"2017-09-11 14:43:02","hVersion":1,"available":1,"name":"湘雅二医院","code":"","description":"湘雅附二","level":1,"parentId":0,"parentName":null},{"id":2,"createdTime":"2017-09-11 15:22:02","hVersion":1,"available":1,"name":"长沙市中心医院","code":null,"description":"长沙市中心医院","level":1,"parentId":1,"parentName":"湘雅二医院"},{"id":3,"createdTime":"2017-09-11 15:22:23","hVersion":1,"available":1,"name":"长沙市三医院","code":null,"description":"长沙市三医院","level":1,"parentId":1,"parentName":"湘雅二医院"},{"id":4,"createdTime":"2017-09-07 00:55:38","hVersion":1,"available":1,"name":"附二医务处","code":null,"description":"湘雅附二医务处","level":2,"parentId":1,"parentName":"湘雅二医院"},{"id":5,"createdTime":"2017-09-07 00:55:44","hVersion":1,"available":1,"name":"附二皮肤科","code":null,"description":"湘雅附二皮肤科","level":2,"parentId":1,"parentName":"湘雅二医院"},{"id":6,"createdTime":"2017-09-11 15:36:38","hVersion":1,"available":1,"name":"三医院皮肤科","code":null,"description":"三医院皮肤科","level":2,"parentId":3,"parentName":"长沙市三医院"},{"id":7,"createdTime":"2017-09-12 01:02:04","hVersion":1,"available":1,"name":"三医院医务处","code":null,"description":"三医院医务处","level":2,"parentId":3,"parentName":"长沙市三医院"},{"id":8,"createdTime":"2017-10-16 17:45:06","hVersion":1,"available":1,"name":"安仁县人民医院","code":"","description":"安仁县人民医院","level":1,"parentId":1,"parentName":"湘雅二医院"},{"id":9,"createdTime":"2017-10-16 17:45:06","hVersion":1,"available":1,"name":"安仁县中医医院","code":"","description":"安仁县中医医院","level":1,"parentId":1,"parentName":"湘雅二医院"},{"id":10,"createdTime":"2017-10-16 17:45:06","hVersion":1,"available":1,"name":"安乡县潺陵医院","code":"","description":"安乡县潺陵医院","level":1,"parentId":1,"parentName":"湘雅二医院"}]
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
        private Object orderBy;
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

        public Object getOrderBy() {
            return orderBy;
        }

        public void setOrderBy(Object orderBy) {
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
             * id : 1
             * createdTime : 2017-09-11 14:43:02
             * hVersion : 1
             * available : 1
             * name : 湘雅二医院
             * code :
             * description : 湘雅附二
             * level : 1
             * parentId : 0
             * parentName : null
             */

            private int id;
            private String createdTime;
            private int hVersion;
            private int available;
            private String name;
            private String code;
            private String description;
            private int level;
            private int parentId;
            private Object parentName;

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

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getCode() {
                return code;
            }

            public void setCode(String code) {
                this.code = code;
            }

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
            }

            public int getLevel() {
                return level;
            }

            public void setLevel(int level) {
                this.level = level;
            }

            public int getParentId() {
                return parentId;
            }

            public void setParentId(int parentId) {
                this.parentId = parentId;
            }

            public Object getParentName() {
                return parentName;
            }

            public void setParentName(Object parentName) {
                this.parentName = parentName;
            }
        }
    }
}

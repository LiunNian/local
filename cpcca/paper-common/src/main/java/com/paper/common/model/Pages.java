package com.paper.common.model;

import java.io.Serializable;

public class Pages implements Serializable {

    private int pageNum; //第几页
    private int pageSize; // 分页显示条数
    private long total; // 总条数
    private  Object data; //数据

    public Pages(){}

    public Pages(Object data ) {
        this.data = data;
}

    public Pages(int pageNum, int pageSize) {
        this.pageNum = pageNum;
        this.pageSize = pageSize;
    }

    public Pages(int pageNum, int pageSize, long total, Object data) {
        this.pageNum = pageNum;
        this.pageSize = pageSize;
        this.total = total;
        this.data = data;
    }


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

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

}

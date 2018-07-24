package com.paper.common.model;

import java.io.Serializable;

public class Page implements Serializable {
    private static final long serialVersionUID = 2984161209204439302L;

    private Integer pageNum;

    private Integer pageSize;

    public Page(){}

    public Page(Integer pageNum, Integer pageSize) {
        this.pageNum = pageNum;
        this.pageSize = pageSize;
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
}

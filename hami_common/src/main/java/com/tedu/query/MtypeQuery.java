package com.tedu.query;

import com.tedu.model.Mtype;

public class MtypeQuery extends Mtype{

    private Integer pageSize = 5;

    private Integer pageNo;

    private Integer startNum;

    private Integer pageNoPortal=1;

    public Integer getPageNoPortal() {
        return pageNoPortal;
    }

    public void setPageNoPortal(Integer pageNoPortal) {
        this.pageNoPortal = pageNoPortal;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public Integer getStartNum() {
        return startNum;
    }

    public void setStartNum(Integer startNum) {
        this.startNum = startNum;
    }
}

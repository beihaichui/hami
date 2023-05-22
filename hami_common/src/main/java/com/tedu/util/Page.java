package com.tedu.util;

import java.util.List;
//当前页码pageNo  每页展示的数量pageSize   开始行号startNum
// 每页的数据集合List<Mtype>  一共多少页totalpage  一共多少条数据totalcount
public class Page<T> {

    /**
     * 页码（已知，从前台页面传递过来）
     */
    private int pageNo = 1;
    /**
     * 开始行号（未知，需要计算）
     */
    private int startNum = 0;
    /**
     * 每页的记录数(已知）
     */
    private int pageSize = 5;
    /**
     * 指定的条件下的总页数(未知，需要计算）
     */
    private int totalPage = 1;
    /**
     * 指定条件下的总记录数（未知, 查询数据库获得）
     */
    private int totalCount = 0;
    /**
     * 结果集
     */
    private List<T> list;

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public int getStartNum() {
        return (pageNo - 1) * pageSize;
    }

    public void setStartNum(int startNum) {
        this.startNum = startNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotalPage() {
        totalPage = totalCount/pageSize;
        if(totalCount == 0 || totalCount%pageSize != 0){
            totalPage++;
        }
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }


    @Override
    public String toString() {
        return "Page{" +
                "pageNo=" + pageNo +
                ", startNum=" + startNum +
                ", pageSize=" + pageSize +
                ", totalPage=" + totalPage +
                ", totalCount=" + totalCount +
                ", list=" + list +
                '}';
    }
}

package com.ricelink.interfaceService.ipad.pojo;

/**
 * Created by Administrator on 2017/7/31.
 * 分页实体
 */
public class PageInfo {

    private String begin;

    private Integer length;

    private Integer count;

    private Integer totalPage;

    private Integer currentPage;

    private Boolean isCount;

    private Boolean isFirst;

    private Boolean isLast;

    private Integer size;

    public String getBegin() {
        return begin;
    }

    public void setBegin(String begin) {
        this.begin = begin;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public void setIsCount(Boolean isCount) {
        this.isCount = isCount;
    }

    public Boolean getIsCount() {
        return isCount;
    }

    public Boolean getIsFirst() {
        return isFirst;
    }

    public void setIsFirst(Boolean first) {
        isFirst = first;
    }

    public Boolean getIsLast() {
        return isLast;
    }

    public void setIsLast(Boolean last) {
        isLast = last;
    }
}

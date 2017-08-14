package com.ricelink.interfaceService.ipad.pojo;

/**
 * Created by Administrator on 2017/8/10.
 * 商机查询条件
 */
public class Condition {
    //商机状态=
    private String currentStatus;
    //客户姓名like
    private String customerName;
    //电话=
    private String phone;
    //订单号=
    private String orderCode;
    //导购like
    private String guide;
    //设计师like
    private String designer;
    //门店名like
    private String orgName;
    //当前页
    private Integer nowpage;
    //每页数量
    private Integer pagesize;
    //潜客录入时间
    private String createMin;
    private String createMax;

    public String getCurrentStatus() {
        return currentStatus;
    }

    public void setCurrentStatus(String currentStatus) {
        this.currentStatus = currentStatus;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public String getGuide() {
        return guide;
    }

    public void setGuide(String guide) {
        this.guide = guide;
    }

    public String getDesigner() {
        return designer;
    }

    public void setDesigner(String designer) {
        this.designer = designer;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public Integer getNowpage() {
        return nowpage;
    }

    public void setNowpage(Integer nowpage) {
        this.nowpage = nowpage;
    }

    public Integer getPagesize() {
        return pagesize;
    }

    public void setPagesize(Integer pagesize) {
        this.pagesize = pagesize;
    }

    public String getCreateMin() {
        return createMin;
    }

    public void setCreateMin(String createMin) {
        this.createMin = createMin;
    }

    public String getCreateMax() {
        return createMax;
    }

    public void setCreateMax(String createMax) {
        this.createMax = createMax;
    }
}

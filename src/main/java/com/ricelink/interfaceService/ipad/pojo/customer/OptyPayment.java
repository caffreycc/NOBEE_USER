package com.ricelink.interfaceService.ipad.pojo.customer;

/**
 * Created by Administrator on 2017/8/2.
 * 付款记录
 */
public class OptyPayment {
    private String amount;
    private String createdBy;
    private Long creationDate;
    private Long lastUpdateDate;
    private String lastUpdatedBy;
    private String optyId;
    private String payId;
    private Long payTime;
    private String payVouNo;
    private String payWay;
    private String recordStatus;
    private String status;
    private String type;
    private String hdCouponNumber;
    private Integer versionNumber;

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Long getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Long creationDate) {
        this.creationDate = creationDate;
    }

    public Long getLastUpdateDate() {
        return lastUpdateDate;
    }

    public void setLastUpdateDate(Long lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }

    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    public String getOptyId() {
        return optyId;
    }

    public void setOptyId(String optyId) {
        this.optyId = optyId;
    }

    public String getPayId() {
        return payId;
    }

    public void setPayId(String payId) {
        this.payId = payId;
    }

    public Long getPayTime() {
        return payTime;
    }

    public void setPayTime(Long payTime) {
        this.payTime = payTime;
    }

    public String getPayVouNo() {
        return payVouNo;
    }

    public void setPayVouNo(String payVouNo) {
        this.payVouNo = payVouNo;
    }

    public String getPayWay() {
        return payWay;
    }

    public void setPayWay(String payWay) {
        this.payWay = payWay;
    }

    public String getRecordStatus() {
        return recordStatus;
    }

    public void setRecordStatus(String recordStatus) {
        this.recordStatus = recordStatus;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getHdCouponNumber() {
        return hdCouponNumber;
    }

    public void setHdCouponNumber(String hdCouponNumber) {
        this.hdCouponNumber = hdCouponNumber;
    }

    public Integer getVersionNumber() {
        return versionNumber;
    }

    public void setVersionNumber(Integer versionNumber) {
        this.versionNumber = versionNumber;
    }
}

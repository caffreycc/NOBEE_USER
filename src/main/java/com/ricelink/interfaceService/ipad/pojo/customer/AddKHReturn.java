package com.ricelink.interfaceService.ipad.pojo.customer;

/**
 * Created by Administrator on 2017/8/4.
 * 新增潜客EOS返回信息
 */
public class AddKHReturn {
    private String companyID;
    private Boolean success;

    public String getCompanyID() {
        return companyID;
    }

    public void setCompanyID(String companyID) {
        this.companyID = companyID;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }
}

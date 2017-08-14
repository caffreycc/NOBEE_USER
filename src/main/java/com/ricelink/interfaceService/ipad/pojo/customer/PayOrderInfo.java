package com.ricelink.interfaceService.ipad.pojo.customer;

/**
 * Created by Administrator on 2017/8/2.
 */
public class PayOrderInfo {
    private String payCode;
    private PayOrderSubInfo rec;

    public String getPayCode() {
        return payCode;
    }

    public void setPayCode(String payCode) {
        this.payCode = payCode;
    }

    public PayOrderSubInfo getRec() {
        return rec;
    }

    public void setRec(PayOrderSubInfo rec) {
        this.rec = rec;
    }
}

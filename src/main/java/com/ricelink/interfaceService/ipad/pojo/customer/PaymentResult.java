package com.ricelink.interfaceService.ipad.pojo.customer;

/**
 * Created by Administrator on 2017/8/3.
 * 付款结果
 */
public class PaymentResult {
    public static String SUCCESS = "1";
    public static String FAILED = "0";
    private String returnc;
    private String returnm;

    public PaymentResult(String returnc, String returnm) {
        this.returnc = returnc;
        this.returnm = returnm;
    }

    public PaymentResult(){}

    public String getReturnc() {
        return returnc;
    }

    public void setReturnc(String returnc) {
        this.returnc = returnc;
    }

    public String getReturnm() {
        return returnm;
    }

    public void setReturnm(String returnm) {
        this.returnm = returnm;
    }
}

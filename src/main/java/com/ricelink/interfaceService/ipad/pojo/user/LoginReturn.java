package com.ricelink.interfaceService.ipad.pojo.user;

/**
 * Created by Administrator on 2017/8/8.
 * 登录返回信息
 */
public class LoginReturn {
    private Integer retCode;
    private Uo uo;

    public Integer getRetCode() {
        return retCode;
    }

    public void setRetCode(Integer retCode) {
        this.retCode = retCode;
    }

    public Uo getUo() {
        return uo;
    }

    public void setUo(Uo uo) {
        this.uo = uo;
    }
}

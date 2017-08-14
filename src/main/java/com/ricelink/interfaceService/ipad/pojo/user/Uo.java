package com.ricelink.interfaceService.ipad.pojo.user;

/**
 * Created by Administrator on 2017/8/8.
 */
public class Uo {
    private Attribute attributes;
    private String sessionId;
    private String uniqueId;
    private String userId;
    private String userMail;
    private String userName;
    private String userOrgId;
    private String userOrgName;
    private String userRealName;
    private String userRemoteIP;

    public Attribute getAttributes() {
        return attributes;
    }

    public void setAttributes(Attribute attribute) {
        this.attributes = attribute;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getUniqueId() {
        return uniqueId;
    }

    public void setUniqueId(String uniqueId) {
        this.uniqueId = uniqueId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserMail() {
        return userMail;
    }

    public void setUserMail(String userMail) {
        this.userMail = userMail;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserOrgId() {
        return userOrgId;
    }

    public void setUserOrgId(String userOrgId) {
        this.userOrgId = userOrgId;
    }

    public String getUserOrgName() {
        return userOrgName;
    }

    public void setUserOrgName(String userOrgName) {
        this.userOrgName = userOrgName;
    }

    public String getUserRealName() {
        return userRealName;
    }

    public void setUserRealName(String userRealName) {
        this.userRealName = userRealName;
    }

    public String getUserRemoteIP() {
        return userRemoteIP;
    }

    public void setUserRemoteIP(String userRemoteIP) {
        this.userRemoteIP = userRemoteIP;
    }
}

package interfaceService.ipad.pojo.user;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Created by Administrator on 2017/8/8.
 */
public class Attribute {
    @JsonProperty("EXTEND_USER_ID")
    private String extendUserId;
    @JsonProperty("TENANT_ID")
    private String tenantId;
    private String authmode;
    private String empid;
    private String empname;
    private List<FunctionButton> functionButtonAuth;
    private String orgid;
    private List orglist;
    private String orgname;
    private String orgseq;
    private String parentOrgIds;
    private String password;
    private String password2;
    private String posid;
    private List reslist;
    private String roleList;
    private String sysChanel;
    private String userType;

    public String getExtendUserId() {
        return extendUserId;
    }

    public void setExtendUserId(String extendUserId) {
        this.extendUserId = extendUserId;
    }

    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    public String getAuthmode() {
        return authmode;
    }

    public void setAuthmode(String authmode) {
        this.authmode = authmode;
    }

    public String getEmpid() {
        return empid;
    }

    public void setEmpid(String empid) {
        this.empid = empid;
    }

    public String getEmpname() {
        return empname;
    }

    public void setEmpname(String empname) {
        this.empname = empname;
    }

    public List<FunctionButton> getFunctionButtonAuth() {
        return functionButtonAuth;
    }

    public void setFunctionButtonAuth(List<FunctionButton> functionButtonAuth) {
        this.functionButtonAuth = functionButtonAuth;
    }

    public String getOrgid() {
        return orgid;
    }

    public void setOrgid(String orgid) {
        this.orgid = orgid;
    }

    public List getOrglist() {
        return orglist;
    }

    public void setOrglist(List orglist) {
        this.orglist = orglist;
    }

    public String getOrgname() {
        return orgname;
    }

    public void setOrgname(String orgname) {
        this.orgname = orgname;
    }

    public String getOrgseq() {
        return orgseq;
    }

    public void setOrgseq(String orgseq) {
        this.orgseq = orgseq;
    }

    public String getParentOrgIds() {
        return parentOrgIds;
    }

    public void setParentOrgIds(String parentOrgIds) {
        this.parentOrgIds = parentOrgIds;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword2() {
        return password2;
    }

    public void setPassword2(String password2) {
        this.password2 = password2;
    }

    public String getPosid() {
        return posid;
    }

    public void setPosid(String posid) {
        this.posid = posid;
    }

    public List getReslist() {
        return reslist;
    }

    public void setReslist(List reslist) {
        this.reslist = reslist;
    }

    public String getRoleList() {
        return roleList;
    }

    public void setRoleList(String roleList) {
        this.roleList = roleList;
    }

    public String getSysChanel() {
        return sysChanel;
    }

    public void setSysChanel(String sysChanel) {
        this.sysChanel = sysChanel;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }
}

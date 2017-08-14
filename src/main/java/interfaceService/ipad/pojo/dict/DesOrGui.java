package interfaceService.ipad.pojo.dict;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by Administrator on 2017/8/8.
 * 设计师和导购实体
 */
public class DesOrGui {
    private String userId;
    private String name;
    private String phone;
    private String email;
    private String reporttoUserId;
    private String orgId;
    private String roleCode;
    private String createdBy;
    private String creationDate;
    private String recordStatus;

    @JsonProperty("userId")
    public String getUserId() {
        return userId;
    }

    @JsonProperty("USER_ID")
    public void setUserId(String userId) {
        this.userId = userId;
    }

    @JsonProperty("nick")
    public String getName() {
        return name;
    }

    @JsonProperty("EMP_NAME")
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("phone")
    public String getPhone() {
        return phone;
    }

    @JsonProperty("PHONE")
    public void setPhone(String phone) {
        this.phone = phone;
    }

    @JsonProperty("email")
    public String getEmail() {
        return email;
    }

    @JsonProperty("EMAIL")
    public void setEmail(String email) {
        this.email = email;
    }

    @JsonProperty("reporttoUserId")
    public String getReporttoUserId() {
        return reporttoUserId;
    }

    @JsonProperty("REPORTTO_USERID")
    public void setReporttoUserId(String reporttoUserId) {
        this.reporttoUserId = reporttoUserId;
    }

    @JsonProperty("orgId")
    public String getOrgId() {
        return orgId;
    }

    @JsonProperty("ORG_ID")
    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    @JsonProperty("roleCode")
    public String getRoleCode() {
        return roleCode;
    }

    @JsonProperty("POSITION_ID")
    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }

    @JsonProperty("createdBy")
    public String getCreatedBy() {
        return createdBy;
    }

    @JsonProperty("CREATEUSER")
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    @JsonProperty("creationDate")
    public String getCreationDate() {
        return creationDate;
    }

    @JsonProperty("CREATETIME")
    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

   /* @JsonProperty("recordStatus")
    public String getRecordStatus() {
        return recordStatus;
    }*/

    @JsonProperty("STATUS")
    public void setRecordStatus(String recordStatus) {
        this.recordStatus = recordStatus;
    }

    @JsonProperty("ADDER")
    public void setAdder(String adder){}

    @JsonProperty("EMP_TYPE")
    public void setEmptype(String emptype){}

    @JsonProperty("ORGIDLIST")
    public void setOrgIdList(String orgIdList){}

    @JsonProperty("SEX")
    public void setSex(String sex){}

    @JsonProperty("SPECIALTY")
    public void setSpecialty(String specialty){}

    @JsonProperty("OPERATOR_ID")
    public void setOperatorId(String operatorId){}
}

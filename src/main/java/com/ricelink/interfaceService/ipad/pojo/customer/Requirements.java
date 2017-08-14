package com.ricelink.interfaceService.ipad.pojo.customer;

/**
 * Created by Administrator on 2017/8/10.
 * 客户购买需求
 */
public class Requirements {
    // 记录人
    private String createdBy;
    // 记录时间
    private Long creationDate;
    //	购买数量
    private Integer functionQty;
    //	商机id
    private String opportunityId;
    //	添加（VALID）删除（INVALID）
    private String recordStatus;
    //	需求描述
    private String requirementDescription;
    //	需求功能
    private String requirementFunction;
    //	需求id
    private String requirementId;
    //	需求类型
    private String requirementType;

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

    public Integer getFunctionQty() {
        return functionQty;
    }

    public void setFunctionQty(Integer functionQty) {
        this.functionQty = functionQty;
    }

    public String getOpportunityId() {
        return opportunityId;
    }

    public void setOpportunityId(String opportunityId) {
        this.opportunityId = opportunityId;
    }

    public String getRecordStatus() {
        return recordStatus;
    }

    public void setRecordStatus(String recordStatus) {
        this.recordStatus = recordStatus;
    }

    public String getRequirementDescription() {
        return requirementDescription;
    }

    public void setRequirementDescription(String requirementDescription) {
        this.requirementDescription = requirementDescription;
    }

    public String getRequirementFunction() {
        return requirementFunction;
    }

    public void setRequirementFunction(String requirementFunction) {
        this.requirementFunction = requirementFunction;
    }

    public String getRequirementId() {
        return requirementId;
    }

    public void setRequirementId(String requirementId) {
        this.requirementId = requirementId;
    }

    public String getRequirementType() {
        return requirementType;
    }

    public void setRequirementType(String requirementType) {
        this.requirementType = requirementType;
    }
}

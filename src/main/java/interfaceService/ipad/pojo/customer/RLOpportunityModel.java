package interfaceService.ipad.pojo.customer;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by Administrator on 2017/8/2.
 * 商机实体类
 */
public class RLOpportunityModel {
    // 客人姓名
    private String customerName;
    // 联系电话
    private String customerPhone;
    // 客人性别
    private String customerSex;
    // 电子邮箱
    private String customerEmail;
    // 是否绑定微信
    private String customerBindWeChat;
    // 其他联系人
    private String connectName;
    private String connectSex;
    private String connectPhone;
    private String connectComment;
    // 行政区县
    private String addressCounty;
    // 楼盘分区
    private String addressArea;
    // 地址详情
    private String addressDetail;
    // 订单编号
    private String orderCode;
    // 希望测量时间
    private String expectMeasureDate;
    // 希望安装时间
    private String expectInstallDate;
    // 房屋户型
    private String houseTypeCode;
    // 装修风格
    private String decorationStyleCode;
    // 装修进度
    private String decorationProgressCode;

    // 客人来源
    private String customerSource;
    // 二级来源
    private String secondSource;
    // 客人职业
    private String job;
    // 客人年龄段
    private String age;
    // 装修方式
    private String decorationMethodCode;
    // 大致预算
    private String budget;
    // 客人特征
    private String customerFeature;

    // 商机id
    private String opportunityId;
    // 客人id
    private String customerId;
    // 导购id
    private String guideId;
    // 设计师id
    private String designerId;
    // 导购姓名
    private String guideName;
    // 设计师姓名
    private String designerName;
    // 定金状态
    private String depositFlag;
    // 交定金时间
    private String depositDate;
    // 定金金额
    private String depositAmount;
    // 实际金额
    private double actualAmount;

    // 客人创建日期
    private String createCus;
    // 提交测量
    private String createOrder;
    private String createOrdered;
    // 上门测量
    private String measure;
    private String measured;
    // 设计出图
    private String draw;
    private String drawed;
    // 确图签约
    private String commitDraw;
    private String commitDrawed;
    // 门店下单
    private String commitOrder;
    private String commitOrdered;
    // 工厂审图
    private String checkDraw;
    private String checkDrawed;
    // 区域排单
    private String issuedOrderDate;
    // 工厂出货
    private String produce;
    private String produced;
    // 上门安装
    private String execute;
    private String executed;
    // 客户回访
    private String interview;
    private String interviewed;
    // 退单
    private String chargeBackTime;
    private String chargeBackReason;
    private String chargeBackComment;
    // 经销商id
    private String dealer;
    // 组织id
    private String orgId;
    // 商机状态
    private String currentStatus;

    //操作
    private String operating;

    private String addressCell;
    private String customerPhone1;
    private String customerPhone2;

    public String getOperating() {
        return operating;
    }

    public void setOperating(String operating) {
        this.operating = operating;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    @JsonProperty("customerPhone")
    public String getCustomerPhone() {
        return customerPhone;
    }

    @JsonProperty("customerPhone1")
    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }

    public String getCustomerSex() {
        return customerSex;
    }

    public void setCustomerSex(String customerSex) {
        this.customerSex = customerSex;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public String getCustomerBindWeChat() {
        return customerBindWeChat;
    }

    public void setCustomerBindWeChat(String customerBindWeChat) {
        this.customerBindWeChat = customerBindWeChat;
    }

    public String getConnectName() {
        return connectName;
    }

    public void setConnectName(String connectName) {
        this.connectName = connectName;
    }

    public String getConnectSex() {
        return connectSex;
    }

    public void setConnectSex(String connectSex) {
        this.connectSex = connectSex;
    }

    public String getConnectPhone() {
        return connectPhone;
    }

    public void setConnectPhone(String connectPhone) {
        this.connectPhone = connectPhone;
    }

    public String getConnectComment() {
        return connectComment;
    }

    public void setConnectComment(String connectComment) {
        this.connectComment = connectComment;
    }

    public String getAddressCounty() {
        return addressCounty;
    }

    public void setAddressCounty(String addressCounty) {
        this.addressCounty = addressCounty;
    }

    public String getAddressArea() {
        return addressArea;
    }

    public void setAddressArea(String addressArea) {
        this.addressArea = addressArea;
    }

    public String getAddressDetail() {
        return addressDetail;
    }

    public void setAddressDetail(String addressDetail) {
        this.addressDetail = addressDetail;
    }

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public String getExpectMeasureDate() {
        return expectMeasureDate;
    }

    public void setExpectMeasureDate(String expectMeasureDate) {
        this.expectMeasureDate = expectMeasureDate;
    }

    public String getExpectInstallDate() {
        return expectInstallDate;
    }

    public void setExpectInstallDate(String expectInstallDate) {
        this.expectInstallDate = expectInstallDate;
    }

    public String getHouseTypeCode() {
        return houseTypeCode;
    }

    public void setHouseTypeCode(String houseTypeCode) {
        this.houseTypeCode = houseTypeCode;
    }

    public String getDecorationStyleCode() {
        return decorationStyleCode;
    }

    public void setDecorationStyleCode(String decorationStyleCode) {
        this.decorationStyleCode = decorationStyleCode;
    }

    public String getDecorationProgressCode() {
        return decorationProgressCode;
    }

    public void setDecorationProgressCode(String decorationProgressCode) {
        this.decorationProgressCode = decorationProgressCode;
    }

    public String getCustomerSource() {
        return customerSource;
    }

    public void setCustomerSource(String customerSource) {
        this.customerSource = customerSource;
    }

    public String getSecondSource() {
        return secondSource;
    }

    public void setSecondSource(String secondSource) {
        this.secondSource = secondSource;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getDecorationMethodCode() {
        return decorationMethodCode;
    }

    public void setDecorationMethodCode(String decorationMethodCode) {
        this.decorationMethodCode = decorationMethodCode;
    }

    public String getBudget() {
        return budget;
    }

    public void setBudget(String budget) {
        this.budget = budget;
    }

    public String getCustomerFeature() {
        return customerFeature;
    }

    public void setCustomerFeature(String customerFeature) {
        this.customerFeature = customerFeature;
    }

    public String getOpportunityId() {
        return opportunityId;
    }

    public void setOpportunityId(String opportunityId) {
        this.opportunityId = opportunityId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getGuideId() {
        return guideId;
    }

    public void setGuideId(String guideId) {
        this.guideId = guideId;
    }

    public String getDesignerId() {
        return designerId;
    }

    public void setDesignerId(String designerId) {
        this.designerId = designerId;
    }

    public String getGuideName() {
        return guideName;
    }

    public void setGuideName(String guideName) {
        this.guideName = guideName;
    }

    public String getDesignerName() {
        return designerName;
    }

    public void setDesignerName(String designerName) {
        this.designerName = designerName;
    }

    public String getDepositFlag() {
        return depositFlag;
    }

    public void setDepositFlag(String depositFlag) {
        this.depositFlag = depositFlag;
    }

    public String getDepositDate() {
        return depositDate;
    }

    public void setDepositDate(String depositDate) {
        this.depositDate = depositDate;
    }

    public String getDepositAmount() {
        return depositAmount;
    }

    public void setDepositAmount(String depositAmount) {
        this.depositAmount = depositAmount;
    }

    public double getActualAmount() {
        return actualAmount;
    }

    public void setActualAmount(double actualAmount) {
        this.actualAmount = actualAmount;
    }

    public String getCreateCus() {
        return createCus;
    }

    public void setCreateCus(String createCus) {
        this.createCus = createCus;
    }

    public String getCreateOrder() {
        return createOrder;
    }

    public void setCreateOrder(String createOrder) {
        this.createOrder = createOrder;
    }

    public String getCreateOrdered() {
        return createOrdered;
    }

    public void setCreateOrdered(String createOrdered) {
        this.createOrdered = createOrdered;
    }

    public String getMeasure() {
        return measure;
    }

    public void setMeasure(String measure) {
        this.measure = measure;
    }

    public String getMeasured() {
        return measured;
    }

    public void setMeasured(String measured) {
        this.measured = measured;
    }

    public String getDraw() {
        return draw;
    }

    public void setDraw(String draw) {
        this.draw = draw;
    }

    public String getDrawed() {
        return drawed;
    }

    public void setDrawed(String drawed) {
        this.drawed = drawed;
    }

    public String getCommitDraw() {
        return commitDraw;
    }

    public void setCommitDraw(String commitDraw) {
        this.commitDraw = commitDraw;
    }

    public String getCommitDrawed() {
        return commitDrawed;
    }

    public void setCommitDrawed(String commitDrawed) {
        this.commitDrawed = commitDrawed;
    }

    public String getCommitOrder() {
        return commitOrder;
    }

    public void setCommitOrder(String commitOrder) {
        this.commitOrder = commitOrder;
    }

    public String getCommitOrdered() {
        return commitOrdered;
    }

    public void setCommitOrdered(String commitOrdered) {
        this.commitOrdered = commitOrdered;
    }

    public String getCheckDraw() {
        return checkDraw;
    }

    public void setCheckDraw(String checkDraw) {
        this.checkDraw = checkDraw;
    }

    public String getCheckDrawed() {
        return checkDrawed;
    }

    public void setCheckDrawed(String checkDrawed) {
        this.checkDrawed = checkDrawed;
    }

    public String getIssuedOrderDate() {
        return issuedOrderDate;
    }

    public void setIssuedOrderDate(String issuedOrderDate) {
        this.issuedOrderDate = issuedOrderDate;
    }

    public String getProduce() {
        return produce;
    }

    public void setProduce(String produce) {
        this.produce = produce;
    }

    public String getProduced() {
        return produced;
    }

    public void setProduced(String produced) {
        this.produced = produced;
    }

    public String getExecute() {
        return execute;
    }

    public void setExecute(String execute) {
        this.execute = execute;
    }

    public String getExecuted() {
        return executed;
    }

    public void setExecuted(String executed) {
        this.executed = executed;
    }

    public String getInterview() {
        return interview;
    }

    public void setInterview(String interview) {
        this.interview = interview;
    }

    public String getInterviewed() {
        return interviewed;
    }

    public void setInterviewed(String interviewed) {
        this.interviewed = interviewed;
    }

    public String getChargeBackTime() {
        return chargeBackTime;
    }

    public void setChargeBackTime(String chargeBackTime) {
        this.chargeBackTime = chargeBackTime;
    }

    public String getChargeBackReason() {
        return chargeBackReason;
    }

    public void setChargeBackReason(String chargeBackReason) {
        this.chargeBackReason = chargeBackReason;
    }

    public String getChargeBackComment() {
        return chargeBackComment;
    }

    public void setChargeBackComment(String chargeBackComment) {
        this.chargeBackComment = chargeBackComment;
    }

    public String getDealer() {
        return dealer;
    }

    public void setDealer(String dealer) {
        this.dealer = dealer;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getCurrentStatus() {
        return currentStatus;
    }

    public void setCurrentStatus(String currentStatus) {
        this.currentStatus = currentStatus;
    }

    public String getAddressCell() {
        return addressCell;
    }

    public void setAddressCell(String addressCell) {
        this.addressCell = addressCell;
    }

    /*public String getCustomerPhone1() {
        return customerPhone1;
    }*/

    @JsonProperty("customerPhone")
    public void setCustomerPhone1(String customerPhone1) {
        this.customerPhone1 = customerPhone1;
    }

    /*public String getCustomerPhone2() {
        return customerPhone2;
    }*/

    public void setCustomerPhone2(String customerPhone2) {
        this.customerPhone2 = customerPhone2;
    }
}

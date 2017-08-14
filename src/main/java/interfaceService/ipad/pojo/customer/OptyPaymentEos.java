package interfaceService.ipad.pojo.customer;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by Administrator on 2017/8/3.
 * EOS返回付款记录
 */
/*    {"ACCT_ID":"193a4cdc-0d7f-11e7-859b-005056bc6449",
            "ACCT_NAME":"家博会",
            "AMOUNT":"100.0000000",
            "COUNTER_FEE":null,
            "CREATION_DATE":"2017-0723 20:05:08.0",
            "EXECUTION_CONFIRMATION_NAME":null,
            "EXECUTION_CONFIRMATION_TIME":null,
            "FINANCE_CHECK_STATUS":"1",
            "GUIDE":"韩磊磊",
            "HD_COUPON_NUMBER":"",
            "NAME":"急急",
            "ORG_ID":"485bf350bb7d4b5b8fcb583f5c50c036",
            "ORG_NAME":null,
            "PAY_CODE":"SK201707232004125064",
            "PAY_ID":"4028808b5d63295d015d6f563a6900b7",
            "PAY_TIME":"2017-07-23 20:03:47.0",
            "PAY_VOU_ID":null,
            "PAY_VOU_NO":"000232",
            "PAY_WAY":"现金",
            "SYS_GEN_ORDNUM":"Y0109181613959",
            "TURN_MARKET":null,
            "TYPE":"定金",
            "operation":""}*/
public class OptyPaymentEos {

    @JsonProperty("ACCT_ID")
    private String acctId;
    @JsonProperty("ACCT_NAME")
    private String acctName;
    @JsonProperty("AMOUNT")
    private String amount;
    @JsonProperty("COUNTER_FEE")
    private String counterFee;
    @JsonProperty("CREATION_DATE")
    private String creationDate;
    @JsonProperty("EXECUTION_CONFIRMATION_NAME")
    private String executionConfirmationName;
    @JsonProperty("EXECUTION_CONFIRMATION_TIME")
    private String executionConfirmationTime;
    @JsonProperty("FINANCE_CHECK_STATUS")
    private String financeCheckStatus;
    @JsonProperty("GUIDE")
    private String guide;
    @JsonProperty("HD_COUPON_NUMBER")
    private String hdCouponNumber;
    @JsonProperty("NAME")
    private String name;
    @JsonProperty("ORG_ID")
    private String orgId;
    @JsonProperty("ORG_NAME")
    private String orgName;
    @JsonProperty("PAY_CODE")
    private String payCode;
    @JsonProperty("PAY_ID")
    private String payId;
    @JsonProperty("PAY_TIME")
    private String payTime;
    @JsonProperty("PAY_VOU_ID")
    private String payVouId;
    @JsonProperty("PAY_VOU_NO")
    private String payVouNo;
    @JsonProperty("PAY_WAY")
    private String payWay;
    @JsonProperty("SYS_GEN_ORDNUM")
    private String sysGenOrdnum;
    @JsonProperty("TURN_MARKET")
    private String trueMarket;
    @JsonProperty("TYPE")
    private String type;
    private String operation;

    public String getAcctId() {
        return acctId;
    }

    public void setAcctId(String acctId) {
        this.acctId = acctId;
    }

    public String getAcctName() {
        return acctName;
    }

    public void setAcctName(String acctName) {
        this.acctName = acctName;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getCounterFee() {
        return counterFee;
    }

    public void setCounterFee(String counterFee) {
        this.counterFee = counterFee;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    public String getExecutionConfirmationName() {
        return executionConfirmationName;
    }

    public void setExecutionConfirmationName(String executionConfirmationName) {
        this.executionConfirmationName = executionConfirmationName;
    }

    public String getExecutionConfirmationTime() {
        return executionConfirmationTime;
    }

    public void setExecutionConfirmationTime(String executionConfirmationTime) {
        this.executionConfirmationTime = executionConfirmationTime;
    }

    public String getFinanceCheckStatus() {
        return financeCheckStatus;
    }

    public void setFinanceCheckStatus(String financeCheckStatus) {
        this.financeCheckStatus = financeCheckStatus;
    }

    public String getGuide() {
        return guide;
    }

    public void setGuide(String guide) {
        this.guide = guide;
    }

    public String getHdCouponNumber() {
        return hdCouponNumber;
    }

    public void setHdCouponNumber(String hdCouponNumber) {
        this.hdCouponNumber = hdCouponNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getPayCode() {
        return payCode;
    }

    public void setPayCode(String payCode) {
        this.payCode = payCode;
    }

    public String getPayId() {
        return payId;
    }

    public void setPayId(String payId) {
        this.payId = payId;
    }

    public String getPayTime() {
        return payTime;
    }

    public void setPayTime(String payTime) {
        this.payTime = payTime;
    }

    public String getPayVouId() {
        return payVouId;
    }

    public void setPayVouId(String payVouId) {
        this.payVouId = payVouId;
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

    public String getSysGenOrdnum() {
        return sysGenOrdnum;
    }

    public void setSysGenOrdnum(String sysGenOrdnum) {
        this.sysGenOrdnum = sysGenOrdnum;
    }

    public String getTrueMarket() {
        return trueMarket;
    }

    public void setTrueMarket(String trueMarket) {
        this.trueMarket = trueMarket;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }
}

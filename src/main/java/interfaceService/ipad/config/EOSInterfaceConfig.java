package interfaceService.ipad.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Administrator on 2017/8/7.
 * EOS接口
 */
@Configuration
@ConfigurationProperties(prefix = "eos_interface_config")
public class EOSInterfaceConfig {
    //登录
    private String login;
    //查询客人信息
    private String queryPotOppKH;
    //提交测量
    private String arrangeMeasureInfos;
    //潜客录入
    private String addKH;
    //更新潜客
    private String updateKH;
    //删除潜客
    private String deleteKH;
    //付款
    private String addPayment;
    //获取订单信息
    private String queryOrdorInfo;
    //付款记录查询
    private String queryPaymentList;
    //获取业务字典
    private String getDictName;
    //获取设计师
    private String queryDesigner;
    //获取导购或设计师
    private String queryGuid;
    //获取城市
    private String queryCitys;
    //获取行政区
    private String queryAreas;
    //获取小区
    private String querycells;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getQueryPotOppKH() {
        return queryPotOppKH;
    }

    public void setQueryPotOppKH(String queryPotOppKH) {
        this.queryPotOppKH = queryPotOppKH;
    }

    public String getArrangeMeasureInfos() {
        return arrangeMeasureInfos;
    }

    public void setArrangeMeasureInfos(String arrangeMeasureInfos) {
        this.arrangeMeasureInfos = arrangeMeasureInfos;
    }

    public String getAddKH() {
        return addKH;
    }

    public void setAddKH(String addKH) {
        this.addKH = addKH;
    }

    public String getAddPayment() {
        return addPayment;
    }

    public void setAddPayment(String addPayment) {
        this.addPayment = addPayment;
    }

    public String getQueryOrdorInfo() {
        return queryOrdorInfo;
    }

    public void setQueryOrdorInfo(String queryOrdorInfo) {
        this.queryOrdorInfo = queryOrdorInfo;
    }

    public String getQueryPaymentList() {
        return queryPaymentList;
    }

    public void setQueryPaymentList(String queryPaymentList) {
        this.queryPaymentList = queryPaymentList;
    }

    public String getGetDictName() {
        return getDictName;
    }

    public void setGetDictName(String getDictName) {
        this.getDictName = getDictName;
    }

    public String getQueryDesigner() {
        return queryDesigner;
    }

    public void setQueryDesigner(String queryDesigner) {
        this.queryDesigner = queryDesigner;
    }

    public String getQueryGuid() {
        return queryGuid;
    }

    public void setQueryGuid(String queryGuid) {
        this.queryGuid = queryGuid;
    }

    public String getQueryCitys() {
        return queryCitys;
    }

    public void setQueryCitys(String queryCitys) {
        this.queryCitys = queryCitys;
    }

    public String getQueryAreas() {
        return queryAreas;
    }

    public void setQueryAreas(String queryAreas) {
        this.queryAreas = queryAreas;
    }

    public String getQuerycells() {
        return querycells;
    }

    public void setQuerycells(String querycells) {
        this.querycells = querycells;
    }

    public String getUpdateKH() {
        return updateKH;
    }

    public void setUpdateKH(String updateKH) {
        this.updateKH = updateKH;
    }

    public String getDeleteKH() {
        return deleteKH;
    }

    public void setDeleteKH(String deleteKH) {
        this.deleteKH = deleteKH;
    }
}

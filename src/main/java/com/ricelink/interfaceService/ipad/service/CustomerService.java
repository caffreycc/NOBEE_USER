package com.ricelink.interfaceService.ipad.service;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ricelink.interfaceService.ipad.cache.MyCache;
import com.ricelink.interfaceService.ipad.config.EOSInterfaceConfig;
import com.ricelink.interfaceService.ipad.pojo.Condition;
import com.ricelink.interfaceService.ipad.pojo.customer.*;
import com.ricelink.interfaceService.ipad.pojo.user.UserAuthInfo;
import com.ricelink.interfaceService.ipad.utils.DateUtil;
import com.ricelink.interfaceService.ipad.utils.HttpUtil;
import com.ricelink.interfaceService.ipad.utils.JsonUtil;
import net.sf.json.JSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/7/31.
 * 客户操作
 */
@Service
public class CustomerService {

    private Logger LOGGER = LogManager.getLogger(CustomerService.class);

    @Resource(name="simpleCache")
    private MyCache myCache;

    @Autowired
    private UserAuthService userAuthService;

    @Autowired
    private EOSInterfaceConfig eosInterfaceConfig;

    /**
     * 查询客人信息
     * @param condition
     * @param token
     * @return
     * @throws Exception
     */
    public List<RLOpportunityModel> getCustomerInfo(Condition condition, String token) throws Exception{

        UserAuthInfo userAuthInfo = myCache.get(token);

        /*Criteria criteria = new Criteria();
        criteria.set_property(Criteria.PROPERTY_ID);
        criteria.set_op(Criteria.OP_EQUALS);
        criteria.set_likeRule(Criteria.RULL_ALL);
        criteria.set_value(orgId);
        Criteria[] criterias = new Criteria[9];
        criterias[8] = criteria;
        JSONArray jsonObject1 = (JSONArray) JsonUtil.arrayToJSON(criterias);
        JSONObject  jsonObject2 = new JSONObject();
        jsonObject2.put("_expr",jsonObject1);

        PageInfo pageInfo = new PageInfo();
        pageInfo.setBegin(nowpage + "");
        pageInfo.setLength(50);
        pageInfo.setIsCount(true);
        JSONObject jsonObject3 = (JSONObject)JsonUtil.objectToJSON(pageInfo);

        JSONObject jsonParam = new JSONObject();
        jsonParam.put("funccode", "IN_IPADCUSINFO");
        jsonParam.put("criteria", jsonObject2);
        jsonParam.put("page", jsonObject3);
        jsonParam.put("userId", orgId);*/



 /*       JSONObject jsonParam = new JSONObject();
        jsonParam.put("criteria/_expr[1]/_value","");
        jsonParam.put("criteria/_expr[1]/_property","b.NAME");
        jsonParam.put("criteria/_expr[1]/_op","like");
        jsonParam.put("criteria/_expr[1]/_likeRule","end");
        jsonParam.put("criteria/_expr[2]/_value","");
        jsonParam.put("criteria/_expr[2]/_property","PHONE1");
        jsonParam.put("criteria/_expr[2]/_op", "=");
        jsonParam.put("criteria/_expr[2]/_likeRule", "all");
        jsonParam.put( "criteria/_expr[3]/_value", "");
        jsonParam.put("criteria/_expr[3]/_property", "ASSIGN_STATUS");
        jsonParam.put("criteria/_expr[3]/_op", "like");
        jsonParam.put("criteria/_expr[3]/_likeRule", "all");
        jsonParam.put( "criteria/_expr[4]/_min", "");
        jsonParam.put("criteria/_expr[4]/_max", "");
        jsonParam.put("criteria/_expr[4]/_property", "a.CREATION_DATE");
        jsonParam.put("criteria/_expr[4]/_op", "between");
        jsonParam.put("criteria/_expr[5]/_value", "");
        jsonParam.put("criteria/_expr[5]/_property", "h.NAME");
        jsonParam.put("criteria/_expr[5]/_op", "like");
        jsonParam.put("criteria/_expr[5]/_likeRule", "all");
        jsonParam.put("criteria/_expr[6]/_value", "1");
        jsonParam.put("criteria/_expr[6]/_property", "IS_VALID");
        jsonParam.put("criteria/_expr[6]/_op", "=");
        jsonParam.put( "criteria/_expr[6]/_likeRule", "all");
        jsonParam.put("criteria/_expr[7]/_op", "");
        jsonParam.put("criteria/_expr[7]/_property", "CONTACT_COUNT");
        jsonParam.put("criteria/_expr[7]/_value", "");
        jsonParam.put("criteria/_expr[8]/_value", "");
        jsonParam.put( "criteria/_expr[8]/_property", "k.NAME");
        jsonParam.put( "criteria/_expr[8]/_op", "like");
        jsonParam.put("criteria/_expr[8]/_likeRule", "all");
        jsonParam.put("criteria/_expr[9]/_value", "");

        jsonParam.put("pageIndex", nowpage);
        jsonParam.put("pageSize", pagesize);
        jsonParam.put("funccode", "IN_IPADCUSINFOS");
        JSONObject page = new JSONObject();
        page.put("begin", nowpage);
        page.put("length", pagesize);
        jsonParam.put("page", page);

        System.out.println(jsonParam);*/
        String result = null;
        CustomerInfo customerInfo;
        try {
            result = HttpUtil.getInstance().postWithJSON(eosInterfaceConfig.getQueryPotOppKH(), (JSONObject) makeCondition(condition), userAuthInfo.getSessionId());
            if(result.indexOf("session失效或者用户未登陆") != -1){
                //session失效或未登录
                LOGGER.info("session失效尝试重新登录！");
                userAuthService.login(userAuthInfo.getUsername(), userAuthInfo.getPassword(), token);
                return getCustomerInfo(condition, token);
            }

            //解析结果
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
            customerInfo = objectMapper.readValue(result, CustomerInfo.class);
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.info(e.getMessage());
            throw new Exception(e.getMessage());
        }

        //替换时间
        List<RLOpportunityModel> data = customerInfo.getData();
        if(data != null && data.size() > 0){
            for(int i = 0; i< data.size(); i++){
                if(data.get(i).getCreateCus() != null){
                    String time = DateUtil.getSeconds(data.get(i).getCreateCus());
                    data.get(i).setCreateCus(time);
                }
                if(data.get(i).getExpectMeasureDate() != null){
                    String time = DateUtil.getSeconds(data.get(i).getExpectMeasureDate());
                    data.get(i).setExpectMeasureDate(time);
                }
                if(data.get(i).getMeasure() != null){
                    String time = DateUtil.getSeconds(data.get(i).getMeasure());
                    data.get(i).setMeasure(time);
                }
                if(data.get(i).getMeasured() != null){
                    String time = DateUtil.getSeconds(data.get(i).getMeasured());
                    data.get(i).setMeasured(time);
                }
                if("0".equals(data.get(i).getCustomerBindWeChat())){
                    data.get(i).setCustomerBindWeChat("未绑定");
                } else{
                    data.get(i).setCustomerBindWeChat("已绑定");
                }

            }
        }
        return customerInfo.getData();
    }


    /**
     * 更新潜客
     * @param rlOpportunityModel
     * @param token
     * @return
     * @throws Exception
     */
    public JSON updateKH(RLOpportunityModel rlOpportunityModel, String token) throws Exception{

        UserAuthInfo userAuthInfo = myCache.get(token);
        try {
            String result = HttpUtil.getInstance().postWithJSON(eosInterfaceConfig.getUpdateKH(), opportunityToJson(rlOpportunityModel), userAuthInfo.getSessionId());
            if(result.indexOf("session失效或者用户未登陆") != -1){
                //session失效或未登录
                LOGGER.info("session失效尝试重新登录！");
                userAuthService.login(userAuthInfo.getUsername(), userAuthInfo.getPassword(), token);
                return updateKH(rlOpportunityModel, token);
            }
            return JsonUtil.objectToJSON(result);
        } catch (IOException e) {
            e.printStackTrace();
            LOGGER.info(e.getMessage());
            JSONObject result1 = new JSONObject();
            result1.put("code", "0");
            result1.put("msg", "网络错误，链接Eos异常！");
            return result1;
        }
    }


    /**
     * 保存客户信息(提交测量、测量完成、出图完成)
     * @param rlOpportunityModel
     * @param token
     */
    public Map savaCustomer(RLOpportunityModel rlOpportunityModel, String token) throws Exception{

       Map<String, Object> result = new HashMap<>();
        if("1".equals(rlOpportunityModel.getOperating())){
            //潜客录入
            AddKHReturn addKHReturn = addKH(rlOpportunityModel, token);
            if(addKHReturn.getSuccess()){
                result.put("code","SUCCESS");
            }else{
                result.put("code","FAILED");
            }
            result.put("companyID", addKHReturn.getCompanyID());
            result.put("timestamp", new Date().getTime());
        }
        if("2".equals(rlOpportunityModel.getOperating())){
            //提交测量
            MeasureReturn measureReturn = arrangeMeasureInfos(rlOpportunityModel, token);
            if("1".equals(measureReturn.getRetcode())){
                result.put("code","SUCCESS");
            }else{
                result.put("code","FAILED");
            }
            result.put("retmsg", measureReturn.getRetmsg());
            result.put("timestamp", new Date().getTime());
        }
        return result;
    }

    /**
     * 提交测量
     * @param rlOpportunityModel
     * @param token
     */
    private MeasureReturn arrangeMeasureInfos(RLOpportunityModel rlOpportunityModel, String token) throws Exception{

        //构建json对象
        JSONObject measureData = new JSONObject();
        measureData.put("opportunityId", rlOpportunityModel.getOpportunityId());
        measureData.put("measuringTime", rlOpportunityModel.getExpectMeasureDate());
        //measureData.put("activityCode", "");
        //measureData.put("activityName", "");
        //measureData.put("activityComments", "");
        measureData.put("measurStaff",rlOpportunityModel.getDesignerId());
        //measureData.put("isSsldd", "1");
        measureData.put("addressCounty", rlOpportunityModel.getAddressCounty());
        //measureData.put("addressCity", rlOpportunityModel());
        //measureData.put("addressArea", rlOpportunityModel.getAddressArea());
        //measureData.put("addressCell", )
        measureData.put("addressDetail", rlOpportunityModel.getAddressDetail());
        //measureData.put("comments", rlOpportunityModel)
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("measureData", measureData);

        //提交测量
        UserAuthInfo userAuthInfo = myCache.get(token);
        String result;
        MeasureReturn measureReturn;
        try {
            result = HttpUtil.getInstance().postWithJSON(eosInterfaceConfig.getArrangeMeasureInfos(), jsonObject, userAuthInfo.getSessionId());
            if(result.indexOf("session失效或者用户未登陆") != -1){
                //session失效或未登录
                LOGGER.info("session失效尝试重新登录！");
                userAuthService.login(userAuthInfo.getUsername(), userAuthInfo.getPassword(), token);
                return arrangeMeasureInfos(rlOpportunityModel, token);
            }
            //解析结果
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
            measureReturn = objectMapper.readValue(result, MeasureReturn.class);

        } catch (IOException e) {
            e.printStackTrace();
            LOGGER.info(e.getMessage());
            throw new Exception(e.getMessage());
        }
        return measureReturn;

    }

    /**
     * 潜客录入
     * @param rlOpportunityModel
     */
    private AddKHReturn addKH (RLOpportunityModel rlOpportunityModel, String token) throws Exception {

        UserAuthInfo userAuthInfo = myCache.get(token);
        AddKHReturn addKHReturn;
        try {
            String result = HttpUtil.getInstance().postWithJSON(eosInterfaceConfig.getAddKH(), opportunityToJson(rlOpportunityModel), userAuthInfo.getSessionId());
            if(result.indexOf("session失效或者用户未登陆") != -1){
                //session失效或未登录
                LOGGER.info("session失效尝试重新登录！");
                userAuthService.login(userAuthInfo.getUsername(), userAuthInfo.getPassword(), token);
                return addKH(rlOpportunityModel, token);
            }
            //解析结果
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
            addKHReturn = objectMapper.readValue(result, AddKHReturn.class);

        } catch (IOException e) {
            e.printStackTrace();
            LOGGER.info(e.getMessage());
            throw new Exception(e.getMessage());
        }
        return addKHReturn;
    }


    /**
     * 删除潜客
     * @param rlOpportunityModel
     * @param token
     * @return
     * @throws Exception
     */
    public JSON deleteKH(RLOpportunityModel rlOpportunityModel, String token) throws Exception{
        //构建json对象
        JSONObject customer = new JSONObject();
        customer.put("CUSTOMER_ID", rlOpportunityModel.getCustomerId());
        customer.put("OPPORTUNITY_ID", rlOpportunityModel.getOpportunityId());
        JSONArray ebDetailed = new JSONArray();
        ebDetailed.add(customer);

        JSONObject opty = new JSONObject();
        opty.put("secondFollowStatus", "其他");
        opty.put("followComment", "ipaid端删除");

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("ebDetailed", ebDetailed);
        jsonObject.put("opty", opty);

        UserAuthInfo userAuthInfo = myCache.get(token);
        String result;
        MeasureReturn measureReturn;
        try {
            result = HttpUtil.getInstance().postWithJSON(eosInterfaceConfig.getDeleteKH(), jsonObject, userAuthInfo.getSessionId());
            if(result.indexOf("session失效或者用户未登陆") != -1){
                //session失效或未登录
                LOGGER.info("session失效尝试重新登录！");
                userAuthService.login(userAuthInfo.getUsername(), userAuthInfo.getPassword(), token);
                return deleteKH(rlOpportunityModel, token);
            }
            return JsonUtil.objectToJSON(result);
        } catch (IOException e) {
            e.printStackTrace();
            LOGGER.info(e.getMessage());
            JSONObject result1 = new JSONObject();
            result1.put("code", "0");
            result1.put("msg", "网络错误，链接Eos异常！");
            return result1;
        }
    }

    /**
     * 复尺
     * @param rlOpportunityModel
     * @param token
     */
    public JSON remeasure(RLOpportunityModel rlOpportunityModel, String token) throws Exception{
        //构建json对象
        JSONObject measureData = new JSONObject();
        //measureData.put("addressCity", "北京市");
        measureData.put("addressCounty", rlOpportunityModel.getAddressCounty());
        measureData.put("addressCell", rlOpportunityModel.getAddressCell());
        measureData.put("addressDetail", rlOpportunityModel.getAddressDetail());
        measureData.put("comments", "");
        measureData.put("measuringTime",DateUtil.format2String(DateUtil.pattern, new Date(Long.valueOf(rlOpportunityModel.getExpectMeasureDate() + 000))));
        measureData.put("measurStaff", rlOpportunityModel.getDesignerId());
        measureData.put("opportunityId", rlOpportunityModel.getOpportunityId());
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("measureData", measureData);

        UserAuthInfo userAuthInfo = myCache.get(token);
        try {
            String result = HttpUtil.getInstance().postWithJSON(eosInterfaceConfig.getDeleteKH(), jsonObject, userAuthInfo.getSessionId());
            if(result.indexOf("session失效或者用户未登陆") != -1){
                //session失效或未登录
                LOGGER.info("session失效尝试重新登录！");
                userAuthService.login(userAuthInfo.getUsername(), userAuthInfo.getPassword(), token);
                return remeasure(rlOpportunityModel, token);
            }
            return JsonUtil.objectToJSON(result);
        } catch (IOException e) {
            e.printStackTrace();
            LOGGER.info(e.getMessage());
            JSONObject result1 = new JSONObject();
            result1.put("code", "0");
            result1.put("msg", "网络错误，链接Eos异常！");
            return result1;
        }
    }

    /**
     * 新增客户购买需求
     * @param requirements
     * @param token
     * @return
     */
    public JSON addRequirements(Requirements requirements, String token){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", "SUCCESS");
        jsonObject.put("timestamp", String.valueOf(new Date().getTime()).substring(0, 10));
        return jsonObject;
    }

    /**
     * 删除客户购买需求
     * @param requirements
     * @param token
     * @return
     */
    public JSON deleteRequirements(Requirements requirements, String token){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", "SUCCESS");
        jsonObject.put("timestamp", String.valueOf(new Date().getTime()).substring(0, 10));
        return jsonObject;
    }

    /**
     * 用户信息转json
     * @param rlOpportunityModel
     * @return
     */
    private JSONObject opportunityToJson(RLOpportunityModel rlOpportunityModel){
        //构建json对象
        //JSONObject coo = new JSONObject();
        //coo.put("cooperatorName", "");

        JSONObject cus = new JSONObject();
        cus.put("name", rlOpportunityModel.getCustomerName());
        cus.put("customerId", rlOpportunityModel.getCustomerId());
        cus.put("sex", rlOpportunityModel.getCustomerSex());
        cus.put("phone1", rlOpportunityModel.getCustomerPhone());

        JSONObject eb = new JSONObject();
        eb.put("addressCounty", rlOpportunityModel.getAddressCounty());
        //eb.put("addressCity", rlOpportunityModel.getadd());
        eb.put("addressArea", rlOpportunityModel.getAddressArea());
        //eb.put("addressCell", rlOpportunityModel.getadd());
        eb.put("addressDetail", rlOpportunityModel.getAddressDetail());
        eb.put("age", rlOpportunityModel.getAge());
        eb.put("orgId", rlOpportunityModel.getOrgId());
        eb.put("guideId", rlOpportunityModel.getGuideId());
        eb.put("opportunityId", rlOpportunityModel.getOpportunityId());
        eb.put("source", rlOpportunityModel.getCustomerSource());
        eb.put("secondSource", rlOpportunityModel.getSecondSource());
        eb.put("phone2", rlOpportunityModel.getConnectPhone());
        eb.put("email", rlOpportunityModel.getCustomerEmail());
        eb.put("job", rlOpportunityModel.getJob());
        eb.put("customerFeature", rlOpportunityModel.getCustomerFeature());
        eb.put("orderPriorityCode", "NOMAL_PRIORITY");
        eb.put("decorationStyleCode", rlOpportunityModel.getDecorationStyleCode());
        eb.put("houseTypeCode", rlOpportunityModel.getHouseTypeCode());
        eb.put("decorationProgressCode", rlOpportunityModel.getDecorationProgressCode());
        eb.put("decorationMethodCode", rlOpportunityModel.getDecorationMethodCode());
        eb.put("budget", rlOpportunityModel.getBudget());

        JSONObject ext = new JSONObject();
        ext.put("brand", "");
        //ext.put("customerReceiver")
        //ext.put("isIntroduce", "false");
        //ext.put("cooperator", "");

        JSONObject jsonObject = new JSONObject();
        //jsonObject.put("coo", coo);
        jsonObject.put("cus", cus);
        jsonObject.put("eb", eb);
        jsonObject.put("ext", ext);
        return jsonObject;
    }

    /**
     * 客人信息查询条件
     * @param condition
     * @return
     */
    private JSON makeCondition(Condition condition){
        JSONObject jsonParam = new JSONObject();
        JSONObject criteria = new JSONObject();
        JSONArray expr = new JSONArray();
        //订单号查询
        JSONObject jsonObject =new JSONObject();
        jsonObject.put("_op", "=");
        jsonObject.put("_property", "ORDER_CODE");
        if(condition.getOrderCode() == null){
            condition.setOrderCode("");
        }
        jsonObject.put("_value", condition.getOrderCode());
        expr.add(jsonObject);

        //客户姓名模糊查询
        JSONObject jsonObject1 =new JSONObject();
        jsonObject1.put("_op", "like");
        jsonObject1.put("_property", "b.NAME");
        jsonObject1.put("_likeRule", "end");
        if(condition.getCustomerName() == null){
            condition.setCustomerName("");
        }
        jsonObject1.put("_value", condition.getCustomerName());
        expr.add(jsonObject1);

        //门店名模糊查询
        JSONObject jsonObject2 =new JSONObject();
        jsonObject2.put("_op", "like");
        jsonObject2.put("_property", "c.NAME");
        jsonObject2.put("_likeRule", "all");
        if(condition.getOrgName() == null){
            condition.setOrgName("");
        }
        jsonObject2.put("_value", condition.getOrgName());
        expr.add(jsonObject2);

        //根据电话查询
        JSONObject jsonObject3 =new JSONObject();
        jsonObject3.put("_op", "=");
        jsonObject3.put("_property", "b.phone1");
        if(condition.getPhone() == null){
            condition.setPhone("");
        }
        jsonObject3.put("_value", condition.getPhone());
        expr.add(jsonObject3);

        //潜客录入时间
        JSONObject jsonObject4 =new JSONObject();
        jsonObject4.put("_op", "between");
        jsonObject4.put("_property", "CREATE_CUS");
        if(condition.getCreateMin() != null){
            jsonObject4.put("_min", DateUtil.seconds2Date(condition.getCreateMin()));
        }else{
            jsonObject4.put("_min", "");
        }
        if(condition.getCreateMax() != null){
            jsonObject4.put("_max", DateUtil.seconds2Date(condition.getCreateMax()));
        }else{
            jsonObject4.put("_max", "");
        }
        expr.add(jsonObject4);

        //商机状态
        JSONObject jsonObject5 =new JSONObject();
        jsonObject5.put("_op", "=");
        jsonObject5.put("_property", "CURRENT_STATUS");
        if(condition.getCurrentStatus() == null){
            condition.setCurrentStatus("");
        }
        jsonObject5.put("_value", condition.getCurrentStatus());
        expr.add(jsonObject5);

        //待定
        JSONObject jsonObject6 =new JSONObject();
        jsonObject6.put("_op", "like");
        jsonObject6.put("_property", "f.COLUMN_NAME");
        jsonObject6.put("_likeRule", "all");
        jsonObject6.put("_value", "");
        expr.add(jsonObject6);

        //待定
        JSONObject jsonObject7 =new JSONObject();
        jsonObject7.put("_op", "like");
        jsonObject7.put("_property", "h.COLUMN_NAME");
        jsonObject7.put("_likeRule", "all");
        jsonObject7.put("_value", "");
        expr.add(jsonObject7);

        //根据设计师模糊查询
        JSONObject jsonObject8 =new JSONObject();
        jsonObject8.put("_op", "like");
        jsonObject8.put("_property", "u1.EMP_NAME");
        jsonObject8.put("_likeRule", "all");
        if(condition.getDesigner() == null){
            condition.setDesigner("");
        }
        jsonObject8.put("_value", condition.getDesigner());
        expr.add(jsonObject8);

        //根据导购模糊查询
        JSONObject jsonObject9 =new JSONObject();
        jsonObject9.put("_op", "like");
        jsonObject9.put("_property", "u2.EMP_NAME");
        jsonObject9.put("_likeRule", "all");
        if(condition.getGuide() == null){
            condition.setGuide("");
        }
        jsonObject9.put("_value", condition.getGuide());
        expr.add(jsonObject9);

        //待定
        JSONObject jsonObject10 =new JSONObject();
        jsonObject10.put("_op", "=");
        jsonObject10.put("_property", "REFUND_TYPE");
        jsonObject10.put("_value", "");
        expr.add(jsonObject10);

        criteria.put("_expr", expr);
        jsonParam.put("criteria", criteria);
        jsonParam.put("funccode", "IN_IPADCUSINFOS");
        Integer begin = condition.getNowpage();
        if(begin == null){
            begin = 0;
        }
        Integer size = condition.getPagesize();
        if(size == null){
            size = 10;
        }
        JSONObject page = new JSONObject();
        page.put("begin", begin);
        page.put("length", size);
        jsonParam.put("page", page);
        jsonParam.put("pageIndex", begin);
        jsonParam.put("pageSize", size);
        System.out.println(jsonParam.toString());
        return jsonParam;
    }

}

package interfaceService.ipad.service;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import interfaceService.ipad.cache.MyCache;
import interfaceService.ipad.config.EOSInterfaceConfig;
import interfaceService.ipad.pojo.customer.*;
import interfaceService.ipad.pojo.user.UserAuthInfo;
import interfaceService.ipad.utils.DateUtil;
import interfaceService.ipad.utils.HttpUtil;
import interfaceService.ipad.utils.JsonUtil;
import net.sf.json.JSONObject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/8/3.
 * 付款操作
 */
@Service
public class OptyPaymentService {

    private Logger LOGGER = LogManager.getLogger(OptyPaymentService.class);

    @Resource(name="simpleCache")
    private MyCache myCache;

    @Autowired
    private UserAuthService userAuthService;

    @Autowired
    private EOSInterfaceConfig eosInterfaceConfig;

    /**
     * 付款记录查询
     * @param pageIndex
     * @param pageSize
     * @param token
     * @return
     * @throws Exception
     */
    public List<OptyPayment> queryOptyPayment(Integer pageIndex, Integer pageSize, String token) throws Exception {

        UserAuthInfo userAuthInfo = myCache.get(token);

        //构建json
        JSONObject page = new JSONObject();
        page.put("begin", pageIndex);
        page.put("length", pageSize);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("page", page);
        jsonObject.put("pageIndex", pageIndex);
        jsonObject.put("pageSize", pageSize);
        jsonObject.put("sortField", "");
        jsonObject.put("sortOrder", "");
        jsonObject.put("funccode", "skcx");

        OptyPaymentInfoEOS optyPaymentInfoEOS;
        try {
            String result = HttpUtil.getInstance().postWithJSON(eosInterfaceConfig.getQueryPaymentList(), jsonObject, userAuthInfo.getSessionId());
            if(result.indexOf("session失效或者用户未登陆") != -1){
                //session失效或未登录
                LOGGER.info("session失效尝试重新登录！");
                userAuthService.login(userAuthInfo.getUsername(), userAuthInfo.getPassword(), token);
                return queryOptyPayment(pageIndex, pageSize, token);
            }

            //解析结果
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
            optyPaymentInfoEOS = objectMapper.readValue(result, OptyPaymentInfoEOS.class);
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.info(e.getMessage());
            throw new Exception(e.getMessage());
        }

        //转换为需要的数据
        List<OptyPaymentEos> data = optyPaymentInfoEOS.getData();
        List<OptyPayment> result = new ArrayList<>();
        for(int i = 0; i < data.size(); i++){
            OptyPaymentEos optyPaymentEos = data.get(i);
            OptyPayment optyPayment = new OptyPayment();
            optyPayment.setPayId(optyPaymentEos.getPayId());
            //optyPayment.setOptyId(optyPaymentEos.);
            optyPayment.setType(optyPaymentEos.getType());
            optyPayment.setAmount(optyPaymentEos.getAmount());
            optyPayment.setPayWay(optyPaymentEos.getPayWay());
            optyPayment.setPayTime(Long.parseLong(DateUtil.getSeconds(optyPaymentEos.getPayTime())));
            optyPayment.setStatus("有效");
            optyPayment.setPayVouNo(optyPaymentEos.getPayVouNo());
            optyPayment.setHdCouponNumber(optyPaymentEos.getHdCouponNumber());
            //optyPayment.setCreatedBy();
            optyPayment.setCreationDate(Long.parseLong(DateUtil.getSeconds(optyPaymentEos.getCreationDate())));
            //optyPayment.setLastUpdatedBy();
            //optyPayment.setLastUpdateDate();
            //optyPayment.setVersionNumber();
            optyPayment.setRecordStatus("VALID");
            result.add(optyPayment);
        }
        return result;
    }

    /**
     * 添加付款记录
     * @param optyPayment
     */
    public PaymentResult saveOptyPayment(OptyPayment optyPayment, String token) throws Exception{

        //根据optyId从后台获取订单信息
        PayOrderInfo payOrderInfo = null;
        try {
            payOrderInfo = getPayOrderInfo(optyPayment.getOptyId(), token);
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.info(e.getMessage());
            return new PaymentResult(PaymentResult.FAILED, "获取订单信息失败！");
        }

        //付款
        return payPayment(optyPayment, payOrderInfo, token);

    }

    /**
     * 付款
     * @param optyPayment
     * @param payOrderInfo
     * @param token
     * @return
     * @throws Exception
     */
    private PaymentResult payPayment(OptyPayment optyPayment, PayOrderInfo payOrderInfo, String token) throws Exception{
        //构建json对象
        Map<String, Serializable> sgPay = new HashMap<>();
        sgPay.put("optyId", optyPayment.getOptyId());
        sgPay.put("amount", optyPayment.getAmount());
        sgPay.put("type", optyPayment.getType());
        sgPay.put("payWay", optyPayment.getPayWay());
        sgPay.put("hdCouponNumber", "");
        sgPay.put("payVouNo", optyPayment.getPayVouNo());
        sgPay.put("payTime", DateUtil.formatDate());
        sgPay.put("comments", "");
        JSONObject jsonSgpay = (JSONObject) JsonUtil.objectToJSON(sgPay);

        Map<String, Serializable> payExt = new HashMap<>();
        payExt.put("whetherShare", "N");
        payExt.put("marketAmount", optyPayment.getAmount());
        payExt.put("orgId", "");
        payExt.put("shopId", "");
        payExt.put("acctName", "");
        payExt.put("acctType", "");
        payExt.put("marketId", "");
        payExt.put("counterFee", "");
        payExt.put("acctId", "");
        payExt.put("turnMarket", "");
        payExt.put("scontractAmount", "");
        payExt.put("payCode", optyPayment.getPayId());
        payExt.put("smallTicketAccessories", 11);
        JSONObject jsonPayExt = (JSONObject)JsonUtil.objectToJSON(payExt);

        Map<String, Serializable> rec = new HashMap<>();
        PayOrderSubInfo recBean = payOrderInfo.getRec();
        rec.put("sysGenOrdnum", recBean.getSysGenOrdnum());
        rec.put("name", recBean.getName());
        rec.put("phone1", recBean.getPhone1());
        rec.put("orgName", recBean.getOrgName());
        rec.put("columnName", recBean.getColumnName());
        JSONObject jsonRec = (JSONObject)JsonUtil.objectToJSON(rec);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("sgPay", jsonSgpay);
        jsonObject.put("payExt", jsonPayExt);
        jsonObject.put("rec", jsonRec);

        //付款
        UserAuthInfo userAuthInfo = myCache.get(token);
        String result;
        PaymentResult paymentResult;
        try {
            result = HttpUtil.getInstance().postWithJSON(eosInterfaceConfig.getAddPayment(), jsonObject, userAuthInfo.getSessionId());
            if(result.indexOf("session失效或者用户未登陆") != -1){
                //session失效或未登录
                LOGGER.info("session失效尝试重新登录！");
                userAuthService.login(userAuthInfo.getUsername(), userAuthInfo.getPassword(), token);
                return payPayment(optyPayment, payOrderInfo, token);
            }
            //解析结果
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
            paymentResult = objectMapper.readValue(result, PaymentResult.class);

        } catch (IOException e) {
            e.printStackTrace();
            LOGGER.info(e.getMessage());
            throw new Exception(e.getMessage());
        }
        return paymentResult;
    }


    /**
     * 提交付款前获取订单信息
     * @param optyId
     * @param token
     * @return
     * @throws Exception
     */
    private PayOrderInfo getPayOrderInfo(String optyId, String token) throws Exception{
        UserAuthInfo userAuthInfo = myCache.get(token);
        Map<String, String> map = new HashMap<>();
        map.put("id", optyId);
        String result;
        PayOrderInfo payOrderInfo;
        try {
            result = HttpUtil.getInstance().postWithSession(eosInterfaceConfig.getQueryOrdorInfo(), map, userAuthInfo.getSessionId());
            if(result.indexOf("session失效或者用户未登陆") != -1){
                //session失效或未登录
                LOGGER.info("session失效尝试重新登录！");
                userAuthService.login(userAuthInfo.getUsername(), userAuthInfo.getPassword(), token);
                return getPayOrderInfo(optyId, token);
            }
            //解析结果
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
            payOrderInfo = objectMapper.readValue(result, PayOrderInfo.class);

        } catch (IOException e) {
            e.printStackTrace();
            LOGGER.info(e.getMessage());
            throw new Exception(e.getMessage());
        }
        return payOrderInfo;
    }
}

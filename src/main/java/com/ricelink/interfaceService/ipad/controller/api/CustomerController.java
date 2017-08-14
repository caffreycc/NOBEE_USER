package com.ricelink.interfaceService.ipad.controller.api;



import com.ricelink.interfaceService.ipad.pojo.Condition;
import com.ricelink.interfaceService.ipad.pojo.customer.RLOpportunityModel;
import com.ricelink.interfaceService.ipad.pojo.customer.Requirements;
import com.ricelink.interfaceService.ipad.service.CustomerService;
import com.ricelink.interfaceService.ipad.utils.DateUtil;
import net.sf.json.JSON;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/8/1.
 */
@Controller
@RequestMapping("/sec/opportunities")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    /**
     * 获取客人列表
     * @param nowpage
     * @param pagesize
     * @param request
     * @return
     */
    @RequestMapping("/selectOnline")
    @ResponseBody
    public List<RLOpportunityModel> getCustomerInfo(@RequestBody Condition condition, HttpServletRequest request){
        String token = request.getHeader("Authorization");
        try {
            return customerService.getCustomerInfo(condition, token);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 保存客户信息(提交测量、测量完成、出图完成)
     * @param rlOpportunityModel
     */
    @RequestMapping(value = "/opportunitiesinfo" , method= RequestMethod.POST)
    @ResponseBody
    public Map savaCustomer(@RequestBody RLOpportunityModel rlOpportunityModel, HttpServletRequest request){

        try {
            return customerService.savaCustomer(rlOpportunityModel, request.getHeader("Authorization"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
        /***********************************/
/*        Map<String, String> map1 = new HashMap<>();
        map1.put("name", rlOpportunityModel.getCustomerName());
        //map1.put("customerId", rlOpportunityModel)

        Map<String, Serializable> map =new HashMap<>();
        map.put("code", "SUCCESS");
        map.put("moreInfo", "保存成功！");
        map.put("timestamp", new Date().getTime());
        return map;*/
    }

    /**
     * 更新潜客
     * @param rlOpportunityModel
     * @param request
     * @return
     */
    @RequestMapping("/updateOpportunity")
    @ResponseBody
    public JSON updateCustomer(@RequestBody RLOpportunityModel rlOpportunityModel, HttpServletRequest request){
        String token = request.getHeader("Authorization");
        JSONObject jsonObject = new JSONObject();
        try {
            return customerService.updateKH(rlOpportunityModel, token);
        } catch (Exception e) {
            e.printStackTrace();
            jsonObject.put("code", "0");
            jsonObject.put("msg", e.getMessage());
        }
        return jsonObject;
    }

    /**
     * 删除潜客
     * @param rlOpportunityModel
     * @param request
     * @return
     */
    @RequestMapping("/deleteOpportunity")
    @ResponseBody
    public JSON deleteCustomer(@RequestBody RLOpportunityModel rlOpportunityModel, HttpServletRequest request){
        String token = request.getHeader("Authorization");
        JSONObject jsonObject = new JSONObject();
        try {
            return customerService.deleteKH(rlOpportunityModel, token);
        } catch (Exception e) {
            e.printStackTrace();
            jsonObject.put("code", "0");
            jsonObject.put("msg", e.getMessage());
        }
        return jsonObject;
    }

    /**
     * 新增客户购买需求
     * @param requirements
     * @param request
     * @return
     */
    @RequestMapping("/addRequirements")
    @ResponseBody
    public JSON addRequirements(@RequestBody Requirements requirements, HttpServletRequest request){
        String token = request.getHeader("Authorization");
        JSONObject jsonObject = new JSONObject();
        try {
            return customerService.addRequirements(requirements, token);
        } catch (Exception e) {
            e.printStackTrace();
            jsonObject.put("code", "0");
            jsonObject.put("msg", e.getMessage());
            jsonObject.put("timestamp", DateUtil.getSeconds());
        }
        return jsonObject;
    }

    /**
     * 删除客户购买需求
     * @param requirements
     * @param request
     * @return
     */
    @RequestMapping("/deleteRequirements")
    @ResponseBody
    public JSON deleteRequirements(@RequestBody Requirements requirements, HttpServletRequest request){
        String token = request.getHeader("Authorization");
        JSONObject jsonObject = new JSONObject();
        try {
            return customerService.deleteRequirements(requirements, token);
        } catch (Exception e) {
            e.printStackTrace();
            jsonObject.put("code", "0");
            jsonObject.put("msg", e.getMessage());
            jsonObject.put("timestamp", DateUtil.getSeconds());
        }
        return jsonObject;
    }

}

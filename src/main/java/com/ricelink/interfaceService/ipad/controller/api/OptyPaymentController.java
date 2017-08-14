package com.ricelink.interfaceService.ipad.controller.api;


import com.ricelink.interfaceService.ipad.pojo.customer.OptyPayment;
import com.ricelink.interfaceService.ipad.pojo.customer.PaymentResult;
import com.ricelink.interfaceService.ipad.service.OptyPaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by Administrator on 2017/8/3.
 */
@Controller
@RequestMapping("/sec/sgpayment")
public class OptyPaymentController {

    @Autowired
    private OptyPaymentService optyPaymentService;

    /**
     * 查询付款记录
     * @param pageIndex
     * @param pageSize
     * @param request
     * @return
     */
    @RequestMapping("/queryQptyPayment")
    @ResponseBody
    public List queryQptyPayment(Integer pageIndex, Integer pageSize, HttpServletRequest request){

        if(pageIndex == null){
            pageIndex =0;
        }
        if(pageSize == null || pageSize == 0){
            pageSize =10;
        }
        try {
            return optyPaymentService.queryOptyPayment(pageIndex, pageSize, request.getHeader("Authorization"));
        } catch (Exception e) {
            e.printStackTrace();
            //return e.getMessage();
        }
        return null;
    }

    /**
     * 保存付款记录
     * @param optyPayment
     * @param request
     * @return
     */
    @RequestMapping("/saveoptypayment")
    @ResponseBody
    public PaymentResult saveOptypayment(@RequestBody OptyPayment optyPayment, HttpServletRequest request){

        try {
            return optyPaymentService.saveOptyPayment(optyPayment,request.getHeader("Authorization"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}

package com.ricelink.interfaceService.ipad.controller.api;


import com.ricelink.interfaceService.ipad.pojo.ResultMessage;
import com.ricelink.interfaceService.ipad.service.UserAuthService;
import com.ricelink.interfaceService.ipad.utils.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Administrator on 2017/7/31.
 */
@Controller
@RequestMapping("/auth")
public class UserAuthController {

    @Autowired
    private UserAuthService userAuthService;

    @RequestMapping(value = "/login")
    @ResponseBody
    public ResultMessage login(String username, String password){
        //参数校验
        if(username == null || "".equals(username) || password == null || "".equals(password)){
            return new ResultMessage(ResultMessage.FAILED, DateUtil.getTime(), "用户名和密码不能为空！","");
        }

        //用户验证
        String token;
        try {
            token = userAuthService.login(username, password, null);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultMessage(ResultMessage.FAILED, DateUtil.getTime(), e.getMessage(),null);
        }
        return new ResultMessage(ResultMessage.SUCCESS, DateUtil.getTime(), token);
        //return new ResultMessage(ResultMessage.FAILED, DateUtil.getTime(), "登录失败，请重试！");
    }

    @RequestMapping(value = "/login2")
    @ResponseBody
    public ResultMessage login2(String username, String password){
        //参数校验
        if(username == null || "".equals(username) || password == null || "".equals(password)){
            return new ResultMessage(ResultMessage.FAILED, DateUtil.getTime(), "用户名和密码不能为空！","");
        }

        //用户验证
        try {
            return userAuthService.login2(username, password, null);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultMessage(ResultMessage.FAILED, DateUtil.getTime(), e.getMessage(),null);
        }
    }

    @RequestMapping("/logout")
    @ResponseBody
    public void login(HttpServletRequest request){
        String token = request.getHeader("Authorization");
        if(token != null && !"".equals(token)){
            userAuthService.logout(token);
        }
    }
}

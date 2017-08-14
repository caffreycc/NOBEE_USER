package com.ricelink.interfaceService.ipad.service;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.ricelink.utils.http.impl.HttpDefaultUtils;
import com.ricelink.interfaceService.ipad.cache.MyCache;
import com.ricelink.interfaceService.ipad.config.EOSInterfaceConfig;
import com.ricelink.interfaceService.ipad.pojo.ResultMessage;
import com.ricelink.interfaceService.ipad.pojo.user.LoginReturn;
import com.ricelink.interfaceService.ipad.pojo.user.UserAuthInfo;
import com.ricelink.interfaceService.ipad.pojo.user.UserInfo;
import com.ricelink.interfaceService.ipad.utils.DateUtil;
import com.ricelink.interfaceService.ipad.utils.HttpUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.UUID;

/**
 * Created by Administrator on 2017/7/31.
 */
@Service
public class UserAuthService {

    private Logger LOGGER = LogManager.getLogger(UserAuthService.class);

    @Resource(name="simpleCache")
    private MyCache myCache;

    @Autowired
    private EOSInterfaceConfig eosInterfaceConfig;
    /**
     * 用户登录验证
     * @param username
     * @param password
     * @throws Exception
     */
    public String login(String username, String password, String token) throws Exception{
        //调用EOS验证并缓存token
        String urlFormat = "userId={0}&password={1}";
        String[] params = {username, password};
        String param =  MessageFormat.format(urlFormat, params);
        UserAuthInfo userAuthInfo = new UserAuthInfo();
        String sessionId;
        try {
            sessionId = HttpUtil.getInstance().getSessionId(eosInterfaceConfig.getLogin(), param);
            if(sessionId ==null || "error".equals(sessionId)){
                throw new Exception("用户名或密码错误");
            }

            //缓存用户信息
            if(token == null) {
                token = UUID.randomUUID().toString().replace("-", "");
            }
            userAuthInfo.setUsername(username);
            userAuthInfo.setPassword(password);
            userAuthInfo.setToken(token);
            userAuthInfo.setSessionId(sessionId);
            myCache.set(userAuthInfo);

        } catch (IOException e) {
            LOGGER.info(e.getMessage());
            e.printStackTrace();
        }
        return userAuthInfo.getToken();
    }


    /**
     * 用户登录验证
     * @param username
     * @param password
     * @throws Exception
     */
    public ResultMessage login2(String username, String password, String token) throws Exception{
        //调用EOS验证并缓存token
        String urlFormat = "userId={0}&password={1}";
        String[] params = {username, password};
        String param =  MessageFormat.format(urlFormat, params);
        UserAuthInfo userAuthInfo = new UserAuthInfo();
        UserInfo userInfo = new UserInfo();
        try {
            String result = HttpDefaultUtils.getInstance().get(eosInterfaceConfig.getLogin(), param);
            //解析返回
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
            LoginReturn loginReturn = objectMapper.readValue(result, LoginReturn.class);
            if(loginReturn.getRetCode() != 1){
                throw new Exception("用户名或密码错误！");
            }

            userInfo.setUserId(loginReturn.getUo().getUserId());
            userInfo.setUsername(loginReturn.getUo().getUserName());
            userInfo.setNick(loginReturn.getUo().getUserRealName());
            userInfo.setSex("男");
            userInfo.setPhone(loginReturn.getUo().getUserName());
            userInfo.setEmail(loginReturn.getUo().getUserMail());
            userInfo.setPosition("导购");
            userInfo.setRoleCode(loginReturn.getUo().getAttributes().getRoleList());
            userInfo.setOrgId(loginReturn.getUo().getUserOrgId());
            userInfo.setOrgName(loginReturn.getUo().getUserOrgName());
            userInfo.setPhotoId("12345678");
            userInfo.setPersonalSignature("导购个性签名");
            userInfo.setProviceName("");
            userInfo.setProvinceId("");
            userInfo.setCityId("C001");
            userInfo.setCityName("北京市");
            userInfo.setCountryId("CT0001");
            userInfo.setCountryName("东城区");

            //缓存用户信息
            if(token == null) {
                token = UUID.randomUUID().toString().replace("-", "");
            }
            userAuthInfo.setUsername(username);
            userAuthInfo.setPassword(password);
            userAuthInfo.setToken(token);
            userAuthInfo.setSessionId(loginReturn.getUo().getSessionId());
            myCache.set(userAuthInfo);

            ResultMessage resultMessage =  new ResultMessage(ResultMessage.SUCCESS, DateUtil.getTime(), token);
            resultMessage.setUserInfo(userInfo);
            return resultMessage;

        } catch (IOException e) {
            LOGGER.info(e.getMessage());
            e.printStackTrace();
            return new ResultMessage(ResultMessage.FAILED, DateUtil.getTime(), e.getMessage(),null);
        }
    }

    public void logout(String token) {
        myCache.remove(token);
    }
}

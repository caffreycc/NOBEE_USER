package com.ricelink.interfaceService.ipad.cache;


import com.ricelink.interfaceService.ipad.pojo.user.UserAuthInfo;
import com.ricelink.interfaceService.ipad.service.CustomerService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/7/31.
 * 模拟简单缓存
 */
@Component("simpleCache")
public class SimpleCache implements MyCache{

    private Logger LOGGER = LogManager.getLogger(CustomerService.class);

    private static Map<String, UserAuthInfo> map = new HashMap<>();

    @Override
    public UserAuthInfo get(String token) throws Exception{
        UserAuthInfo userAuthInfo = map.get(token);
        if(userAuthInfo == null){
            LOGGER.info("缓存中未找到用户信息！");
            throw new Exception("缓存中未找到用户信息！");
        }
        return userAuthInfo;
    }

    @Override
    public void set(UserAuthInfo userAuthInfo){
         map.put(userAuthInfo.getToken(), userAuthInfo);
    }

    @Override
    public void remove(String token) {
        try {
            UserAuthInfo userAuthInfo = get(token);
            map.remove(token);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void clear() {
    }
}

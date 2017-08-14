package com.ricelink.interfaceService.ipad.controller.api;


import com.ricelink.interfaceService.ipad.pojo.dict.DesOrGui;
import com.ricelink.interfaceService.ipad.pojo.dict.DictPojo;
import com.ricelink.interfaceService.ipad.service.SecCommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/8/1.
 */
@Controller
@RequestMapping("/sec/com")
public class SecCommonController {

    @Autowired
    private SecCommonService secCommonService;

    /**
     * 获取业务字典
     * @param request
     * @param map
     * @return
     */
    @RequestMapping("/fndlookup")
    @ResponseBody
    public DictPojo getDictInfo(HttpServletRequest request, @RequestBody Map<String, List<String>> map){
        String token = request.getHeader("Authorization");
        /*if(token == null || "".equals(token)){
            return null;
        }*/
        List<String> typeCode = map.get("typeCode");
        if(typeCode == null || typeCode.size() == 0){
            return null;
        }
        StringBuilder stringBuilder = new StringBuilder();
        for(int i = 0; i < typeCode.size(); i++){
            stringBuilder.append(typeCode.get(i));
            if(i < (typeCode.size() - 1)){
                stringBuilder.append("','");
            }
        }
        try {
            return secCommonService.getDictInfo(token, stringBuilder.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取导购/设计师
     * @param orgId
     * @param request
     * @return
     */
    @RequestMapping("/getdesorgui")
    @ResponseBody
    public List getDesorgui(String orgId, HttpServletRequest request){
        String token = request.getHeader("Authorization");
        try {
            List<DesOrGui> list = secCommonService.geiGuidByOrgId(orgId, token);
            list.addAll(secCommonService.getDesorgui(orgId, token));
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取小区
     * @param area
     * @param request
     * @return
     */
    @RequestMapping("/getCells")
    @ResponseBody
    public List getCells(String area, HttpServletRequest request){
        try {
            return secCommonService.getCells(area, request.getHeader("Authorization"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取所有城市和行政区县
     * @param request
     * @return
     */
    @RequestMapping("/getCitysAndArea")
    @ResponseBody
    public List getCitysOrArea(String type, String parentId, HttpServletRequest request){
        try {
            return secCommonService.getCitysOrAreas(type, parentId, request.getHeader("Authorization"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}

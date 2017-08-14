package com.ricelink.interfaceService.ipad.service;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.ricelink.interfaceService.ipad.cache.MyCache;
import com.ricelink.interfaceService.ipad.config.EOSInterfaceConfig;
import com.ricelink.interfaceService.ipad.pojo.address.*;
import com.ricelink.interfaceService.ipad.pojo.dict.*;
import com.ricelink.interfaceService.ipad.pojo.user.UserAuthInfo;
import com.ricelink.interfaceService.ipad.utils.DateUtil;
import com.ricelink.interfaceService.ipad.utils.HttpUtil;
import net.sf.json.JSONObject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/7/31.
 */
@Service
public class SecCommonService {

    private Logger LOGGER = LogManager.getLogger(SecCommonService.class);

    @Resource(name="simpleCache")
    private MyCache myCache;

    @Autowired
    private UserAuthService userAuthService;

    @Autowired
    private EOSInterfaceConfig eosInterfaceConfig;

    /**
     * 获取业务字典
     * @param token
     * @param typeCode
     * @return
     * @throws Exception
     */
    public DictPojo getDictInfo(String  token, String typeCode) throws Exception{
       /* if(typeCode == null || typeCode.length == 0){
           // return null;
        }*/
        UserAuthInfo userAuthInfo = myCache.get(token);

        JSONObject jsonParam = new JSONObject();
        jsonParam.put("DICTTYPEID", typeCode);
        JSONObject jsonParam1 = new JSONObject();
        jsonParam1.put("para", jsonParam);
        System.out.println(jsonParam1);

        String result = null;
        DictPojo dictPojo;
        try {
            result = HttpUtil.getInstance().postWithJSON(eosInterfaceConfig.getGetDictName(), jsonParam1, userAuthInfo.getSessionId());
            if(result.indexOf("session失效或者用户未登陆") != -1){
                //session失效或未登录
                LOGGER.info("session失效尝试重新登录！");
                userAuthService.login(userAuthInfo.getUsername(), userAuthInfo.getPassword(), token);
                return getDictInfo(token, typeCode);
            }

            //解析结果
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
            dictPojo = objectMapper.readValue(result, DictPojo.class);
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.info(e.getMessage());
            throw new Exception(e.getMessage());
        }

        return dictPojo;
    }

    /**
     * 获取设计师
     * @param token
     */
    public List<DesOrGui> getDesorgui(String orgId, String token) throws Exception{

        UserAuthInfo userAuthInfo = myCache.get(token);

        Map<String, String> map = new HashMap<>();
        if(orgId != null && !"".equals(orgId)){
            map.put("storeId", orgId);
        }
        DesignersReturn designersReturn;
        try {
            String result = HttpUtil.getInstance().postWithSession(eosInterfaceConfig.getQueryDesigner(), map, userAuthInfo.getSessionId());
            if(result.indexOf("session失效或者用户未登陆") != -1){
                //session失效或未登录
                LOGGER.info("session失效尝试重新登录！");
                userAuthService.login(userAuthInfo.getUsername(), userAuthInfo.getPassword(), token);
                return getDesorgui(orgId, token);
            }

            //解析结果
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
            designersReturn = objectMapper.readValue(result, DesignersReturn.class);
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.info(e.getMessage());
            throw new Exception(e.getMessage());
        }

        List<Designer> designers = designersReturn.getDesigners();
        List<DesOrGui> desOrGuis = new ArrayList<>();
        if(designers != null && designers.size() > 0){
            for(int i = 0; i < designers.size(); i++){
                DesOrGui desOrGui = new DesOrGui();
                desOrGui.setName(designers.get(i).getDesignerName());
                desOrGui.setUserId(designers.get(i).getDesignerId());
                desOrGui.setRoleCode("DESIGNER");
                desOrGui.setOrgId(designers.get(i).getOrgId());
                desOrGui.setPhone(designers.get(i).getPhone());
                desOrGui.setReporttoUserId(designers.get(i).getColumnName());
                desOrGuis.add(desOrGui);
            }
        }
        return desOrGuis;
    }

    /**
     * 获取导购
     * @param orgId
     * @param token
     */
    public List<DesOrGui> geiGuidByOrgId(String orgId, String token) throws Exception{

        UserAuthInfo userAuthInfo = myCache.get(token);

        JSONObject para = new JSONObject();
        para.put("orgId", orgId);
        JSONObject jsonParam = new JSONObject();
        jsonParam.put("para", para);

        DesOrGuiPojo desOrGuiPojo;
        try {
            String result = HttpUtil.getInstance().postWithJSON(eosInterfaceConfig.getQueryGuid(), jsonParam, userAuthInfo.getSessionId());
            if(result.indexOf("session失效或者用户未登陆") != -1){
                //session失效或未登录
                LOGGER.info("session失效尝试重新登录！");
                userAuthService.login(userAuthInfo.getUsername(), userAuthInfo.getPassword(), token);
                return geiGuidByOrgId(orgId, token);
            }

            //解析结果
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
            desOrGuiPojo = objectMapper.readValue(result, DesOrGuiPojo.class);
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.info(e.getMessage());
            throw new Exception(e.getMessage());
        }

        //参数替换
        List<DesOrGui> datas = desOrGuiPojo.getDatas();
        if(datas != null && datas.size() > 0){
            for (int i = 0; i < datas.size(); i++){
                //角色替换
                datas.get(i).setRoleCode("GUIDE");
                //时间转为long数值型
                String time = DateUtil.getSeconds(datas.get(i).getCreationDate());
                datas.get(i).setCreationDate(time);
            }
        }
        return datas;
    }

    /**
     * 获取小区
     * @param area
     * @param token
     */
    public List getCells(String area, String token) throws Exception{

            UserAuthInfo userAuthInfo = myCache.get(token);

            Map<String, String> map = new HashMap<>();
            map.put("area", area);
            CellPojo cellPojo;
            try {
                String result = HttpUtil.getInstance().postWithSession(eosInterfaceConfig.getQuerycells(), map, userAuthInfo.getSessionId());
                if(result.indexOf("session失效或者用户未登陆") != -1){
                    //session失效或未登录
                    LOGGER.info("session失效尝试重新登录！");
                    userAuthService.login(userAuthInfo.getUsername(), userAuthInfo.getPassword(), token);
                    return getCells(area, token);
                }

                //解析结果
                ObjectMapper objectMapper = new ObjectMapper();
                objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
                cellPojo = objectMapper.readValue(result, CellPojo.class);
            } catch (Exception e) {
                e.printStackTrace();
                LOGGER.info(e.getMessage());
                throw new Exception(e.getMessage());
            }

            //转换为ProvinceCityDistrict
            List<Cell> cells = cellPojo.getCells();
            List<ProvinceCityDistrict> list = new ArrayList<>();
            if(cells != null && cells.size() > 0){
                for(int i = 0; i < cells.size(); i++){
                    Cell cell = cells.get(i);
                    ProvinceCityDistrict provinceCityDistrict = new ProvinceCityDistrict();
                    provinceCityDistrict.setName(cell.getName());
                    provinceCityDistrict.setType("CELL");
                    provinceCityDistrict.setParentId(area);
                    provinceCityDistrict.setVal(cell.getId());
                    list.add(provinceCityDistrict);
                }
            }
            return list;
    }

    /**
     * 获取所有城市和行政区
     * @param token
     * @return
     * @throws Exception
     */
    public List getCitysOrAreas(String type, String parentId, String token) throws Exception{
        if("BI_PROVINCE".equals(type)){
            return getAllCity(token);
        }else if("BI_CITY".equals(type)){

        }else if("BI_COUNTY".equals(type)){

        }
        return null;
       /* List<ProvinceCityDistrict> list = new ArrayList<>();
        list.addAll(getAllCity(token));
        list.addAll(getAllArea(token));
        return list;*/
    }

    /**
     * 获取所有城市
     * @param token
     */
    private List getAllCity(String token) throws Exception{
        UserAuthInfo userAuthInfo = myCache.get(token);

        Map<String, String> map =new HashMap<>();
        CityPojo cityPojo;
        try {
            String result = HttpUtil.getInstance().postWithSession(eosInterfaceConfig.getQueryCitys(), map, userAuthInfo.getSessionId());
            if(result.indexOf("session失效或者用户未登陆") != -1){
                //session失效或未登录
                LOGGER.info("session失效尝试重新登录！");
                userAuthService.login(userAuthInfo.getUsername(), userAuthInfo.getPassword(), token);
                return getAllCity(token);
            }

            //解析结果
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
            cityPojo = objectMapper.readValue(result, CityPojo.class);
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.info(e.getMessage());
            throw new Exception(e.getMessage());
        }

        //转换为ProvinceCityDistrict
        List<City> citys = cityPojo.getCitys();
        List<ProvinceCityDistrict> list = new ArrayList<>();
        if(citys != null && citys.size() > 0){
            for(int i = 0; i < citys.size(); i++){
                City city = citys.get(i);
                ProvinceCityDistrict provinceCityDistrict = new ProvinceCityDistrict();
                provinceCityDistrict.setName(city.getSaleName());
                provinceCityDistrict.setType("CITY");
                provinceCityDistrict.setParentId("");
                provinceCityDistrict.setVal(city.getSaleId());
                list.add(provinceCityDistrict);
            }
        }
        return list;
    }

    /**
     * 获取所有行政区
     * @param token
     */
    private List getAllArea(String token) throws Exception{
        UserAuthInfo userAuthInfo = myCache.get(token);

        Map<String, String> map =new HashMap<>();
        map.put("type", "1");
        CountyPojo countyPojo;
        try {
            String result = HttpUtil.getInstance().postWithSession(eosInterfaceConfig.getQueryAreas(), map, userAuthInfo.getSessionId());
            if(result.indexOf("session失效或者用户未登陆") != -1){
                //session失效或未登录
                LOGGER.info("session失效尝试重新登录！");
                userAuthService.login(userAuthInfo.getUsername(), userAuthInfo.getPassword(), token);
                return getAllArea(token);
            }

            //解析结果
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
            countyPojo = objectMapper.readValue(result, CountyPojo.class);
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.info(e.getMessage());
            throw new Exception(e.getMessage());
        }

        //转换为ProvinceCityDistrict
        List<County> counties = countyPojo.getCountys();
        List<ProvinceCityDistrict> list = new ArrayList<>();
        if(counties != null && counties.size() > 0){
            for(int i = 0; i < counties.size(); i++){
                County county = counties.get(i);
                ProvinceCityDistrict provinceCityDistrict = new ProvinceCityDistrict();
                provinceCityDistrict.setName(county.getCountyName());
                provinceCityDistrict.setType("COUNTY");
                provinceCityDistrict.setParentId(county.getCityId());
                provinceCityDistrict.setVal(county.getCountyId());
                list.add(provinceCityDistrict);
            }
        }
        return list;
    }
}

package com.ricelink.interfaceService.ipad.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.sf.json.JSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.io.IOException;

/**
 * Created by Administrator on 2017/7/31.
 */
public class JsonUtil {
    /**
     * 数组转json
     * @param objects
     * @return
     */
    public static JSON arrayToJSON(Object[] objects) {
        return JSONArray.fromObject(objects);
    }

    /**
     * 对象转Json
     * @param object
     * @return
     */
    public static JSON objectToJSON(Object object){
        if(object instanceof String) {
            String str = ((String) object).replaceAll(":null",":\"\"");
            return JSONObject.fromObject(str);
        }
        return  JSONObject.fromObject(object);
    }

    /**
     * json转对象
     * @param json
     * @param clazz
     * @return
     * @throws IOException
     */
    public static Object jsonToObject(JSON json, Class clazz) throws IOException{
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        return objectMapper.readValue(json.toString(), clazz);
    }
}
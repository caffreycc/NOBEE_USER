package com.ricelink.interfaceService.ipad.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Administrator on 2017/7/31.
 */
public class DateUtil {

    public static final String pattern = "yyyy-MM-dd HH:mm:ss";

    public static String formatDate(){
        return format2String(pattern, new Date());
    }
    /**
     * 格式化时间为字符串
     * @param pattern
     * @param date
     * @return
     */
    public static String format2String(String pattern, Date date){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        return simpleDateFormat.format(date);
    }


    public static Long getTime(){
        return new Date().getTime();
    }

    /**
     *字符串转指定格式的日期
     * @param strDateTime
     * @param pattern 格式
     * @return
     */
    public static Date toDate(String strDateTime, String pattern){
        try{
            SimpleDateFormat tw = new SimpleDateFormat(pattern, Locale.CHINESE);
            return tw.parse(strDateTime);
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }

    }

    /**
     * 获取秒数
     * @param date
     * @return
     */
    public static String getSeconds(String date){
        String timestamp = DateUtil.toDate(date, DateUtil.pattern).getTime() + "";
        return timestamp.substring(0, 10);
    }

    /**
     * 获取当前秒数
     * @return
     */
    public static String getSeconds(){
        String timestamp = new Date().getTime() + "";
        return timestamp.substring(0, 10);
    }

    /**
     * 获取yyyy-MM-dd HH:mm:ss格式字符串
     * @param seconds
     * @return
     */
    public static String seconds2Date(String seconds){
        Long timestamp = Long.valueOf(seconds + "000");
        Date date = new Date(timestamp);
        return format2String(pattern, date);
    }
}

package com.yayanovel.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 时间工具类
 */
public class DateTimeUtil {
    public static String getTime(){
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHH");
        return simpleDateFormat.format(date);
    }
}

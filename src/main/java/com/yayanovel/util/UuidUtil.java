package com.yayanovel.util;

import java.util.UUID;

/**
 * 创建uuid的工具类
 */
public class UuidUtil {
    public static String getUUID(){
        return UUID.randomUUID().toString().replace("-","");
    }
}

package com.yayanovel.util;

import io.swagger.models.auth.In;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;

import java.io.*;
import java.net.URLDecoder;
import java.util.Locale;


/**
 * 文本读取工具类
 */
public class TextReadUtil {
    private static  Logger logger = LoggerFactory.getLogger(TextReadUtil.class);
    public static String getText(String path) {
        String sb = null;
        try {
            path  = URLDecoder.decode(path, "utf-8");
            ClassPathResource classPathResource = new ClassPathResource(path);
            InputStream inputStream = classPathResource.getInputStream();
            byte[] bytes = new byte[inputStream.available()];
            inputStream.read(bytes);
            sb = new String(bytes);
        } catch (Exception e) {
            System.out.println("读取文件内容操作出错");
            e.printStackTrace();
        }
        logger.info("读取到的内容：" + sb);
        return sb;
    }
}

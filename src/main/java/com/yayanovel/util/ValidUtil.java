package com.yayanovel.util;

import java.util.regex.Pattern;

/**
 * 格式校验工具类
 */
public class ValidUtil {
    /**
     * 邮箱校验
     * @param email
     * @return
     */
    public static boolean isValidEmail(String email) {
        if ((email != null) && (!email.isEmpty())) {
            return Pattern.matches("^(\\w+([-.][A-Za-z0-9]+)*){3,18}@\\w+([-.][A-Za-z0-9]+)*\\.\\w+([-.][A-Za-z0-9]+)*$", email);
        }
        return false;
    }

    /**
     * 校验密码，大于或者等于8位，必须含有数字和字母
     * @param password
     * @return
     */
    public static boolean isValidPassword(String password) {
        if ((password != null) && (!password.isEmpty())) {
            return Pattern.matches("^(?![^a-zA-Z]+$)(?!\\D+$).{8,}$", password);
        }
        return false;
    }
}

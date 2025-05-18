package com.cow.util.general;

/**
 *
 * @description 自定义工具类
 */

public class MyUtils {
    public static String getCode(int length) {
        String str ="123456";
        return str.substring(str.length() - length);
    }

    private MyUtils() {
    }
}

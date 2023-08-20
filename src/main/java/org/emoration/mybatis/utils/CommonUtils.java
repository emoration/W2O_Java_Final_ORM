package org.emoration.mybatis.utils;


import java.util.Collection;
import java.util.Map;


/**
 * @Author czh
 * @Description 工具类
 * @Date 2023/8/4
 */
public final class CommonUtils {

    /**
     * 字符串是否非空(且非纯空白符)
     *
     * @param s 字符串
     * @return 是否非空
     */
    public static boolean isNotEmpty(String s) {
        return s != null && !s.trim().isEmpty();
    }

    /**
     * list/set是否非空
     *
     * @param collection collection
     * @return 是否非空
     */
    public static boolean isNotEmpty(Collection<?> collection) {
        return collection != null && !collection.isEmpty();
    }

    /**
     * map是否非空
     *
     * @param map map
     * @return 是否非空
     */
    public static boolean isNotEmpty(Map<?, ?> map) {
        return map != null && !map.isEmpty();
    }

    /**
     * 数组是否非空
     *
     * @param arr arr
     * @return 是否非空
     */
    public static boolean isNotEmpty(Object[] arr) {
        return arr != null && arr.length > 0;
    }

    /**
     * 对字符串去空白符和换行符等
     *
     * @return 去除后的字符串
     */
    public static String stringTrim(String src) {
        return (null != src) ? src.trim() : null;
    }
}

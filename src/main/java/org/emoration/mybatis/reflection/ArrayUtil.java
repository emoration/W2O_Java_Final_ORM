package org.emoration.mybatis.reflection;

import java.util.Arrays;

/**
 * @Author czh
 * @Description 仿照mybatis的ArrayUtil
 * @Date 2023/8/20
 */

public class ArrayUtil {
    public ArrayUtil() {
    }

    public static int hashCode(Object obj) {
        if (obj == null) {
            return 0;
        } else {
            Class<?> clazz = obj.getClass();
            if (!clazz.isArray()) {
                return obj.hashCode();
            } else {
                Class<?> componentType = clazz.getComponentType();
                if (Long.TYPE.equals(componentType)) {
                    return Arrays.hashCode((long[]) obj);
                } else if (Integer.TYPE.equals(componentType)) {
                    return Arrays.hashCode((int[]) obj);
                } else if (Short.TYPE.equals(componentType)) {
                    return Arrays.hashCode((short[]) obj);
                } else if (Character.TYPE.equals(componentType)) {
                    return Arrays.hashCode((char[]) obj);
                } else if (Byte.TYPE.equals(componentType)) {
                    return Arrays.hashCode((byte[]) obj);
                } else if (Boolean.TYPE.equals(componentType)) {
                    return Arrays.hashCode((boolean[]) obj);
                } else if (Float.TYPE.equals(componentType)) {
                    return Arrays.hashCode((float[]) obj);
                } else {
                    return Double.TYPE.equals(componentType) ? Arrays.hashCode((double[]) obj) : Arrays.hashCode((Object[]) obj);
                }
            }
        }
    }

    public static boolean equals(Object thisObj, Object thatObj) {
        if (thisObj == null) {
            return thatObj == null;
        } else if (thatObj == null) {
            return false;
        } else {
            Class<?> clazz = thisObj.getClass();
            if (!clazz.equals(thatObj.getClass())) {
                return false;
            } else if (!clazz.isArray()) {
                return thisObj.equals(thatObj);
            } else {
                Class<?> componentType = clazz.getComponentType();
                if (Long.TYPE.equals(componentType)) {
                    return Arrays.equals((long[]) thisObj, (long[]) thatObj);
                } else if (Integer.TYPE.equals(componentType)) {
                    return Arrays.equals((int[]) thisObj, (int[]) thatObj);
                } else if (Short.TYPE.equals(componentType)) {
                    return Arrays.equals((short[]) thisObj, (short[]) thatObj);
                } else if (Character.TYPE.equals(componentType)) {
                    return Arrays.equals((char[]) thisObj, (char[]) thatObj);
                } else if (Byte.TYPE.equals(componentType)) {
                    return Arrays.equals((byte[]) thisObj, (byte[]) thatObj);
                } else if (Boolean.TYPE.equals(componentType)) {
                    return Arrays.equals((boolean[]) thisObj, (boolean[]) thatObj);
                } else if (Float.TYPE.equals(componentType)) {
                    return Arrays.equals((float[]) thisObj, (float[]) thatObj);
                } else {
                    return Double.TYPE.equals(componentType) ? Arrays.equals((double[]) thisObj, (double[]) thatObj) : Arrays.equals((Object[]) thisObj, (Object[]) thatObj);
                }
            }
        }
    }

    public static String toString(Object obj) {
        if (obj == null) {
            return "null";
        } else {
            Class<?> clazz = obj.getClass();
            if (!clazz.isArray()) {
                return obj.toString();
            } else {
                Class<?> componentType = obj.getClass().getComponentType();
                if (Long.TYPE.equals(componentType)) {
                    return Arrays.toString((long[]) obj);
                } else if (Integer.TYPE.equals(componentType)) {
                    return Arrays.toString((int[]) obj);
                } else if (Short.TYPE.equals(componentType)) {
                    return Arrays.toString((short[]) obj);
                } else if (Character.TYPE.equals(componentType)) {
                    return Arrays.toString((char[]) obj);
                } else if (Byte.TYPE.equals(componentType)) {
                    return Arrays.toString((byte[]) obj);
                } else if (Boolean.TYPE.equals(componentType)) {
                    return Arrays.toString((boolean[]) obj);
                } else if (Float.TYPE.equals(componentType)) {
                    return Arrays.toString((float[]) obj);
                } else {
                    return Double.TYPE.equals(componentType) ? Arrays.toString((double[]) obj) : Arrays.toString((Object[]) obj);
                }
            }
        }
    }
}

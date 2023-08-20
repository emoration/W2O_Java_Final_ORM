package org.emoration.mybatis.reflection;

import java.util.Arrays;

/**
 * @Author czh
 * @Description TODO
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
                    return Arrays.hashCode((long[])((long[])obj));
                } else if (Integer.TYPE.equals(componentType)) {
                    return Arrays.hashCode((int[])((int[])obj));
                } else if (Short.TYPE.equals(componentType)) {
                    return Arrays.hashCode((short[])((short[])obj));
                } else if (Character.TYPE.equals(componentType)) {
                    return Arrays.hashCode((char[])((char[])obj));
                } else if (Byte.TYPE.equals(componentType)) {
                    return Arrays.hashCode((byte[])((byte[])obj));
                } else if (Boolean.TYPE.equals(componentType)) {
                    return Arrays.hashCode((boolean[])((boolean[])obj));
                } else if (Float.TYPE.equals(componentType)) {
                    return Arrays.hashCode((float[])((float[])obj));
                } else {
                    return Double.TYPE.equals(componentType) ? Arrays.hashCode((double[])((double[])obj)) : Arrays.hashCode((Object[])((Object[])obj));
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
                    return Arrays.equals((long[])((long[])thisObj), (long[])((long[])thatObj));
                } else if (Integer.TYPE.equals(componentType)) {
                    return Arrays.equals((int[])((int[])thisObj), (int[])((int[])thatObj));
                } else if (Short.TYPE.equals(componentType)) {
                    return Arrays.equals((short[])((short[])thisObj), (short[])((short[])thatObj));
                } else if (Character.TYPE.equals(componentType)) {
                    return Arrays.equals((char[])((char[])thisObj), (char[])((char[])thatObj));
                } else if (Byte.TYPE.equals(componentType)) {
                    return Arrays.equals((byte[])((byte[])thisObj), (byte[])((byte[])thatObj));
                } else if (Boolean.TYPE.equals(componentType)) {
                    return Arrays.equals((boolean[])((boolean[])thisObj), (boolean[])((boolean[])thatObj));
                } else if (Float.TYPE.equals(componentType)) {
                    return Arrays.equals((float[])((float[])thisObj), (float[])((float[])thatObj));
                } else {
                    return Double.TYPE.equals(componentType) ? Arrays.equals((double[])((double[])thisObj), (double[])((double[])thatObj)) : Arrays.equals((Object[])((Object[])thisObj), (Object[])((Object[])thatObj));
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
                    return Arrays.toString((long[])((long[])obj));
                } else if (Integer.TYPE.equals(componentType)) {
                    return Arrays.toString((int[])((int[])obj));
                } else if (Short.TYPE.equals(componentType)) {
                    return Arrays.toString((short[])((short[])obj));
                } else if (Character.TYPE.equals(componentType)) {
                    return Arrays.toString((char[])((char[])obj));
                } else if (Byte.TYPE.equals(componentType)) {
                    return Arrays.toString((byte[])((byte[])obj));
                } else if (Boolean.TYPE.equals(componentType)) {
                    return Arrays.toString((boolean[])((boolean[])obj));
                } else if (Float.TYPE.equals(componentType)) {
                    return Arrays.toString((float[])((float[])obj));
                } else {
                    return Double.TYPE.equals(componentType) ? Arrays.toString((double[])((double[])obj)) : Arrays.toString((Object[])((Object[])obj));
                }
            }
        }
    }
}

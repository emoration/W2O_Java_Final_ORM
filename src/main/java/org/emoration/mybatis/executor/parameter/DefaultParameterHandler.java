package org.emoration.mybatis.executor.parameter;


import org.emoration.mybatis.binding.MapperProxy;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.Locale.ENGLISH;


/**
 * @Author czh
 * @Description 默认的参数处理器
 * @Date 2023/8/4
 */
public class DefaultParameterHandler implements ParameterHandler {

    private Object parameter;
    private List<String> paramNames;

    public DefaultParameterHandler(Object parameter, List<String> paramNames) {
        this.parameter = parameter;
        this.paramNames = paramNames;
    }

    /**
     * 将SQL参数设置到PreparedStatement中
     *
     * @param ps
     */
    @Override
    public void setParameters(PreparedStatement ps) {

        try {

            if (null != parameter) {
                if (parameter.getClass().isArray()) {
                    MapperProxy.ParameterPair[] params = (MapperProxy.ParameterPair[]) parameter;
                    Map<String, Object> paramMap = new HashMap<>();
                    for (MapperProxy.ParameterPair param : params) {
                        paramMap.put(param.getParameterName(), param.getParameterValue());
                    }
                    for (int i = 0; i < paramNames.size(); i++) {
                        //Mapper保证传入参数类型匹配，这里就不做类型转换了
                        String paramName = paramNames.get(i);
                        Object paramValue = null;
                        if (paramMap.containsKey(paramName)) {
                            paramValue = paramMap.get(paramName);
                        } else if (params.length == 1) {
                            paramValue = getFieldValue(params[0].getParameterValue(), paramName);
                            if (paramValue != null) {
                                System.out.println("[Warning]参数未找到，尝试将参数视为对象，成功获得字段：" + paramName + "=" + paramValue);
                            } else {
                                paramValue = params[0].getParameterValue();
                                System.out.println("[Warning]参数未找到，尝试将参数直接赋值：" + paramName + "=" + paramValue);
                            }
                        }
                        if (paramValue == null) {
                            throw new SQLException("SQL语句中的参数" + paramName + "未找到");
                        }
                        ps.setObject(i + 1, paramValue);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String getFieldValue(Object object, String fieldName) {
        Class<?> clazz = object.getClass();

        try {
            // Try to get the value through the getter method (if available)
            Method getter = clazz.getMethod("get" + capitalize(fieldName));
            Object value = getter.invoke(object);
            if (value != null) {
                return value.toString();
            }
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException ignored) {
            // Ignore exceptions
        }

        try {
            // Try to get the value directly from the field
            Field field = clazz.getDeclaredField(fieldName);
            field.setAccessible(true);
            Object value = field.get(object);
            if (value != null) {
                return value.toString();
            }
        } catch (NoSuchFieldException | IllegalAccessException ignored) {
            // Ignore exceptions
        }
        return null;
    }

    private static String capitalize(String name) {
        return name.substring(0, 1).toUpperCase(ENGLISH) + name.substring(1);
    }

}

package org.emoration.mybatis.executor.parameter;


import org.emoration.mybatis.binding.MapperProxy;

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

    private final Object parameter;
    private final List<String> paramNames;

    public DefaultParameterHandler(Object parameter, List<String> paramNames) {
        this.parameter = parameter;
        this.paramNames = paramNames;
    }

    /**
     * 将SQL参数设置到PreparedStatement中
     *
     * @param ps PreparedStatement
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
                                System.out.println("[Warning]SQL语句中的{" + paramName + "}未找到，尝试调用参数" + params[0].getParameterName() + "的get方法，成功: " + paramName + "=" + paramValue);
                            } else {
                                paramValue = params[0].getParameterValue();
                                System.out.println("[Warning]SQL语句中的{" + paramName + "}未找到，尝试使用参数" + params[0].getParameterName() + "直接赋值，成功: " + paramName + "=" + paramValue);
                            }
                        }
                        if (paramValue == null) {
                            throw new SQLException("SQL语句中的{" + paramName + "}未找到");
                        }
                        ps.setObject(i + 1, paramValue);
                    }
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 尝试通过get方法获得参数
     *
     * @param object    操作对象
     * @param fieldName 字段名
     * @return 值或者null
     */
    public static Object getFieldValue(Object object, String fieldName) {
        Class<?> clazz = object.getClass();

        try {
            // 尝试通过get方法获取
            Method getter = clazz.getMethod("get" + capitalize(fieldName));
            Object value = getter.invoke(object);
            if (value != null) {
                return value;
            }
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException ignored) {
        }

        return null;
    }

    /**
     * 获取get方法名
     *
     * @param name 字段名
     * @return 对应的get方法名
     */
    private static String capitalize(String name) {
        return name.substring(0, 1).toUpperCase(ENGLISH) + name.substring(1);
    }

}

package org.emoration.mybatis.executor.parameter;


import org.emoration.mybatis.binding.MapperProxy;

import java.sql.PreparedStatement;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


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
//                    ParameterPair[] params = new ParameterPair[((Object[]) parameter).length];
//                    Object[] paramObjects = (Object[]) parameter;
//                    for (int i = 0; i < paramObjects.length; i++) {
//                        ParameterPair param = (ParameterPair) paramObjects[i];
//                        params[i] = param;
//                    }
                    ParameterPair[] params = (ParameterPair[]) parameter;
                    Map<String, Object> paramMap = new HashMap<>();
                    for (ParameterPair param : params) {
                        paramMap.put(param.getParameterName(), param.getParameterValue());
                    }
                    for (int i = 0; i < params.length; i++) {
                        //Mapper保证传入参数类型匹配，这里就不做类型转换了
                        String paramName = paramNames.get(i);
                        Object paramValue = paramMap.get(paramName);
                        ps.setObject(i + 1, paramValue);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

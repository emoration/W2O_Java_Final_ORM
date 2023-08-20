package org.emoration.mybatis.utils.ognl;

import ognl.*;
import org.emoration.mybatis.binding.MapperProxy;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author czh
 * @Description 表达式计算
 * @Date 2023/8/10
 */
public class ExpressionEvaluator {
    public static boolean evaluateBoolean(String expression, Object parameterObject) {
        Map<String, Object> objectMap = new HashMap<>();
        if (parameterObject.getClass().isArray()) {
            MapperProxy.ParameterPair[] params = (MapperProxy.ParameterPair[]) parameterObject;
            for (MapperProxy.ParameterPair param : params) {
                objectMap.put(param.getParameterName(), param.getParameterValue());
            }
        }
        OgnlContext context = Ognl.createDefaultContext(objectMap,
                new DefaultMemberAccess(true), new DefaultClassResolver(), new DefaultTypeConverter());

        Object ans;
        try {
            ans = Ognl.getValue(Ognl.parseExpression(expression), context, context.getRoot());
        } catch (OgnlException e) {
            System.err.println("表达式" + expression + "解析失败");
            throw new RuntimeException(e);
        }
        if (ans instanceof Boolean) {
            return (Boolean) ans;
        }
        throw new RuntimeException("表达式" + expression + "不是布尔表达式");
    }
}

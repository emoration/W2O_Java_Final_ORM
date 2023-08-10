package org.emoration.mybatis.binding;


import java.io.Serializable;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Collection;

import lombok.Data;
import org.emoration.mybatis.mapping.MappedStatement;
import org.emoration.mybatis.session.SqlSession;


/**
 * @Author czh
 * @Description Mapper代理
 * @Date 2023/8/4
 */
public class MapperProxy<T> implements InvocationHandler, Serializable {

    private static final long serialVersionUID = -7861758496991319661L;

    private final SqlSession sqlSession;

    private final Class<T> mapperInterface;

    /**
     * 构造方法
     *
     * @param sqlSession
     * @param mapperInterface
     */
    public MapperProxy(SqlSession sqlSession, Class<T> mapperInterface) {

        this.sqlSession = sqlSession;
        this.mapperInterface = mapperInterface;
    }

    /**
     * 真正的执行方法
     *
     * @param proxy
     * @param method
     * @param args
     * @return
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) {
        try {
            if (Object.class.equals(method.getDeclaringClass())) {
                return method.invoke(this, args);
            }
            Parameter[] parameters = method.getParameters();
            ParameterPair[] parameterPairs = new ParameterPair[parameters.length];
            for (int i = 0; i < parameters.length; i++) {
                parameterPairs[i] = new ParameterPair();
                parameterPairs[i].setParameterValue(args[i]);
                parameterPairs[i].setParameterName(parameters[i].getName());
                if (parameters[i].getAnnotation(org.emoration.mybatis.annotations.Param.class) != null) {
                    parameterPairs[i].setParameterName(parameters[i].getAnnotation(org.emoration.mybatis.annotations.Param.class).value());
                }
            }
            return this.execute(method, parameterPairs);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;

    }

    /**
     * 根据SQL指令执行对应操作
     *
     * @param method
     * @param args
     * @return
     */
    private Object execute(Method method, Object[] args) {
        String statementId = this.mapperInterface.getName() + "." + method.getName();
        MappedStatement ms = this.sqlSession.getConfiguration().getMappedStatement(statementId);

        switch (ms.getSqlCommandType()) {
            case SELECT -> {
                Object result = null;
                Class<?> returnType = method.getReturnType();
                // 如果返回的是list，应该调用查询多个结果的方法，否则只要查单条记录
                if (Collection.class.isAssignableFrom(returnType)) {
                    //ID为mapper类全名+方法名
                    result = sqlSession.selectList(statementId, args);
                } else {
                    result = sqlSession.selectOne(statementId, args);
                }
                return result;
            }
            case UPDATE -> {
                return sqlSession.update(statementId, args);
            }
            case INSERT -> {
                return sqlSession.insert(statementId, args);
            }
            case DELETE -> {
                return sqlSession.delete(statementId, args);
            }
            default -> {
                //TODO 其他方法待实现
            }
        }
        return null;
    }

    /**
     * @Author czh
     * @Description 参数名称-值对
     * @Date 2023/8/6
     */
    @Data
    public static class ParameterPair {
        private Object parameterValue;
        private String parameterName;
    }
}

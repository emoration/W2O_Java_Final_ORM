package org.emoration.mybatis.binding;


import java.lang.reflect.Proxy;

import org.emoration.mybatis.session.SqlSession;


/**
 * @Author czh
 * @Description Mapper代理工厂
 * @Date 2023/8/4
 */
public class MapperProxyFactory<T> {

    private final Class<T> mapperInterface;

    /**
     * 初始化方法
     *
     * @param mapperInterface mapper接口
     */
    public MapperProxyFactory(Class<T> mapperInterface) {
        this.mapperInterface = mapperInterface;
    }

    /**
     * 根据sqlSession创建一个代理
     *
     * @param sqlSession sqlSession
     * @return 代理T
     * @see MapperProxyFactory#newInstance(MapperProxy)
     */
    public T newInstance(SqlSession sqlSession) {
        MapperProxy<T> mapperProxy = new MapperProxy<>(sqlSession, this.mapperInterface);
        return newInstance(mapperProxy);
    }

    /**
     * 根据mapper代理返回实例
     *
     * @param mapperProxy mapper代理
     * @return 代理T
     * @see Proxy#newProxyInstance(ClassLoader, Class[], java.lang.reflect.InvocationHandler)
     */
    @SuppressWarnings("unchecked")
    protected T newInstance(MapperProxy<T> mapperProxy) {
        return (T) Proxy.newProxyInstance(this.mapperInterface.getClassLoader(), new Class[]{this.mapperInterface},
                mapperProxy);
    }
}

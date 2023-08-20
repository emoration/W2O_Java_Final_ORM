package org.emoration.mybatis.cache;


/**
 * @Author czh
 * @Description 缓存的接口
 * @Date 2023/8/19
 */
public interface Cache {

    void putObject(Object var1, Object var2);

    Object getObject(Object var1);

    Object removeObject(Object var1);

    void clear();

    int getSize();

}

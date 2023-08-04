package org.emoration.mybatis.executor;


import java.util.List;

import org.emoration.mybatis.mapping.MappedStatement;


/**
 * @Author czh
 * @Description 执行器
 * @Date 2023/8/4
 */
public interface Executor {

    /**
     * 查询数据库
     *
     * @param ms
     * @param parameter
     * @return
     * @see
     */
    <E> List<E> doQuery(MappedStatement ms, Object parameter);

    /**
     * 更新操作
     *
     * @param ms
     * @param parameter
     */
    void doUpdate(MappedStatement ms, Object parameter);
}

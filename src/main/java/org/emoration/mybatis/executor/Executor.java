package org.emoration.mybatis.executor;


import java.sql.SQLException;
import java.util.List;

import org.emoration.mybatis.cache.CacheKey;
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
    <E> List<E> query(MappedStatement ms, Object parameter) throws SQLException;

    /**
     * 查询数据库, 带缓存key
     *
     * @param ms
     * @param parameter
     * @param key
     * @return
     * @see
     */
    <E> List<E> query(MappedStatement ms, Object parameter, CacheKey key) throws SQLException;

    /**
     * 更新操作
     *
     * @param ms
     * @param parameter
     */
    int update(MappedStatement ms, Object parameter) throws SQLException;

    /**
     * 提交事务
     */
    void commit() throws SQLException;

    /**
     * 设置是否自动提交事务
     *
     * @param autoCommit
     * @throws SQLException
     */
    void setAutoCommit(boolean autoCommit) throws SQLException;

    /**
     * 回滚事务
     */
    void rollback() throws SQLException;

    /**
     * 关闭连接
     */
    void close() throws SQLException;

    /**
     * 判断连接是否关闭
     */
    boolean isClosed();

    /**
     * 生成缓存key
     * @param ms
     * @param parameter
     * @return
     */
    CacheKey createCacheKey(MappedStatement ms, Object parameter);
}

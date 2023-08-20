package org.emoration.mybatis.executor;

import org.emoration.mybatis.cache.Cache;
import org.emoration.mybatis.cache.CacheKey;
import org.emoration.mybatis.mapping.MappedStatement;

import java.sql.SQLException;
import java.util.List;

/**
 * @Author czh
 * @Description TODO
 * @Date 2023/8/19
 */
public class CachingExecutor{}
//public class CachingExecutor implements Executor {
//
//    private final Executor delegate;
//
//    public CachingExecutor(Executor delegate) {
//        this.delegate = delegate;
//    }
//
//    /**
//     * 查询数据库
//     *
//     * @param ms
//     * @param parameter
//     * @return
//     * @see
//     */
//    @Override
//    public <E> List<E> query(MappedStatement ms, Object parameter) throws SQLException {
//        CacheKey key = this.createCacheKey(ms, parameter);
//        return this.delegate.query(ms, parameter, key);
//    }
//
//    @Override
//    public <E> List<E> query(MappedStatement ms, Object parameterObject, CacheKey key) throws SQLException {
//        Cache cache = ms.getCache();
////        if (cache != null) {
////            if (ms.isUseCache() && resultHandler == null) {
////                List<E> list = (List)cache.getObject(key);
////                if (list == null) {
////                    list = this.delegate.query(ms, parameterObject);
////                    cache.putObject(key, list);
////                }
////
////                return list;
////            }
////        }
//
//        return this.delegate.query(ms, parameterObject);
//    }
//
//    /**
//     * 更新操作
//     *
//     * @param ms
//     * @param parameter
//     */
//    @Override
//    public int update(MappedStatement ms, Object parameter) throws SQLException {
////        this.flushCacheIfRequired(ms);
//        Cache cache = ms.getCache();
//        cache.clear();
//        return this.delegate.update(ms, parameter);
//    }
//
//    /**
//     * 提交事务
//     */
//    @Override
//    public void commit() throws SQLException {
//        this.delegate.commit();
//    }
//
//    /**
//     * 设置是否自动提交事务
//     *
//     * @param autoCommit
//     * @throws SQLException
//     */
//    @Override
//    public void setAutoCommit(boolean autoCommit) throws SQLException {
//        this.delegate.setAutoCommit(autoCommit);
//    }
//
//    /**
//     * 回滚事务
//     */
//    @Override
//    public void rollback() throws SQLException {
//        this.delegate.rollback();
//    }
//
//    /**
//     * 关闭连接
//     */
//    @Override
//    public void close() throws SQLException {
//        this.delegate.close();
//    }
//
//    /**
//     * 判断连接是否关闭
//     */
//    @Override
//    public boolean isClosed() {
//        return this.delegate.isClosed();
//    }
//
//    /**
//     * 生成缓存key
//     * @param ms
//     * @param parameter
//     * @return
//     */
//    @Override
//    public CacheKey createCacheKey(MappedStatement ms, Object parameter){
//        return this.delegate.createCacheKey(ms, parameter);
//    }
//}

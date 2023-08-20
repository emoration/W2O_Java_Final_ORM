package org.emoration.mybatis.executor;

import lombok.Getter;
import org.emoration.mybatis.cache.CacheKey;
import org.emoration.mybatis.cache.impl.PerpetualCache;
import org.emoration.mybatis.constants.Constant;
import org.emoration.mybatis.mapping.MappedStatement;
import org.emoration.mybatis.session.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * @Author czh
 * @Description TODO
 * @Date 2023/8/20
 */

public abstract class BaseExecutor implements Executor {
    protected Connection connection;
    protected PerpetualCache localCache;
    protected Configuration configuration;
    @Getter
    private boolean closed;

    protected BaseExecutor(Configuration configuration) {
        this.localCache = new PerpetualCache();
        this.closed = false;
        this.configuration = configuration;
        this.connection = initConnect();
    }

    @Override
    public void close() throws SQLException {
        clearLocalCache();
        localCache = null;
        configuration = null;
        closed = true;
        Connection connection = getConnection();
        if (connection.isClosed()) {
            throw new SQLException("连接已关闭，不能重复关闭");
        }
        connection.close();
    }

    @Override
    public int update(MappedStatement ms, Object parameter) throws SQLException {
        if (this.closed) {
            throw new RuntimeException("Executor was closed.");
        } else {
            this.clearLocalCache();
            return this.doUpdate(ms, parameter);
        }
    }
    static CacheKey keyTest = null;
    @Override
    public <E> List<E> query(MappedStatement ms, Object parameter) throws SQLException {
        CacheKey key = this.createCacheKey(ms, parameter);
        keyTest = key;
        return this.query(ms, parameter, key);
    }

    @Override
    public <E> List<E> query(MappedStatement ms, Object parameter, CacheKey key) throws SQLException {
        if (this.closed) {
            throw new RuntimeException("Executor was closed.");
        } else {
            List list;
            list = (List) this.localCache.getObject(key);
            if (list == null) {
                list = this.doQuery(ms, parameter);
                this.localCache.putObject(key, list);
            }
            return list;
        }
    }

    @Override
    public CacheKey createCacheKey(MappedStatement ms, Object parameterObject) {
        if (this.closed) {
            throw new RuntimeException("Executor was closed.");
        } else {
            CacheKey cacheKey = new CacheKey();
            cacheKey.update(ms.getNamespace());
            cacheKey.update(ms.getSqlId());
            cacheKey.update(parameterObject);
            return cacheKey;
        }
    }

    /**
     * 检查是否在缓存中
     * @param ms
     * @param key
     * @return
     */
    public boolean isCached(MappedStatement ms, CacheKey key) {
        return this.localCache.getObject(key) != null;
    }

    /**
     * 提交
     * @throws SQLException
     */
    @Override
    public void commit() throws SQLException {
        Connection connection = getConnection();
        if (connection.isClosed()) {
            throw new SQLException("连接已关闭，不能提交");
        }
        connection.commit();
    }

    /**
     * 回滚
     * @throws SQLException
     */
    @Override
    public void rollback() throws SQLException {
        Connection connection = getConnection();
        if (connection.isClosed()) {
            throw new SQLException("连接已关闭，不能回滚");
        }
        connection.rollback();
    }

    /**
     * 清空缓存
     */
    public void clearLocalCache() {
        if (!this.closed) {
            this.localCache.clear();
        }

    }

    protected abstract int doUpdate(MappedStatement var1, Object var2) throws SQLException;

    protected abstract <E> List<E> doQuery(MappedStatement var1, Object var2) throws SQLException;

    /**
     * get数据库连接
     *
     * @return
     * @throws SQLException
     */
    public Connection getConnection() throws SQLException {
        if (null != connection) {
            return connection;
        } else {
            throw new SQLException("无法连接数据库/数据库已关闭连接");
        }
    }

    /**
     * 静态初始化数据库连接
     *
     * @return
     */
    protected Connection initConnect() {

        String driver = Configuration.getProperty(Constant.DB_DRIVER_CONF);
        String url = Configuration.getProperty(Constant.DB_URL_CONF);
        String username = Configuration.getProperty(Constant.DB_USERNAME_CONF);
        String password = Configuration.getProperty(Constant.DB_PASSWORD);
        Connection connection = null;
        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(url, username, password);
            System.out.println("数据库连接成功");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }

    /**
     * 设置是否自动提交事务
     *
     * @param autoCommit
     * @throws SQLException
     */
    @Override
    public void setAutoCommit(boolean autoCommit) throws SQLException {
        Connection connection = getConnection();
        if (connection.isClosed()) {
            throw new SQLException("连接已关闭，不能设置自动提交");
        }
        connection.setAutoCommit(autoCommit);
    }
}

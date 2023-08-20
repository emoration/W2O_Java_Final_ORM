package org.emoration.mybatis.session.defaults;


import java.sql.SQLException;
import java.util.List;

import org.emoration.mybatis.executor.Executor;
import org.emoration.mybatis.executor.SimpleExecutor;
import org.emoration.mybatis.mapping.MappedStatement;
import org.emoration.mybatis.session.Configuration;
import org.emoration.mybatis.session.SqlSession;


/**
 * @Author czh
 * @Description 默认的Sql会话
 * @Date 2023/8/4
 */
public class DefaultSqlSession implements SqlSession {

    private final Configuration configuration;

    private final Executor executor;

    private boolean autoCommit;

    public void setAutoCommit(boolean autoCommit) {
        this.autoCommit = autoCommit;
        try {
            this.executor.setAutoCommit(autoCommit);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    private boolean dirty;

    /**
     * 默认构造方法
     *
     * @param configuration
     */
    public DefaultSqlSession(Configuration configuration) {
        this.configuration = configuration;
        this.executor = new SimpleExecutor(configuration);
    }

    /**
     * 查询带条记录
     *
     * @param statementId
     * @return
     */
    @Override
    public <T> T selectOne(String statementId, Object parameter) {
        List<T> results = this.<T>selectList(statementId, parameter);
        if (results == null || results.isEmpty()) {
            return null;
        }
        if(results.size() > 1) {
            System.err.println(results);
            throw new RuntimeException("查询结果不唯一");
        }
        return results.get(0);
    }

    /**
     * 查询多条记录
     *
     * @param statementId ID为mapper类全名+方法名
     * @param parameter   参数列表
     * @return
     */
    @Override
    public <E> List<E> selectList(String statementId, Object parameter) {
        MappedStatement mappedStatement = this.configuration.getMappedStatement(statementId);
        try {
            return this.executor.<E>query(mappedStatement, parameter);
        } catch (SQLException e) {
            rollback();
            close();
            System.out.println("查询失败, 已尝试回滚并关闭连接");
            throw new RuntimeException(e);
        }
    }

    /**
     * 更新
     *
     * @param statementId
     * @param parameter
     */
    @Override
    public int update(String statementId, Object parameter) {
        this.dirty = true;
        MappedStatement mappedStatement = this.configuration.getMappedStatement(statementId);
        try {
            return this.executor.update(mappedStatement, parameter);
        } catch (SQLException e) {
            rollback();
            close();
            System.out.println("更新失败, 已尝试回滚并关闭连接");
            throw new RuntimeException(e);
        }
    }

    /**
     * 插入
     *
     * @param statementId
     * @param parameter
     */
    @Override
    public int insert(String statementId, Object parameter) {
        this.dirty = true;
        MappedStatement mappedStatement = this.configuration.getMappedStatement(statementId);
        try {
            return this.executor.update(mappedStatement, parameter);
        } catch (SQLException e) {
            rollback();
            close();
            System.out.println("插入失败, 已尝试回滚并关闭连接");
            throw new RuntimeException(e);
        }
    }

    /**
     * 删除
     *
     * @param statementId
     * @param parameter
     */
    @Override
    public int delete(String statementId, Object parameter) {
        this.dirty = true;
        MappedStatement mappedStatement = this.configuration.getMappedStatement(statementId);
        try {
            return this.executor.update(mappedStatement, parameter);
        } catch (SQLException e) {
            rollback();
            close();
            System.out.println("删除失败, 已尝试回滚并关闭连接");
            throw new RuntimeException(e);
        }
    }

    /**
     * 提交事务
     */
    @Override
    public void commit(boolean force) {
        if ((this.dirty && this.autoCommit) || force) {
            try {
                this.executor.commit();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        this.dirty = false;
    }

    /**
     * 提交事务
     */
    @Override
    public void commit() {
        commit(false);
    }

    /**
     * 回滚事务
     */
    @Override
    public void rollback(boolean force) {
        if ((this.dirty && this.autoCommit) || force) {
            try {
                this.executor.rollback();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        this.dirty = false;
    }

    /**
     * 回滚事务
     */
    @Override
    public void rollback() {
        rollback(false);
    }

    /**
     * 关闭
     */
    @Override
    public void close() {
        if (this.executor.isClosed()) {
            return;
        }
        try {
            this.executor.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        this.dirty = false;
    }

    /**
     * 获取Mapper
     *
     * @param type
     * @return
     */
    @Override
    public <T> T getMapper(Class<T> type) {
        return configuration.<T>getMapper(type, this);
    }

    /**
     * getConfiguration
     *
     * @return
     */
    @Override
    public Configuration getConfiguration() {
        return this.configuration;
    }

}

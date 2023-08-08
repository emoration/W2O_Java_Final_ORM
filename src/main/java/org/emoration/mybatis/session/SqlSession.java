package org.emoration.mybatis.session;


import java.util.List;


/**
 * @Author czh
 * @Description Sql会话
 * @Date 2023/8/4
 */
public interface SqlSession {

    /**
     * 设置是否自动提交事务
     *
     * @param autoCommit
     */
    void setAutoCommit(boolean autoCommit);

    /**
     * 查询带条记录
     *
     * @param statementId
     * @param parameter
     * @return
     * @see
     */
    <T> T selectOne(String statementId, Object parameter);

    /**
     * 查询多条记录
     *
     * @param statementId
     * @param parameter
     * @return
     * @see
     */
    <E> List<E> selectList(String statementId, Object parameter);

    /**
     * update
     *
     * @param statementId
     * @param parameter
     */
    int update(String statementId, Object parameter);


    /**
     * insert
     *
     * @param statementId
     * @param parameter
     */
    int insert(String statementId, Object parameter);

    /**
     * delete
     *
     * @param statementId
     * @param parameter
     */
    int delete(String statementId, Object parameter);

    /**
     * 提交事务
     */
    void commit(boolean force);

    /**
     * 提交事务
     */
    void commit();

    /**
     * 回滚事务
     */
    void rollback(boolean force);

    /**
     * 回滚事务
     */
    void rollback();

    /**
     * 关闭
     */
    void close();

    /**
     * 获取mapper
     *
     * @param paramClass
     * @return
     * @see
     */
    <T> T getMapper(Class<T> paramClass);

    /**
     * 获取配置类
     *
     * @return
     * @see
     */
    Configuration getConfiguration();
}

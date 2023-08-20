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
     * @param autoCommit 是否自动提交
     */
    void setAutoCommit(boolean autoCommit);

    /**
     * 查询带条记录
     *
     * @param statementId statement的id
     * @param parameter   参数
     * @return 结果
     */
    <T> T selectOne(String statementId, Object parameter);

    /**
     * 查询多条记录
     *
     * @param statementId statement的id
     * @param parameter   参数
     * @return 结果
     */
    <E> List<E> selectList(String statementId, Object parameter);

    /**
     * update
     *
     * @param statementId statement的id
     * @param parameter   参数
     */
    int update(String statementId, Object parameter);


    /**
     * insert
     *
     * @param statementId statement的id
     * @param parameter   参数
     */
    int insert(String statementId, Object parameter);

    /**
     * delete
     *
     * @param statementId statement的id
     * @param parameter   参数
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
     * @param paramClass 类型
     * @return Mapper
     */
    <T> T getMapper(Class<T> paramClass);

    /**
     * 获取配置类
     *
     * @return 配置
     */
    Configuration getConfiguration();
}

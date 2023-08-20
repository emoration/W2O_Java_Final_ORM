package org.emoration.mybatis.executor.statement;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


/**
 * @Author czh
 * @Description statement处理器
 * @Date 2023/8/4
 */
public interface StatementHandler {

    /**
     * SQL预处理
     *
     * @param connection sql的连接
     * @return PreparedStatement
     */
    PreparedStatement prepare(Connection connection, Object parameter) throws SQLException;

    /**
     * 查询数据库
     *
     * @param preparedStatement preparedStatement
     * @return preparedStatement执行结果
     */
    ResultSet query(PreparedStatement preparedStatement) throws SQLException;

    /**
     * update
     *
     * @param preparedStatement preparedStatement
     * @return preparedStatement执行结果
     */
    int update(PreparedStatement preparedStatement) throws SQLException;

    List<String> getParamNames();
}

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
     * @param paramConnection
     * @return
     * @throws SQLException
     * @see
     */
    PreparedStatement prepare(Connection paramConnection, Object parameter) throws SQLException;

    /**
     * 查询数据库
     *
     * @param preparedStatement
     * @return
     * @throws SQLException
     * @see
     */
    ResultSet query(PreparedStatement preparedStatement) throws SQLException;

    /**
     * update
     *
     * @param preparedStatement
     * @throws SQLException
     */
    int update(PreparedStatement preparedStatement) throws SQLException;

    List<String> getParamNames();
}

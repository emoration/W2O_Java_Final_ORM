package org.emoration.mybatis.executor.statement;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import lombok.Getter;
import org.emoration.mybatis.mapping.MappedStatement;
import org.emoration.mybatis.utils.CommonUtils;


/**
 * @Author czh
 * @Description 简单的statement处理器
 * @Date 2023/8/4
 */
public class SimpleStatementHandler implements StatementHandler {
    /**
     * #{}正则匹配
     */
    private static Pattern param_pattern = Pattern.compile("#\\{([^\\{\\}]*)\\}");

    private MappedStatement mappedStatement;

    @Override
    public List<String> getParamNames() {
        return paramNames;
    }

    private List<String> paramNames;

    /**
     * 默认构造方法
     *
     * @param mappedStatement
     */
    public SimpleStatementHandler(MappedStatement mappedStatement) {
        this.paramNames = new ArrayList<>();
        this.mappedStatement = mappedStatement;
    }

    /**
     * prepare
     *
     * @param paramConnection
     * @return
     * @throws SQLException
     */
    @Override
    public PreparedStatement prepare(Connection paramConnection)
            throws SQLException {
        String originalSql = mappedStatement.getSql();

        if (CommonUtils.isNotEmpty(originalSql)) {
            // 替换#{}，预处理，防止SQL注入
            return paramConnection.prepareStatement(parseSymbol(originalSql));
        } else {
            throw new SQLException("original sql is null.");
        }
    }

    /**
     * query
     *
     * @param preparedStatement
     * @return
     * @throws SQLException
     */
    @Override
    public ResultSet query(PreparedStatement preparedStatement)
            throws SQLException {
        return preparedStatement.executeQuery();
    }

    /**
     * update
     *
     * @param preparedStatement
     * @throws SQLException
     */
    @Override
    public int update(PreparedStatement preparedStatement) throws SQLException {
        return preparedStatement.executeUpdate();
    }

    /**
     * 将SQL语句中的#{}替换为？，源码中是在SqlSourceBuilder类中解析的
     *
     * @param source
     * @return
     */
    private String parseSymbol(String source) {
        source = source.trim();
        Matcher matcher = param_pattern.matcher(source);
        while (matcher.find()) {
            String paramName = matcher.group(1); // Get the variable name inside #{}
            paramNames.add(paramName);
        }
        return matcher.replaceAll("?");
    }

}

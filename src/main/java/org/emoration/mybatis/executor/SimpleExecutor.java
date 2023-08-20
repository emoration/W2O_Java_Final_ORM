package org.emoration.mybatis.executor;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.emoration.mybatis.binding.MapperProxy;
import org.emoration.mybatis.executor.parameter.DefaultParameterHandler;
import org.emoration.mybatis.executor.parameter.ParameterHandler;
import org.emoration.mybatis.executor.resultset.DefaultResultSetHandler;
import org.emoration.mybatis.executor.resultset.ResultSetHandler;
import org.emoration.mybatis.executor.statement.SimpleStatementHandler;
import org.emoration.mybatis.executor.statement.StatementHandler;
import org.emoration.mybatis.mapping.MappedStatement;
import org.emoration.mybatis.session.Configuration;


/**
 * @Author czh
 * @Description 简单的执行器
 * @Date 2023/8/4
 */
public class SimpleExecutor extends BaseExecutor {

    /**
     * 初始化方法
     *
     * @param configuration 配置
     */
    public SimpleExecutor(Configuration configuration) {
        super(configuration);
    }

    /**
     * 根据参数查询数据库
     *
     * @param ms        MappedStatement
     * @param parameter 参数
     * @return 结果
     */
    @Override
    public <E> List<E> doQuery(MappedStatement ms, Object parameter) throws SQLException {
        System.out.print("==> SQL   : ");
        System.out.println(ms.getSql());
        System.out.print("==> PARAM : ");
        for (int i = 0; i < ((MapperProxy.ParameterPair[]) parameter).length; i++) {
            if (i != 0) {
                System.out.print(", ");
            }
            System.out.print(((MapperProxy.ParameterPair[]) parameter)[i]);
        }
        System.out.println();
        //1.获取数据库连接
        Connection connection = getConnection();

        //2.获取MappedStatement信息，里面有sql信息
        MappedStatement mappedStatement = configuration.getMappedStatement(ms.getSqlId());

        //3.实例化StatementHandler对象，
        StatementHandler statementHandler = new SimpleStatementHandler(mappedStatement);

        //4.通过StatementHandler和connection获取PreparedStatement
        PreparedStatement preparedStatement = statementHandler.prepare(connection, parameter);

        //5.实例化ParameterHandler，将SQL语句中？参数化
        ParameterHandler parameterHandler = new DefaultParameterHandler(parameter, statementHandler.getParamNames());
        parameterHandler.setParameters(preparedStatement);

        //6.执行SQL，得到结果集ResultSet
        ResultSet resultSet = statementHandler.query(preparedStatement);

        //7.实例化ResultSetHandler，通过反射将ResultSet中结果设置到目标resultType对象中
        ResultSetHandler resultSetHandler = new DefaultResultSetHandler(mappedStatement);
        List<E> results = resultSetHandler.handleResultSets(resultSet);
        System.out.println("<== RESULT: " + results);
        return results;
    }

    /**
     * doUpdate
     *
     * @param ms        MappedStatement
     * @param parameter 参数
     */
    @Override
    public int doUpdate(MappedStatement ms, Object parameter) throws SQLException {
        System.out.print("==> SQL   : ");
        System.out.println(ms.getSql());
        System.out.print("==> PARAM : ");
        for (int i = 0; i < ((MapperProxy.ParameterPair[]) parameter).length; i++) {
            if (i != 0) {
                System.out.print(", ");
            }
            System.out.print(((MapperProxy.ParameterPair[]) parameter)[i]);
        }
        System.out.println();
        //1.获取数据库连接
        Connection connection = getConnection();

        //2.获取MappedStatement信息，里面有sql信息
        MappedStatement mappedStatement = configuration.getMappedStatement(ms.getSqlId());

        //3.实例化StatementHandler对象，
        StatementHandler statementHandler = new SimpleStatementHandler(mappedStatement);

        //4.通过StatementHandler和connection获取PreparedStatement
        PreparedStatement preparedStatement = statementHandler.prepare(connection, parameter);

        //5.实例化ParameterHandler，将SQL语句中？参数化
        ParameterHandler parameterHandler = new DefaultParameterHandler(parameter, statementHandler.getParamNames());
        parameterHandler.setParameters(preparedStatement);

        //6.执行SQL
        int result = statementHandler.update(preparedStatement);
        System.out.println("<== RESULT: " + result);
        return result;
    }

}

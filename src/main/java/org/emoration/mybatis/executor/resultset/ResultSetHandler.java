package org.emoration.mybatis.executor.resultset;


import java.sql.ResultSet;
import java.util.List;


/**
 * @Author czh
 * @Description 结果处理器
 * @Date 2023/8/4
 */
public interface ResultSetHandler {

    /**
     * 处理查询结果
     *
     * @param resultSet
     * @return
     * @see
     */
    <E> List<E> handleResultSets(ResultSet resultSet);

}

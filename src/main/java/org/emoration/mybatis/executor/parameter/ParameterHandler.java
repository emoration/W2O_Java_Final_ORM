package org.emoration.mybatis.executor.parameter;


import java.sql.PreparedStatement;


/**
 * @Author czh
 * @Description 参数处理器
 * @Date 2023/8/4
 */
public interface ParameterHandler {

    /**
     * 设置参数
     *
     * @param paramPreparedStatement 给PreparedStatement设置参数
     */
    void setParameters(PreparedStatement paramPreparedStatement);
}

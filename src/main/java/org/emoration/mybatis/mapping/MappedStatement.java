package org.emoration.mybatis.mapping;

import lombok.Data;
import org.emoration.mybatis.constants.Constant.SqlType;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author czh
 * @Description 映射statement
 * @Date 2023/8/4
 */
@Data
public final class MappedStatement {

    /**
     * mapper文件的namespace
     */
    private String namespace;

    /**
     * sql的id属性
     */
    private String sqlId;

    /**
     * sql语句，对应源码的sqlSource
     */
    private String sql;

    /**
     * 返回类型
     */
    private String resultType;

    /**
     * 参数类型
     */
    private String parameterType;

    /**
     * where动态SQL
     */
    private List<IfSqlNode> ifSqlNodeList=new ArrayList<>();

    /**
     * sqlCommandType对应select/update/insert等
     */
    private SqlType sqlCommandType;

    @Data
    public static class IfSqlNode {
        private String test;
        private String sql;
    }
}

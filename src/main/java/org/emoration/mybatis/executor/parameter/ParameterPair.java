package org.emoration.mybatis.executor.parameter;

import lombok.Data;

/**
 * @Author czh
 * @Description 参数名称-值对
 * @Date 2023/8/6
 */
@Data
public class ParameterPair{
    private Object parameterValue;
    private String parameterName;
}
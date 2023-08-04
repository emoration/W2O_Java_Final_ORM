package org.emoration.mybatis.executor.parameter;


import java.sql.PreparedStatement;


/**
 * @Author czh
 * @Description 默认的参数处理器
 * @Date 2023/8/4
 */
public class DefaultParameterHandler implements ParameterHandler {

    private Object parameter;

    public DefaultParameterHandler(Object parameter) {
        this.parameter = parameter;
    }

    /**
     * 将SQL参数设置到PreparedStatement中
     *
     * @param ps
     */
    @Override
    public void setParameters(PreparedStatement ps) {

        try {

            if (null != parameter) {
                if (parameter.getClass().isArray()) {
                    Object[] params = (Object[]) parameter;
                    for (int i = 0; i < params.length; i++) {
                        //Mapper保证传入参数类型匹配，这里就不做类型转换了
                        ps.setObject(i + 1, params[i]);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

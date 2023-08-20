package org.emoration.mybatis.constants;


/**
 * @Author czh
 * @Description 一些常量
 * @Date 2023/8/4
 */
public interface Constant {
    /**
     * UTF-8编码
     */
    String CHARSET_UTF8 = "UTF-8";

    /**
     * 在properties文件中配置信息
     **/
    String MAPPER_LOCATION = "mapper.location";

    String DB_DRIVER_CONF = "db.driver";

    String DB_URL_CONF = "db.url";

    String DB_USERNAME_CONF = "db.username";

    String DB_PASSWORD = "db.password";

    //************ mapper xml  ****************/

    /**
     * mapper文件后缀
     */
    String MAPPER_FILE_SUFFIX = ".xml";

    String XML_ROOT_LABEL = "mapper";

    String XML_ELEMENT_ID = "id";

    String XML_SELECT_NAMESPACE = "namespace";

    String XML_SELECT_RESULT_TYPE = "resultType";

    String XML_SELECT_PARAMETER_TYPE = "parameterType";

    String XML_IF_LABEL = "if";

    String XML_IF_TEST = "test";

    /**
     * SQL类型枚举，如select、insert、update
     */
    enum SqlType {
        SELECT("select"),
        INSERT("insert"),
        UPDATE("update"),
        DELETE("delete"),
        DEFAULT("default");

        private final String value;

        SqlType(String value) {
            this.value = value;
        }

        public String value() {
            return this.value;
        }
    }

}

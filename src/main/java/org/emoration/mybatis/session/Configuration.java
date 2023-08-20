package org.emoration.mybatis.session;


import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.emoration.mybatis.binding.MapperRegistry;
import org.emoration.mybatis.mapping.MappedStatement;


/**
 * @Author czh
 * @Description mybatis配置
 * @Date 2023/8/4
 */
public class Configuration {
    /**
     * 配置项
     */
    public static Properties PROPS = new Properties();

    /**
     * mapper代理注册器
     */
    protected final MapperRegistry mapperRegistry = new MapperRegistry();

    /**
     * mapper文件的select/update语句的id和SQL语句属性
     **/
    protected final Map<String, MappedStatement> mappedStatements = new HashMap<>();

    /**
     * addMapper
     *
     * @param type Mapper类型
     */
    public <T> void addMapper(Class<T> type) {
        this.mapperRegistry.addMapper(type);
    }

    /**
     * getMapper
     *
     * @param type       Mapper类型
     * @param sqlSession sqlSession
     * @return Mapper
     */
    public <T> T getMapper(Class<T> type, SqlSession sqlSession) {
        return this.mapperRegistry.getMapper(type, sqlSession);
    }

    /**
     * addMappedStatement
     *
     * @param key             MappedStatement的key
     * @param mappedStatement mappedStatement
     */
    public void addMappedStatement(String key, MappedStatement mappedStatement) {
        this.mappedStatements.put(key, mappedStatement);
    }

    /**
     * 获取MappedStatement
     *
     * @param id xml文件标签的id属性
     * @return MappedStatement
     */
    public MappedStatement getMappedStatement(String id) {
        return this.mappedStatements.get(id);
    }

    /**
     * 获取字符型属性(默认值为空字符串)
     *
     * @param key Property的key
     * @return 对应值
     */
    public static String getProperty(String key) {
        return getProperty(key, "");
    }

    /**
     * 获取字符型属性(可指定默认值)
     *
     * @param key          Property的key
     * @param defaultValue 默认值
     * @return 对应值
     */
    public static String getProperty(String key, String defaultValue) {

        return PROPS.containsKey(key) ? PROPS.getProperty(key) : defaultValue;
    }

}

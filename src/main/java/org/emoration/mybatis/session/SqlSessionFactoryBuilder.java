package org.emoration.mybatis.session;


import java.io.IOException;
import java.io.InputStream;

import org.emoration.mybatis.session.defaults.DefaultSqlSessionFactory;


/**
 * @Author czh
 * @Description Sql会话工厂builder
 * @Date 2023/8/4
 */
public class SqlSessionFactoryBuilder {

    /**
     * build
     *
     * @param fileName
     * @return
     * @see
     */
    public SqlSessionFactory build(String fileName) {

        InputStream inputStream = SqlSessionFactoryBuilder.class.getClassLoader().getResourceAsStream(fileName);

        return build(inputStream);
    }

    /**
     * build
     *
     * @param inputStream
     * @return
     * @see
     */
    public SqlSessionFactory build(InputStream inputStream) {
        try {
            Configuration.PROPS.load(inputStream);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return new DefaultSqlSessionFactory(new Configuration());
    }
}

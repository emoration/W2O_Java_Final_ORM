package org.emoration.mybatis.session.defaults;


import java.io.File;
import java.net.URL;

import org.emoration.mybatis.constants.Constant;
import org.emoration.mybatis.session.Configuration;
import org.emoration.mybatis.session.SqlSession;
import org.emoration.mybatis.session.SqlSessionFactory;
import org.emoration.mybatis.utils.CommonUtils;
import org.emoration.mybatis.utils.XmlUtil;


/**
 * @Author czh
 * @Description 默认的Sql会话工厂
 * @Date 2023/8/4
 */
public class DefaultSqlSessionFactory implements SqlSessionFactory {

    /**
     * the configuration
     */
    private final Configuration configuration;

    /**
     * @param configuration 配置
     */
    public DefaultSqlSessionFactory(Configuration configuration) {

        this.configuration = configuration;
        loadMappersInfo(Configuration.getProperty(Constant.MAPPER_LOCATION).replaceAll("\\.", "/"));
    }

    /**
     * 开启会话
     *
     * @return SqlSession
     */
    @Override
    public SqlSession openSession() {

        return new DefaultSqlSession(this.configuration);
    }

    /**
     * loadMappersInfo
     *
     * @param dirName mapper的目录名
     */
    private void loadMappersInfo(String dirName) {

        URL resources = DefaultSqlSessionFactory.class.getClassLoader().getResource(dirName);

        assert resources != null;
        File mappersDir = new File(resources.getFile());

        if (mappersDir.isDirectory()) {

            // 显示包下所有文件
            File[] mappers = mappersDir.listFiles();
            if (CommonUtils.isNotEmpty(mappers)) {
                for (File file : mappers) {

                    // 对文件夹继续递归
                    if (file.isDirectory()) {
                        loadMappersInfo(dirName + "/" + file.getName());

                    } else if (file.getName().endsWith(Constant.MAPPER_FILE_SUFFIX)) {

                        // 只对XML文件解析
                        XmlUtil.readMapperXml(file, this.configuration);
                    }

                }

            }
        }

    }

}

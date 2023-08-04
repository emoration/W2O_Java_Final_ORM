package org.emoration.mybatis.session;

/**
 * @Author czh
 * @Description Sql会话工厂
 * @Date 2023/8/4
 */
public interface SqlSessionFactory {

    /**
     * 开启数据库会话
     *
     * @return
     * @see
     */
    SqlSession openSession();

}

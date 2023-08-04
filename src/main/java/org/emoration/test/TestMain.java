package org.emoration.test;


import org.emoration.mybatis.session.SqlSession;
import org.emoration.mybatis.session.SqlSessionFactory;
import org.emoration.mybatis.session.SqlSessionFactoryBuilder;

import org.emoration.test.bean.User;
import org.emoration.test.dao.UserMapper;


/**
 * @Author czh
 * @Description 测试
 * @Date 2023/8/4
 */
public class TestMain {

    /**
     * main
     *
     * @param args
     */
    public static void main(String[] args) {
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build("conf.properties");
        SqlSession session = factory.openSession();

        UserMapper userMapper = session.getMapper(UserMapper.class);
        User user = userMapper.getUser("1");
        System.out.println("******* " + user);
        System.out.println("*******all " + userMapper.getAll());

        userMapper.updateUser("1123", "1");
        System.out.println("*******all " + userMapper.getAll());
    }

}
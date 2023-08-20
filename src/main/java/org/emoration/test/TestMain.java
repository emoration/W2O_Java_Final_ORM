package org.emoration.test;


import org.emoration.mybatis.session.SqlSession;
import org.emoration.mybatis.session.SqlSessionFactory;
import org.emoration.mybatis.session.SqlSessionFactoryBuilder;

import org.emoration.test.bean.User;
import org.emoration.test.bean.Zoo;
import org.emoration.test.dao.UserMapper;
import org.emoration.test.dao.ZooMapper;

import java.util.List;


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
        ZooMapper zooMapper = session.getMapper(ZooMapper.class);
        User user;
        List<User> userList;

        System.out.println(userMapper.deleteUserById("1"));
        System.out.println(userMapper.insertUserByUser(new User("1", "123")));
        System.out.println(userMapper.selectUserByIdAndName("1", "123"));
        System.out.println(userMapper.selectUserByIdAndName("1", "123"));
//        userMapper.deleteUserById("3");

//        session.setAutoCommit(false);
//        System.out.println(userMapper.insertUserByUser(new User("3", "123")));
//        System.out.println(userMapper.updateUserByUser(new User("3", "123123123")));
//        System.out.println(userMapper.updateUserByUserWithParameterType(new User("3", "123123123")));
//        System.out.println(userMapper.selectSystem("0"));
//        session.rollback();
//        session.close();
//        session.commit();

//        User user = userMapper.selectUserById("3");
//        System.out.println("******* " + user);
//        System.out.println("*******all " + userMapper.selectAllUser());
//        System.out.println(userMapper.updateUser("1", "1123123"));
//        System.out.println("*******all " + userMapper.getAll());
//        zooMapper.insertZooByZoo(new Zoo(0, "1", 1000.17));
    }

}

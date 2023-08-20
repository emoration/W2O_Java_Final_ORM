package org.emoration.test;


import org.emoration.mybatis.session.SqlSession;
import org.emoration.mybatis.session.SqlSessionFactory;
import org.emoration.mybatis.session.SqlSessionFactoryBuilder;

import org.emoration.test.bean.User;
import org.emoration.test.bean.Zoo;
import org.emoration.test.dao.UserMapper;
import org.emoration.test.dao.ZooMapper;


/**
 * @Author czh
 * @Description 测试
 * @Date 2023/8/4
 */
public class TestMain {

    public static void main(String[] args) {
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build("conf.properties");
        SqlSession session = factory.openSession();
        UserMapper userMapper = session.getMapper(UserMapper.class);
        ZooMapper zooMapper = session.getMapper(ZooMapper.class);

        // 测试增删改查
        // 删
        System.out.println(userMapper.deleteUserById("0") + "\n"); // 普通删除
        System.out.println(userMapper.deleteUserById("1") + "\n");
        System.out.println(userMapper.deleteUserById("2") + "\n");
        System.out.println(userMapper.deleteUserById("3") + "\n");
        // 增
        System.out.println(userMapper.insertUserByUser(new User("0", "s")) + "\n"); // 普通插入
        System.out.println(userMapper.insertUserByUser(new User("1", "system")) + "\n");
        System.out.println(userMapper.insertUserByUser(new User("2", "Tom")) + "\n");
        System.out.println(userMapper.insertUserByUser(new User("3", "Jan")) + "\n");
        // 改
        System.out.println(userMapper.updateUserNameById("0", "ss") + "\n"); // 普通更新
        System.out.println(userMapper.updateUserNameByUser(new User("2", "Mike")) + "\n"); // 参数为User的更新(不指定参数类型)
        System.out.println(userMapper.updateUserNameByUserWithParameterType(new User("3", "Mary")) + "\n"); // 参数为User的更新(指定参数类型)
        // 查
        System.out.println(userMapper.selectAll() + "\n"); // 普通查询
        System.out.println(userMapper.selectAll() + "\n"); // 普通查询(使用缓存)
        System.out.println(userMapper.selectUserById("0") + "\n"); // 普通查询
        System.out.println(userMapper.selectUserById("0") + "\n"); // 普通查询(使用缓存)
        System.out.println(userMapper.selectUserByRawId("1") + "\n"); // 不使用@Param的查询
        System.out.println(userMapper.selectUserByAliasId("2") + "\n"); // 使用@Param的查询
        System.out.println(userMapper.selectUserByIdAndName("3", "Jan") + "\n"); // 普通查询
        System.out.println(userMapper.selectSystem("0") + "\n"); // 动态SQL查询
        System.out.println(userMapper.selectSystem("1") + "\n"); // 动态SQL查询

        // 测一下Integer类型和Double的参数
        // 删
        System.out.println(zooMapper.deleteZooById(1) + "\n");
        System.out.println(zooMapper.deleteZooById(2) + "\n");
        System.out.println(zooMapper.deleteZooById(3) + "\n");
        // 增
        System.out.println(zooMapper.insertZooByZoo(new Zoo(1, "cat", 1.0)) + "\n");
        System.out.println(zooMapper.insertZooByZoo(new Zoo(2, "dog", 2.0)) + "\n");
        System.out.println(zooMapper.insertZooByZoo(new Zoo(3, "pig", 3.0)) + "\n");
        // 改
        System.out.println(zooMapper.updateZooByZooByParam(1, "cat", 1.1) + "\n");
        System.out.println(zooMapper.updateZooByZoo(new Zoo(1, "cat", 1.1)) + "\n");
        System.out.println(zooMapper.updateZooByZooWithParameterType(new Zoo(2, "dog", 2.2)) + "\n");
        // 查
        System.out.println(zooMapper.selectALl()); //测试重名方法
        System.out.println(zooMapper.selectZooById(1) + "\n");
        System.out.println(zooMapper.selectZooByName("cat") + "\n");
        System.out.println(zooMapper.selectZooByPrice(3.0) + "\n");

    }

}

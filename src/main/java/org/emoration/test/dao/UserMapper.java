package org.emoration.test.dao;


import java.util.List;

import org.emoration.mybatis.annotations.Param;
import org.emoration.test.bean.User;


/**
 * @Author czh
 * @Description UserMapper接口
 * @Date 2023/8/4
 */
public interface UserMapper {
    /**
     * 根据id查询用户（使用注解指定参数名称）
     */
    User selectUserById(@Param("id") String id);

    /**
     * 根据id查询用户，不使用注解
     */
    User selectUserByRawId(String id);

    /**
     * 根据id查询系统用户，id为0的均为系统用户，id非0是name为system的是系统用户（使用动态sql）
     */
    User selectSystem(@Param("id") String id);

    /**
     * 根据id查询用户（使用注解指定别名）
     */
    User selectUserByAliasId(@Param("alias_id") String AliasId);

    /**
     * 根据id和name查询用户（使用注解指定参数名称）
     */
    User selectUserByIdAndName(@Param("id") String id, @Param("name") String name);

    /**
     * 查询所有用户
     */
    List<User> selectAllUser();

    /**
     * 根据id更新用户name
     */
    int updateUserNameById(@Param("id") String id, @Param("name") String name);

    /**
     * 根据id更新用户name（使用自动实体类映射）
     */
    int updateUserByUser(User user);

    /**
     * 根据id更新用户name（使用实体类映射，指定实体类参数类型）
     */
    int updateUserByUserWithParameterType(User user);

    /**
     * 插入用户（使用自动实体类映射）
     */
    int insertUserByUser(User user);

    /**
     * 指定id删除用户
     */
    int deleteUserById(@Param("id") String id);
}

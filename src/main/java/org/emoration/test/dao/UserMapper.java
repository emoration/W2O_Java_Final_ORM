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

    User getUser(@Param("id") String id);

    List<User> getAll();

    int updateUser(@Param("id") String id, @Param("name") String name);

    int insertUser(User user);

    int updateUserByUser(User user);
}

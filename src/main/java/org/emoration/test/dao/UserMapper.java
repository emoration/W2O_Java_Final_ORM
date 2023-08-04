package org.emoration.test.dao;


import java.util.List;

import org.emoration.test.bean.User;


/**
 * @Author czh
 * @Description UserMapper接口
 * @Date 2023/8/4
 */
public interface UserMapper {

    /**
     * 获取单个user
     *
     * @param id
     * @return
     * @see
     */
    User getUser(String id);

    /**
     * 获取所有用户
     *
     * @return
     * @see
     */
    List<User> getAll();

    /**
     * 更新用户（功能未完成）
     *
     * @param id
     */
    void updateUser(String name, String id);
}

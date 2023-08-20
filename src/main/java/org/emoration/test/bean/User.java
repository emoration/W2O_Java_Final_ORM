package org.emoration.test.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author czh
 * @Description User实体类
 * @Date 2023/8/4
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    /**
     * the id
     */
    private String id;

    /**
     * the name
     */
    private String name;

}

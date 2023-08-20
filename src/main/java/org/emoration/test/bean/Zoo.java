package org.emoration.test.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author czh
 * @Description 测试用的实体类
 * @Date 2023/8/11
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Zoo {
    private Integer id;
    private String name;
    private Double price;
}

package org.emoration.test.dao;

import org.emoration.test.bean.Zoo;

/**
 * @Author czh
 * @Description TODO
 * @Date 2023/8/11
 */
public interface ZooMapper {
    Zoo selectZooById(String id);
    Zoo selectZooByName(String name);
    int insertZooByZoo(Zoo zoo);
    int updateZooById(Zoo zoo);
    int deleteZooById(String id);
}

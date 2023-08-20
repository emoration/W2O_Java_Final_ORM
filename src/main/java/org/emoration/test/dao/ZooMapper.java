package org.emoration.test.dao;

import org.emoration.mybatis.annotations.Param;
import org.emoration.test.bean.Zoo;

import java.util.List;

/**
 * @Author czh
 * @Description Zoo的Mapper
 * @Date 2023/8/11
 */
public interface ZooMapper {

    List<Zoo> selectALl();
    Zoo selectZooById(Integer id);

    Zoo selectZooByName(String name);

    Zoo selectZooByPrice(Double price);

    int insertZooByZoo(Zoo zoo);

    int updateZooByZoo(Zoo zoo);

    int updateZooByZooByParam(@Param("id") Integer id, @Param("name") String name, @Param("price") Double price);

    int updateZooByZooWithParameterType(Zoo zoo);

    int deleteZooById(Integer id);
}

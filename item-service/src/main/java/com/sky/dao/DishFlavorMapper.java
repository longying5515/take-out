package com.sky.dao;


import com.sky.pojo.entity.DishFlavor;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface DishFlavorMapper {

    void insertBatch(List<DishFlavor> flavors);

    /**
     *
     * @param dishId
     */
    @Delete("delete from dish_flavor where dish_id =#{dishId}")
    void deleteByDishId(Long dishId);
    @Select("select * from dish_flavor where dish_id=#{id}")
    List<DishFlavor> getByDishId(Long id);
}

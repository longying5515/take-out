package com.zyt.mapper;

import com.github.pagehelper.Page;
import com.zyt.annocation.AutoFill;
import com.zyt.dto.DishPageQueryDTO;
import com.zyt.entity.Dish;
import com.zyt.entity.DishFlavor;
import com.zyt.enumeration.OperationType;
import com.zyt.vo.DishVO;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.ArrayList;
import java.util.List;

@Mapper
public interface DishMapper {

    /**
     * 根据分类id查询菜品数量
     * @param categoryId
     * @return
     */
    @Select("select count(id) from dish where category_id = #{categoryId}")
    Integer countByCategoryId(Long categoryId);

    /**
     *
     * @param dish
     */
    @AutoFill(value = OperationType.INSERT)
    void insert(Dish dish);

    Page<DishVO> pageQuery(DishPageQueryDTO dishPageQueryDTO);

    @Select("select * from dish where id = #{id}")
    Dish getById(Long id);


    @Delete("delete from dish where id =#{id}")
    void deleteById(Long id);

    List<Dish> list(Dish dish);

    @Select("select * from dish_flavor where dish_id = #{dishId}")

    List<DishFlavor> getByDishId(Long dishId);

    ArrayList<Dish> getByCategoryId(Long categoryId, Integer status);
}

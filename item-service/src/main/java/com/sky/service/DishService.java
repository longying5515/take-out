package com.sky.service;


import com.sky.pojo.dto.DishDTO;
import com.sky.pojo.dto.DishPageQueryDTO;
import com.sky.pojo.entity.Dish;
import com.sky.pojo.vo.DishVO;
import com.sky.result.PageResult;

import java.util.List;

public interface DishService {
    /**
     * 新增菜品
     * @param dishDTO
     */
    public void saveWithFlavor(DishDTO dishDTO);

    PageResult pageQuery(DishPageQueryDTO dishPageQueryDTO);

    void deleteBatch(List<Long> ids);
    /**
     * 条件查询菜品和口味
     * @param dish
     * @return
     */
    List<DishVO> listWithFlavor(Dish dish);


    Dish getById(Long id);
}

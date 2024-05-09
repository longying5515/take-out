package com.zyt.service;


import com.zyt.pojo.dto.DishDTO;
import com.zyt.pojo.dto.DishPageQueryDTO;
import com.zyt.pojo.entity.Dish;
import com.zyt.pojo.vo.DishVO;
import com.zyt.result.PageResult;

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

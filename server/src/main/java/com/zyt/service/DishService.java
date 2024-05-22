package com.zyt.service;

import com.zyt.dto.DishDTO;
import com.zyt.dto.DishPageQueryDTO;
import com.zyt.entity.Dish;
import com.zyt.result.PageResult;
import com.zyt.vo.DishVO;

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


}

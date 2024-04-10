package com.sky.mapper;

import com.github.pagehelper.Page;
import com.sky.annocation.AutoFill;
import com.sky.dto.DishPageQueryDTO;
import com.sky.entity.Dish;
import com.sky.enumeration.OperationType;
import com.sky.vo.DishVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface SetmealDishMapper {
    /**
     *
     * @return
     */

    public List<Long> getSetmealIdsByDIshIds(List<Long> dishIds);
}

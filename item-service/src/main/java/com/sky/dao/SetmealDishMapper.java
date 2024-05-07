package com.sky.dao;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SetmealDishMapper {
    /**
     *
     * @return
     */

    public List<Long> getSetmealIdsByDIshIds(List<Long> dishIds);
}

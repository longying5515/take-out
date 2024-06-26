package com.zyt.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.zyt.constant.MessageConstant;
import com.zyt.constant.StatusConstant;
import com.zyt.exception.DeletionNotAllowedException;
import com.zyt.dao.DishFlavorMapper;
import com.zyt.dao.DishMapper;
import com.zyt.dao.SetmealDishMapper;
import com.zyt.pojo.dto.DishDTO;
import com.zyt.pojo.dto.DishPageQueryDTO;
import com.zyt.pojo.entity.Dish;
import com.zyt.pojo.entity.DishFlavor;
import com.zyt.pojo.vo.DishVO;
import com.zyt.result.PageResult;
import com.zyt.service.DishService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@Slf4j
public class DishServiceImpl implements DishService {
    @Autowired
    private DishMapper dishMapper;
    @Autowired
    DishFlavorMapper dishFlavorMapper;
    @Autowired
    SetmealDishMapper setmealDishMapper;
    @Override
    @Transactional//开启事务
    public void saveWithFlavor(DishDTO dishDTO) {

        Dish dish =new Dish();
        BeanUtils.copyProperties(dishDTO,dish);
        //向菜品表插入数据
        dishMapper.insert(dish);
        //获取主键值
        Long dishId = dish.getId();
        //向口味表插入数据
        List<DishFlavor> flavors=dishDTO.getFlavors();
        if(flavors!=null&&flavors.size()>0){
            flavors.forEach(dishFlavor ->{
                dishFlavor.setDishId(dishId);
            } );
            dishFlavorMapper.insertBatch(flavors);
        }

    }

    /**
     *
     * @param dishPageQueryDTO
     * @return
     */
    @Override
    public PageResult pageQuery(DishPageQueryDTO dishPageQueryDTO) {
        PageHelper.startPage(dishPageQueryDTO.getPage(),dishPageQueryDTO.getPageSize());
        Page<DishVO> page = dishMapper.pageQuery(dishPageQueryDTO);
        return new PageResult(page.getTotal(),page.getResult());
    }

    @Transactional
    @Override
    public void deleteBatch(List<Long> ids) {
        //是否存在起售中的商品
        ids.forEach(id->{
            Dish dish = dishMapper.getById(id);
            if(Objects.equals(dish.getStatus(), StatusConstant.ENABLE)){
                throw new DeletionNotAllowedException(MessageConstant.DISH_ON_SALE);
            }

        });
        //被套餐关联

        List<Long> setmealIdsByDIshIds = setmealDishMapper.getSetmealIdsByDIshIds(ids);
        if(setmealIdsByDIshIds!=null&&setmealIdsByDIshIds.size()>0){
            throw new DeletionNotAllowedException(MessageConstant.DISH_BE_RELATED_BY_SETMEAL);
        }

        //删除菜品数据
        ids.forEach(id->{
            dishMapper.deleteById(id);
            //删除口味数据
            dishFlavorMapper.deleteByDishId(id);
        });


    }

    /**
     * 条件查询菜品和口味
     * @param dish
     * @return
     */
    public List<DishVO> listWithFlavor(Dish dish) {
        ArrayList<Dish> dishList = dishMapper.getByCategoryId(dish.getCategoryId(), dish.getStatus());

        List<DishVO> dishVOList = new ArrayList<>();

        for (Dish d : dishList) {
            DishVO dishVO = new DishVO();
            BeanUtils.copyProperties(d,dishVO);

            //根据菜品id查询对应的口味
            List<DishFlavor> flavors = dishFlavorMapper.getByDishId(d.getId());

            dishVO.setFlavors(flavors);
            dishVOList.add(dishVO);
        }

        return dishVOList;
    }

    @Override
    public Dish getById(Long id) {
        return dishMapper.getById(id);
    }
}

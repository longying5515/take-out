package com.sky.service.impl;

import com.sky.client.ItemClient;
import com.sky.dao.ShoppingCartMapper;
import com.sky.pojo.dto.ShoppingCartDTO;
import com.sky.pojo.entity.Dish;
import com.sky.pojo.entity.ShoppingCart;
import com.sky.result.Result;
import com.sky.service.ShoppingCartService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {

    @Autowired
    private ShoppingCartMapper shoppingCartMapper;
    @Autowired
    private ItemClient itemClient;
//    @Autowired
//    private SetmealMapper setmealMapper;
    @Autowired
    private ShoppingCartService shoppingCartService;

    /**
     * 添加购物车
     *
     * @param shoppingCartDTO
     */
    public void addShoppingCart(ShoppingCartDTO shoppingCartDTO) {
        ShoppingCart shoppingCart = new ShoppingCart();
        BeanUtils.copyProperties(shoppingCartDTO, shoppingCart);
        //只能查询自己的购物车数据
        shoppingCart.setUserId(/*BaseContext.getCurrentId()*/4L);

        //判断当前商品是否在购物车中
        List<ShoppingCart> shoppingCartList = shoppingCartMapper.list(shoppingCart);

        if (shoppingCartList != null && shoppingCartList.size() == 1) {
            //如果已经存在，就更新数量，数量加1
            shoppingCart = shoppingCartList.get(0);
            shoppingCart.setNumber(shoppingCart.getNumber() + 1);
            shoppingCartMapper.updateNumberById(shoppingCart);
        } else {
            //如果不存在，插入数据，数量就是1

            //判断当前添加到购物车的是菜品还是套餐
            Long dishId = shoppingCartDTO.getDishId();
            if (dishId != null) {
                //添加到购物车的是菜品
                Result<Dish> result = itemClient.selectById(dishId);
                Dish dish=result.getData();
                shoppingCart.setName(dish.getName());
                shoppingCart.setImage(dish.getImage());
                shoppingCart.setAmount(dish.getPrice());
            } else {
                //添加到购物车的是套餐
                //TODO:根据套餐id查询套餐数据
//                Setmeal setmeal = setmealMapper.getById(shoppingCartDTO.getSetmealId());
//                shoppingCart.setName(setmeal.getName());
//                shoppingCart.setImage(setmeal.getImage());
//                shoppingCart.setAmount(setmeal.getPrice());
            }
            shoppingCart.setNumber(1);
            shoppingCart.setCreateTime(LocalDateTime.now());
            shoppingCartMapper.insert(shoppingCart);
        }
    }

    /**
     * 查看购物车
     * @return
     */
    public List<ShoppingCart> showShoppingCart() {
        return shoppingCartMapper.list(ShoppingCart.
                builder().
                userId(/*BaseContext.getCurrentId()*/4L).
                build());
    }
    /**
     * 清空购物车商品
     * @return
     */
    @DeleteMapping("/clean")
    public Result<String> clean(){
        shoppingCartService.cleanShoppingCart();
        return Result.success();
    }
    /**
     * 清空购物车商品
     */
    public void cleanShoppingCart() {
        shoppingCartMapper.deleteByUserId(/*BaseContext.getCurrentId()*/4L);
    }
}

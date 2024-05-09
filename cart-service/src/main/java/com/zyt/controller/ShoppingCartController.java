package com.zyt.controller;


import com.zyt.result.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.zyt.pojo.dto.ShoppingCartDTO;
import com.zyt.pojo.entity.ShoppingCart;
import com.zyt.service.ShoppingCartService;

import java.util.List;

/**
 * 购物车
 */
@RestController
@RequestMapping("/user/shoppingCart")
@Slf4j

public class ShoppingCartController {

    @Autowired
    private ShoppingCartService shoppingCartService;

    /**
     * 添加购物车
     * @param shoppingCartDTO
     * @return
     */
    @PostMapping("/add")

    public Result<String> add(@RequestBody ShoppingCartDTO shoppingCartDTO){
        log.info("添加购物车：{}", shoppingCartDTO);
        shoppingCartService.addShoppingCart(shoppingCartDTO);//后绪步骤实现
        return Result.success();
    }
    /**
     * 查看购物车
     * @return
     */
    @GetMapping("/list")

    public Result<List<ShoppingCart>> list(){
        return Result.success(shoppingCartService.showShoppingCart());
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
}


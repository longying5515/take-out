package com.sky.client;


import com.sky.pojo.entity.Dish;
import com.sky.result.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@FeignClient("item-service")
public interface ItemClient {
    @GetMapping("/dish/{id}")
    Result<Dish> selectById(@PathVariable("id") Long id);

}

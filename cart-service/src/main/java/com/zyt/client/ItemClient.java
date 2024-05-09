package com.zyt.client;


import com.zyt.pojo.entity.Dish;
import com.zyt.result.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@FeignClient("item-service")
public interface ItemClient {
    @GetMapping("/dish/{id}")
    Result<Dish> selectById(@PathVariable("id") Long id);

}

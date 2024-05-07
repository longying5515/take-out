package com.sky.client;


import org.springframework.cloud.openfeign.FeignClient;


@FeignClient("item-service")
public interface ItemClient {


}

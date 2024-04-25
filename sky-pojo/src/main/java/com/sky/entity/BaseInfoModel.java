package com.sky.entity;

import lombok.Data;

@Data
public class BaseInfoModel<T> {

    private String code;
    private String msg;
    private T data;

    public static <T> BaseResponseMessage success(T data) {
        BaseResponseMessage baseResponseMessage = new BaseResponseMessage();
        baseResponseMessage.code = "0";
        baseResponseMessage.msg = "成功";
        baseResponseMessage.data = data;
        return baseResponseMessage;
    }
}

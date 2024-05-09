package com.zyt.entity;

public class BaseResponseMessage<T> {
    public String code;
    public String msg;
    public T data;
    public static <T> BaseResponseMessage<T> success() {
        BaseResponseMessage<T> result = new BaseResponseMessage<T>();
        result.code = String.valueOf(1);
        return result;
    }

    public static <T>BaseResponseMessage<T> success(T object) {
        BaseResponseMessage<T> result = new BaseResponseMessage<T>();
        result.data = object;
        result.code = String.valueOf(1);
        return result;
    }

    public static <T> BaseResponseMessage<T> error(String data, String msg) {
        BaseResponseMessage result = new BaseResponseMessage();
        result.msg = msg;
        result.code = String.valueOf(0);
        return result;
    }
}

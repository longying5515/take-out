package com.zyt.handler;

import com.zyt.constant.MessageConstant;
import com.zyt.exception.BaseException;
import com.zyt.result.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLIntegrityConstraintViolationException;

/**
 * 全局异常处理器，处理项目中抛出的业务异常
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    /**
     * 捕获业务异常
     * @param ex
     * @return
     */
    @ExceptionHandler
    public Result exceptionHandler(BaseException ex){
        log.error("异常信息：{}", ex.getMessage());
        return Result.error(ex.getMessage());
    }
    @ExceptionHandler
    public Result exceptionHandler(SQLIntegrityConstraintViolationException ex){
        //Duplicate entry 'huangzihe' for key 'employee.idx_username'
        log.error("异常信息：{}",ex.getMessage());
        String message=ex.getMessage();
        if(message.contains("Duplicate entry")){
            String[] split=message.split(" ");
            String username=split[2];
            String ret=username+ MessageConstant.ALREADY_EXISIS;
            return Result.error(ret);
        }
        else{
            return Result.error(MessageConstant.UNKNOWN_ERROR);
        }
    }



}

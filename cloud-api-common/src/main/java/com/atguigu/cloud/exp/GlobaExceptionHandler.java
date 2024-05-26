package com.atguigu.cloud.exp;

import com.atguigu.cloud.resp.Result;
import com.atguigu.cloud.resp.ReturnCodeEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常拦截器
 */
@Slf4j
@RestControllerAdvice
public class GlobaExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Result<String> exception(Exception e){
        log.error("全局异常信息：{}",e.getMessage());
        return Result.fail(ReturnCodeEnum.RC500.getCode(),e.getMessage());
    }



}

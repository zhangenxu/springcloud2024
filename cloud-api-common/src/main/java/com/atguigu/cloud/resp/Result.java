package com.atguigu.cloud.resp;

import lombok.Data;
import lombok.experimental.Accessors;
@Data
@Accessors(chain = true)
public class Result<T>
{

    private String code;
    private String message;
    private T data;
    private long timestamp;

    public Result(){
        this.timestamp = System.currentTimeMillis();
    }

    public static <T> Result<T> success(T data){
        Result r = new Result<>();
        r.setCode(ReturnCodeEnum.RC200.getCode());
        r.setMessage(ReturnCodeEnum.RC200.getMessage());
        r.setData(data);
        return r;
    }

    public static <T> Result<T> fail(String code ,String message){
        Result r = new Result<>();
        r.setCode(code);
        r.setMessage(message);
        r.setData(null);
        return r;
    }


}

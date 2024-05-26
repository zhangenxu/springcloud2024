package com.atguigu.cloud.resp;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum ReturnCodeEnum {
    RC999("999","操作失败"),
    RC200("200","success"),
    RC201("201","开启服务降级保护，请稍后再试！"),
    RC202("202","热点参数限流请稍后再试！"),
    RC203("203","系统规则不满足要求，请稍后再试！"),
    RC204("204","授权规则不通过，请稍后再试！"),
    RC403("403","无访问权限，请联系管理员授予权限！"),
    RC404("404","404找不到页面异常！"),
    RC500("500","系统异常，请稍后重试！"),
    RC375("375","数学运算异常，请稍后重试！"),
    INVALID_TOKEN("2001","访问令牌不合法！"),
    ACCESS_DENIED("2003","没有权限访问该资源！"),
    CLIENT_AUTHENTICATION_FAILDE("1001","客户端认证失败！"),
    USERNAME_OR_PASSWORD_ERROR("1002","用户名或密码错误！"),
    BUSINESS_ERROR("1004","业务逻辑异常！"),
    UNSUPPORTED_GRANT_TYPE("1003","不支持的认证模式！");
    //举值，构造，遍历

    private final String code;
    private final String message;
    ReturnCodeEnum(String code,String message) {
        this.code = code;
        this.message = message;
    }
    //传统版
    public static ReturnCodeEnum getReturnCodeEnumV1(String code){
        for (ReturnCodeEnum element : ReturnCodeEnum.values()) {
            if(element.code.equalsIgnoreCase(code)){
                return element;
            }
        }
        return null;
    }
    //流式计算
    public static ReturnCodeEnum getReturnCodeEnumV2(String code){
        return Arrays.stream(ReturnCodeEnum.values()).filter(
                x -> x.getCode().equalsIgnoreCase(code)).findFirst().orElse(null);
    }

    public static void main(String[] args) {
//        System.out.println(getReturnCodeEnumV1("200"));
//        System.out.println(getReturnCodeEnumV1("200").getCode());
//        System.out.println(getReturnCodeEnumV1("200").getMessage());

        System.out.println(getReturnCodeEnumV2("200"));
        System.out.println(getReturnCodeEnumV2("200").getCode());
        System.out.println(getReturnCodeEnumV2("200").getMessage());
    }

}

package com.atguigu.cloud.entities;

import cn.hutool.core.date.DateTime;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Table;
import java.util.Date;

/**
 * 表名：t_user
*/
@Data
public class UserDto {
    /**
     * id
     */
    @Schema(title = "userId",description = "主键id",defaultValue = "1")
    private Long id;

    /**
     * 用户名
     */
    @Schema(title = "usrname",description = "用户名",defaultValue = "zhangenxu")
    private String usrname;

    /**
     * 密码
     */
    @Schema(title = "password",description = "密码",defaultValue = "123456")
    private String password;

    /**
     * 性别0女|1男
     */
    @Schema(title = "sex",description = "性别",defaultValue = "1")
    private Integer sex;


    /**
     * 更新时间
     */
    @Schema(title = "updateTime",description = "更新时间",defaultValue = "")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private DateTime updateTime;

    /**
     * 创建时间
     */
    @Schema(title = "createTime",description = "创建时间",defaultValue = "")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private DateTime createTime;


}
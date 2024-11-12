package com.atguigu.cloud.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * 表名：t_user
*/
@Data
@Table(name = "t_user")
public class User {
    /**
     * id
     */
    @GeneratedValue(generator = "JDBC")
    @Id
    private Long id;

    /**
     * 用户名
     */
    private String usrname;

    /**
     * 密码
     */
    private String password;

    /**
     * 性别0女|1男
     */
    private Integer sex;

    /**
     * 删除标志0不删除1删除
     */
    private Integer deleted;

    /**
     * 更新时间
     */
    @Column(name = "update_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date updateTime;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createTime;


}
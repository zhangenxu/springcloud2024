package com.atguigu.cloud.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Schema(title = "支付流水实体")
public class PayDto {

    @Schema(title = "id")
    private Integer id;

    /**
     * 支付流水号
     */
    @Schema(title = "支付流水号")
    private String payNo;

    /**
     * 订单流水号
     */
    @Schema(title = "订单流水号")
    private String orderNo;

    /**
     * 用户账号id
     */
    @Schema(title = "用户账号id")
    private Long userId;

    /**
     * 交易金额
     */
    @Schema(title = "交易金额")
    private BigDecimal amount;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createTime;

    /**
     * 更新时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date updateTime;

}

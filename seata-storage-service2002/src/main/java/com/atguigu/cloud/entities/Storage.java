package com.atguigu.cloud.entities;

import lombok.ToString;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 表名：t_storage
*/
@Table(name = "t_storage")
@ToString
public class Storage {
    /**
     * id
     */
    @Id
    @GeneratedValue(generator = "JDBC")
    private Long id;

    /**
     * 产品id
     */
    @Column(name = "product_id")
    private Long productId;

    /**
     * 总库存
     */
    private Integer totle;

    /**
     * 已用库存
     */
    private Integer used;

    /**
     * 剩余库存
     */
    private Integer residue;

    /**
     * 获取id
     *
     * @return id - id
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置id
     *
     * @param id id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取产品id
     *
     * @return productId - 产品id
     */
    public Long getProductId() {
        return productId;
    }

    /**
     * 设置产品id
     *
     * @param productId 产品id
     */
    public void setProductId(Long productId) {
        this.productId = productId;
    }

    /**
     * 获取总库存
     *
     * @return totle - 总库存
     */
    public Integer getTotle() {
        return totle;
    }

    /**
     * 设置总库存
     *
     * @param totle 总库存
     */
    public void setTotle(Integer totle) {
        this.totle = totle;
    }

    /**
     * 获取已用库存
     *
     * @return used - 已用库存
     */
    public Integer getUsed() {
        return used;
    }

    /**
     * 设置已用库存
     *
     * @param used 已用库存
     */
    public void setUsed(Integer used) {
        this.used = used;
    }

    /**
     * 获取剩余库存
     *
     * @return residue - 剩余库存
     */
    public Integer getResidue() {
        return residue;
    }

    /**
     * 设置剩余库存
     *
     * @param residue 剩余库存
     */
    public void setResidue(Integer residue) {
        this.residue = residue;
    }
}
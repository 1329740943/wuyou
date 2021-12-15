package com.demo.wuyou.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 用户信息
 */
@Data
@TableName("t_user_information")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel("用户信息")
public class TUserInformation implements Serializable {
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;
    /**
     * 状态
     */
    @TableField("status")
    private String status;
    /**
     * 订单号
     */
    @TableField("t_order")
    private String t_order;
    /**
     * 用户id
     */
    @TableField("user_id")
    private Integer user_id;
    /**
     * 产品
     */
    @TableField("product")
    private String product;
    /**
     * 来源
     */
    @TableField("source")
    private String source;
    /**
     * 折扣
     */
    @TableField("discount")
    private BigDecimal discount;
    /**
     * 下单时间
     */
    @TableField("order_time")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Date order_time;
    /**
     * 金额
     */
    @TableField("amount")
    private BigDecimal amount;
    /**
     * 返现金额
     */
    @TableField("cash_amount")
    private BigDecimal cash_amount;
    /**
     * 实际折扣
     */
    @TableField("actual_discount")
    private BigDecimal actual_discount;
    /**
     * 收益
     */
    @TableField("profit")
    private BigDecimal profit;
}

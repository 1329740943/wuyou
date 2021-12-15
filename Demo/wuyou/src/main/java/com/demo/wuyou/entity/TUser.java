package com.demo.wuyou.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
* <p>
    * 
    * </p>
*
* @author CJF
* @since 2021-03-23
*/
    @Data
    @TableName("t_user")
    @EqualsAndHashCode(callSuper = false)
    @Accessors(chain = true)
    @ApiModel("用户POJO")
    public class TUser implements Serializable {

    private static final long serialVersionUID = 1L;

            @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

            /**
            * 昵称
            */
        @TableField("name")
    private String name;

            /**
            * 性别
            */
        @TableField("sex")
    private String sex;

            /**
            * 年龄
            */
        @TableField("age")
    private Integer age;

            /**
            * 电话
            */
        @TableField("iphone")
    private String iphone;
    /**
     * 密码
     */
    @TableField("password")
    private String password;

    /**
     * 账号
     */
    @TableField("username")
    private String username;



}

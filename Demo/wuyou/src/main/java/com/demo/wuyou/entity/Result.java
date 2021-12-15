package com.demo.wuyou.entity;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.ArrayList;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel("返回信息结果类")
public class Result<T> implements Serializable {
    private String msg;
    private String code;
    private String user_id;
    private ArrayList<T> list;
    private TUserInformation userInformation;
    private TUser tUser;
}

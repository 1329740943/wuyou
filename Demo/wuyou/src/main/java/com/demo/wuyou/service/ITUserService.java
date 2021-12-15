package com.demo.wuyou.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.demo.wuyou.entity.TUser;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author CJF
 * @since 2021-03-23
 */
public interface ITUserService extends IService<TUser> {
    //登录
    public TUser Login(String username,String password);

    //根据ID查询用户
    public TUser FindById(Integer id);

    //根据账号查询用户
    public TUser FindByName(String username);

    //根据账户查询密码是否同上一次一样
    public TUser FindPwd(String username);

    //根据手机查询用户
    public TUser FindByIphone(String iphone);

    //添加用户
    public Integer InsertUser(TUser tUser);

    //修改用户信息
    public Integer UpdateInformation(TUser tUser);

}

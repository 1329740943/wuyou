package com.demo.wuyou.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.demo.wuyou.entity.TUser;
import com.demo.wuyou.mapper.TUserMapper;
import com.demo.wuyou.service.ITUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author CJF
 * @since 2021-03-23
 */
@Service
@Transactional
public class TUserServiceImpl extends ServiceImpl<TUserMapper, TUser> implements ITUserService {
    @Autowired
    private TUserMapper userMapper;
    //登录
    @Override
    public TUser Login(String username, String password) {
        QueryWrapper<TUser> wrapper=new QueryWrapper<>();
        wrapper.eq("username",username).eq("password",password);
        TUser tUser = userMapper.selectOne(wrapper);
        return tUser;
    }
    //根据ID查询用户
    @Override
    public TUser FindById(Integer id) {
        TUser tUser = userMapper.selectById(id);
        return tUser;
    }
    //根据账号查询用户
    @Override
    public TUser FindByName(String username) {
        QueryWrapper<TUser> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("username",username);
        TUser tUser = userMapper.selectOne(queryWrapper);
        return tUser;
    }
    //根据账户查询密码是否同上一次一样
    @Override
    public TUser FindPwd(String username) {
        QueryWrapper<TUser> wrapper = new QueryWrapper<>();
        wrapper.eq("username",username);
        TUser tUser = userMapper.selectOne(wrapper);
        return tUser;
    }

    //根据手机查询用户
    @Override
    public TUser FindByIphone(String iphone) {
        QueryWrapper<TUser> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("iphone",iphone);
        TUser tUser = userMapper.selectOne(queryWrapper);
        return tUser;
    }
    //添加用户
    @Override
    public Integer InsertUser(TUser tUser) {
        int cont = userMapper.insert(tUser);
        return cont;
    }
    //修改用户信息
    @Override
    public Integer UpdateInformation(TUser tUser) {
        int cont = userMapper.updateById(tUser);
        return cont;
    }




}

package com.demo.wuyou.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.demo.wuyou.entity.TUserInformation;

import java.util.List;

public interface TUserInformationService extends IService<TUserInformation> {
    //查询用户商品信息列表
    public List<TUserInformation> FindUserList(String user_id);
    //删除用户商品信息列表
    public Integer DetUserList(String id);
    //新增用户商品信息
    public Integer SaveUser(TUserInformation userInformation);
    //根据ID查询用户商品信息
    public TUserInformation FindById(String id);
    //根据ID修改用户商品信息
    public Integer UpdateUser(TUserInformation userInformation);
    //快速查询
    public List<TUserInformation> FindQuickSearch(String search,String user_id);
    //分页
    public IPage<TUserInformation> pageQuery(Page<TUserInformation> pageParam, String id);
}

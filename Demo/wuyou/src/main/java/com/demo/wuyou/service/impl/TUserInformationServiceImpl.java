package com.demo.wuyou.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.demo.wuyou.entity.TUserInformation;
import com.demo.wuyou.mapper.TUserInformationMapper;
import com.demo.wuyou.service.TUserInformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class TUserInformationServiceImpl extends ServiceImpl<TUserInformationMapper, TUserInformation> implements TUserInformationService {
    @Autowired
    private TUserInformationMapper userInformationMapper;


    //查询用户商品信息列表
    @Override
    public List<TUserInformation> FindUserList(String user_id) {
        QueryWrapper<TUserInformation> wrapper=new QueryWrapper<>();
        wrapper.eq("user_id",user_id);
        List list = userInformationMapper.selectList(wrapper);
        return list;
    }

    //删除用户商品信息列表
    @Override
    public Integer DetUserList(String id) {
        int cont = userInformationMapper.deleteById(id);
        return cont;
    }

    //新增用户商品信息
    @Override
    public Integer SaveUser(TUserInformation userInformation) {
        int cont = userInformationMapper.insert(userInformation);
        return cont;
    }

    //根据ID查询用户商品信息
    @Override
    public TUserInformation FindById(String id) {
        TUserInformation userInformation = userInformationMapper.selectById(id);
        return userInformation;
    }

    //根据ID修改用户商品信息
    @Override
    public Integer UpdateUser(TUserInformation userInformation) {
        int cont = userInformationMapper.updateById(userInformation);
        return cont;
    }

    //快速查询
    @Override
    public List<TUserInformation> FindQuickSearch(String search,String user_id) {
        QueryWrapper<TUserInformation> wrapper=new QueryWrapper();
        wrapper.eq("user_id",user_id);
        wrapper.and(
                queryWrapper->
                        queryWrapper.like("status",search).or()
                                .like("t_order",search).or()
                                .like("product",search).or()
                                .like("source",search)
        );
        List<TUserInformation> list = userInformationMapper.FindSearch(wrapper);
        return list;
    }
    //分页
    @Override
    public IPage<TUserInformation> pageQuery(Page<TUserInformation> pageParam, String id) {
        QueryWrapper<TUserInformation> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id",id);
        IPage<TUserInformation> iPage = userInformationMapper.selectPage(pageParam, wrapper);
        return  iPage;
    }
}

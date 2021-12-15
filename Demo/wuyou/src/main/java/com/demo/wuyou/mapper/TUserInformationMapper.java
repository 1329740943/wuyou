package com.demo.wuyou.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.demo.wuyou.entity.TUserInformation;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TUserInformationMapper extends BaseMapper<TUserInformation> {

    @Select("SELECT * FROM t_user_information ${ew.customSqlSegment}")
    List<TUserInformation> FindSearch(@Param(Constants.WRAPPER) Wrapper<TUserInformation> userWrapper);
}

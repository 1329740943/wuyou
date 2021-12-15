package com.demo.wuyou.controller;

import cn.dev33.satoken.stp.StpUtil;
import com.demo.wuyou.entity.Result;
import com.demo.wuyou.entity.TUserInformation;
import com.demo.wuyou.service.impl.TUserInformationServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(tags = "修改列表信息页面功能")
public class UpdateUserListController {
    @Autowired
    private TUserInformationServiceImpl userInformationService;

    @PostMapping("/validateUserList")
    @ApiOperation("查询修改页面数据")
    public Result<TUserInformation> ValidateUserList(String listId){
        Result<TUserInformation> result=new Result<>();
        //查看是否登录
        boolean login = StpUtil.isLogin();
        if(login){
            if (listId=="0"){
                result.setCode("error");
                result.setMsg("商品ID传递有误");
                return result;
            }
            result.setCode("true");
            TUserInformation userInformation = userInformationService.FindById(listId);
            result.setUserInformation(userInformation);
            return result;
        }else {
            result.setCode("false");
            result.setMsg("请先登录");
            return result;
        }

    }
    @PostMapping("/update")
    @ApiOperation("修改页面数据提交进行修改")
    public Result Update(TUserInformation userInformation) throws Exception {
        Result result=new Result();
        try {
            Integer cont = userInformationService.UpdateUser(userInformation);
            if (cont==1){
                result.setCode("true");
                result.setMsg("修改成功");
                return result;
            }
            result.setCode("false");
            result.setMsg("发生了一点小意外");
            return result;
        }catch (Exception e){
            throw new Exception("系统正在维护中");
        }

    }
}

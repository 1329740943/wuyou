package com.demo.wuyou.controller;

import cn.dev33.satoken.stp.StpUtil;
import com.demo.wuyou.entity.Result;
import com.demo.wuyou.entity.TUserInformation;
import com.demo.wuyou.service.impl.TUserInformationServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@Api(tags = "新增列表信息页面功能")
public class SaveUserController {
    @Autowired
    private TUserInformationServiceImpl userInformationService;

    @RequestMapping("/validateLogon")
    @ApiOperation("登录效验")
    public Result ValidateLogon(){
        Result result=new Result();
        //查看是否登录
        boolean login = StpUtil.isLogin();
        if (login){
            result.setCode("true");
            result.setMsg("已登录");
            result.setUser_id(StpUtil.getLoginIdAsString());
            return result;
        }else {
            result.setCode("false");
            result.setMsg("请先登录");
            return result;
        }
    }
    @RequestMapping("/save")
    @ApiOperation("新增用户列表信息")
    public Result<TUserInformation> SaveUser(TUserInformation userInformation){
        Result<TUserInformation> result=new Result<>();
        Integer cont = userInformationService.SaveUser(userInformation);
        if (cont!=1){
            result.setCode("false");
            result.setMsg("系统正在维护,请稍后");
            return result;
        }
        result.setCode("true");
        result.setMsg("添加成功");
        return result;
    }

    @PostMapping("/amountAssignment")
    @ApiOperation("金额字段赋值")
    public TUserInformation AmountAssignment (TUserInformation userInformation){
        BigDecimal cont = BigDecimal.valueOf(0.01);
        BigDecimal amount = userInformation.getAmount();
        BigDecimal discount = userInformation.getDiscount();
        BigDecimal actual_discount = userInformation.getActual_discount();
        userInformation.setCash_amount(amount.subtract(amount.multiply(discount.multiply(cont))));
        userInformation.setProfit(amount.subtract(amount.multiply(actual_discount.multiply(cont))).subtract(amount.subtract(amount.multiply(discount.multiply(cont)))));
        return userInformation;
    }

}

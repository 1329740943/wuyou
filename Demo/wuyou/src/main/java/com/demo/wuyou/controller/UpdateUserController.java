package com.demo.wuyou.controller;

import cn.dev33.satoken.stp.StpUtil;
import com.demo.wuyou.entity.Result;
import com.demo.wuyou.entity.TUser;
import com.demo.wuyou.service.impl.TUserServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@Api(tags = "修改用户个人信息")
public class UpdateUserController {
    @Autowired
    private TUserServiceImpl tUserService;

    @PostMapping("/UserHomePage")
    @ApiOperation("个人中心首页功能")
    public Result<TUser> UserHomePage(String id){
        Result<TUser> result=new Result<>();
        boolean login = StpUtil.isLogin();
        if(login){
            if (id=="0"){
                result.setCode("error");
                result.setMsg("用户ID传递有误");
                return result;
            }
            TUser tUser = tUserService.FindById(Integer.parseInt(id));
            result.setTUser(tUser);
            result.setCode("true");
            return result;
        }else {
            result.setCode("false");
            result.setMsg("请先登录");
            return result;
        }
    }
    //修改密码
    @PostMapping("UpdatePwd")
    @ApiOperation("修改密码")
    public Result UpdatePwd(String username,String password){
        Result result=new Result();
        TUser user = tUserService.FindByName(username);
        user.setPassword(password);
        System.out.println(user);
        Integer cont = tUserService.UpdateInformation(user);
        if (cont>0){
            result.setCode("true");
            result.setMsg("修改成功");
            return result;
        }
        result.setCode("false");
        result.setMsg("修改失败,请刷新页面重试");
        return result;
    }

    @PostMapping("/UpdateInformation")
    @ApiOperation("修改个人信息")
    public Result UpdateInformation(TUser tUser){
        Result result=new Result();
        Integer cont = tUserService.UpdateInformation(tUser);
        if (cont>0){
            result.setCode("true");
            result.setMsg("修改成功");
            return result;
        }
        result.setCode("false");
        result.setMsg("修改失败,请刷新页面重试");
        return result;
    }

    @RequestMapping("/toUpdatePwd")
    @ApiOperation("跳转密码修改页面")
    public ModelAndView ToUpdatePwd(){
        ModelAndView mv=new ModelAndView();
        try {
            String id = StpUtil.getLoginIdAsString();
            mv.addObject("id",id);
            mv.setViewName("updatepwd");
            return mv;
        } catch (Exception e) {
            mv.setViewName("login");
            return mv;
        }
    }


}

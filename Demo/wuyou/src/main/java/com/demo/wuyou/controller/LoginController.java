package com.demo.wuyou.controller;

import cn.dev33.satoken.stp.StpUtil;
import com.demo.wuyou.entity.Result;
import com.demo.wuyou.entity.TUser;
import com.demo.wuyou.service.impl.TUserServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@RestController
@Api(tags = "无忧首页功能")
public class LoginController {
    private final Logger log= LoggerFactory.getLogger(LoginController.class);
    @Autowired
    private TUserServiceImpl tUserService;

    //登录
    @PostMapping(value = "/toLogin")
    @ApiOperation("登录")
    public Result<TUser> Login(String username,String password,HttpServletRequest request) throws Exception {
        try {
            TUser tUser = tUserService.Login(username, password);
            Result<TUser> result=new Result();
            if (tUser==null){
                result.setCode("false");
                result.setMsg("账号密码有误");
                return result;
            };
            //添加登录ID
            StpUtil.setLoginId(tUser.getId());
            result.setCode("true");
            request.getSession().setAttribute("userId",tUser.getId());
            return result;
        }catch (Exception e){
            throw new Exception("联系管理员");
        }
    }
    //查询账号是否重复
    @PostMapping(value = "/FindByName")
    @ApiOperation("查询账号是否重复")
    public Result<TUser> FindByName(String username) throws Exception {
        Result<TUser> result=new Result();
       try {
           if (username!=""){
               TUser tUser = tUserService.FindByName(username);
               if (tUser!= null){
                   result.setCode("false");
                   result.setMsg("账号已存在");
                   return result;
               }
               result.setCode("true");
               result.setMsg("账号可以使用");
               return result;
           }
           result.setCode("false");
           result.setMsg("账号不能为空");
           return result;
       }catch (Exception e){
           throw new Exception("联系管理员");
       }
    }
    //根据账户查询密码是否同上一次一样
    @PostMapping("/FindPwd")
    @ApiOperation("根据账户查询密码是否同上一次一样")
    public Result<TUser> FindPwd(String username,String password)throws Exception{
        Result<TUser> result = new Result<TUser>();
        try {
            if (password!=""){
                TUser tUser = tUserService.FindPwd(username);
                String pwd = tUser.getPassword();
                if (pwd.equals(password)){
                    result.setCode("false");
                    result.setMsg("密码不能旧密码相同");
                    return result;
                }
                result.setCode("true");
                result.setMsg("密码可以使用");
                return result;
            }
            result.setCode("false");
            result.setMsg("密码不能为空");
            return result;
        }catch (Exception e){
            throw new Exception("联系管理员");
        }

    }

    //查询手机号是否重复
    @PostMapping("/FindByIphone")
    @ApiOperation("查询手机号是否重复")
    public Result<TUser> FindByIphone(String iphone) throws Exception {
        Result<TUser> result=new Result<>();
        try {
            TUser tUser = tUserService.FindByIphone(iphone);
            if (iphone!="" && iphone.length()==11){
                if (tUser!=null){
                    result.setCode("false");
                    result.setMsg("手机已被注册");
                    return result;
                }
                result.setCode("true");
                result.setMsg("手机号可以使用");
                return result;
            }
            result.setCode("false");
            result.setMsg("请输入正确的手机号");
            return result;
        }catch (Exception e){
            throw new Exception("联系管理员");
        }
    }
    //注册
    @PostMapping("/InsertUser")
    @ApiOperation("注册")
    public Result<TUser> InsertUser(TUser tUser) throws Exception {
        Result<TUser> result=new Result<>();
        try {
            Integer cont = tUserService.InsertUser(tUser);
            if (cont>0){
                result.setCode("true");
                result.setMsg("注册成功");
                return result;
            }
            result.setCode("false");
            result.setMsg("一个未知的原因导致注册失败");
            return result;
        }catch (Exception e){
            throw new Exception("联系管理员");
        }
    }
    //跳转登录页面
    @RequestMapping("/login")
    @ApiOperation("跳转登录页面")
    public ModelAndView Login(){
        ModelAndView login = new ModelAndView("login");
        return login;
    }

    //跳转注册页面
    @RequestMapping(value = "/register")
    @ApiOperation("跳转注册页面")
    public ModelAndView ToRegister(){
        ModelAndView register = new ModelAndView("register");
        return register;
    }
    //跳转注册成功页面
    @RequestMapping("/registersuccess")
    @ApiOperation("跳转注册成功页面")
    public ModelAndView ToRegistersuccess(){
        ModelAndView register = new ModelAndView("registersuccess");
        return register;
    }
    //跳转成功页面
    @RequestMapping("/success")
    @ApiOperation("跳转成功页面")
    public ModelAndView ToSuccess(){
        ModelAndView success = new ModelAndView("success");
        try {
            String id = StpUtil.getLoginIdAsString();
            success.addObject("id",id);
            return success;
        } catch (Exception e) {
            success.setViewName("login");
            return success;
        }

    }
}

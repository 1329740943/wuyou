package com.demo.wuyou.controller;

import cn.hutool.http.HttpUtil;
import com.demo.wuyou.entity.TUser;
import com.demo.wuyou.util.RedisUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

@RestController
@Api(tags = "redis测试")
public class RedisDemo {
    public final Logger log=LoggerFactory.getLogger(RedisDemo.class);
    private static int ExpireTime = 60;   // redis中存储的过期时间60s
    @Resource
    private RedisUtil redisUtil;

    @RequestMapping("demo")
    @ApiOperation("访问外部接口测试")
    public String Demo (String url){
        log.info("外部接口路径:"+url);
        String s = HttpUtil.get(url);
        return s;
    }

    @RequestMapping("demo2")
    @ApiOperation("访问外部接口测试2")
    public ModelAndView DemoTwo (){
        return new ModelAndView("redirect:http://www.baidu.com");
    }

    @RequestMapping("demo3")
    @ApiOperation("访问外部接口测试3")
    public void Demo3 (HttpServletResponse response) throws Exception {
        response.sendRedirect("https://www.baidu.com");
    }

    @RequestMapping("set")
    @ApiOperation("Redis设值")
    public boolean redisset(String key, String value){
        TUser user =new TUser();
        user.setId(0);
        user.setAge(10);
        user.setIphone("122212121");
        user.setName("REDIS");
        redisUtil.set("user",user,-1); //存放对象
        return redisUtil.set(key,value);
    }

    @RequestMapping("get")
    @ApiOperation("Redis取值")
    public Object Redisget(String key){
        System.out.println(redisUtil.get(key));
        TUser user = (TUser) redisUtil.get(key);
        Integer id = user.getId();
        String name = user.getName();
        String iphone = user.getIphone();
        return redisUtil.get(key);
    }

    @RequestMapping("expire")
    public boolean Expire(String key){
        return redisUtil.expire(key,ExpireTime);
    }

}

package com.demo.wuyou.controller;

import cn.dev33.satoken.session.SaSession;
import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.demo.wuyou.entity.Result;
import com.demo.wuyou.entity.TUserInformation;
import com.demo.wuyou.service.impl.TUserInformationServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@Api(tags = "用户首页功能")
public class UserInformationController {
    @Autowired
    private TUserInformationServiceImpl userInformationService;
    //分页
    @RequestMapping("/test")
    public IPage<TUserInformation> Page(){
        Page<TUserInformation> page = new Page<>(1,2);
        IPage<TUserInformation> tUserInformationIPage = userInformationService.pageQuery(page, "1");
        return tUserInformationIPage;
    }

    @RequestMapping("/userList")
    @ApiOperation("查询用户商品信息列表")
    public List<TUserInformation> FindUserList(String user_id){
        List<TUserInformation> list = userInformationService.FindUserList(user_id);
        return list;
    }

    @RequestMapping("/quickSearch")
    @ApiOperation("快速搜索")
    public List<TUserInformation> QuickSearch(String search,String user_id){
        List<TUserInformation> list = userInformationService.FindQuickSearch(search, user_id);
        return list;
    }

    @RequestMapping("/outLogin")
    @ApiOperation("退出登录")
    public ModelAndView OutLogin(HttpServletRequest request, HttpServletResponse response){
        ModelAndView mv=new ModelAndView();
        request.getSession().invalidate();
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie:cookies){
            cookie.setMaxAge(0);
            response.addCookie(cookie);
        }
        mv.setViewName("login");
        StpUtil.logout();
        return mv;
    }

    @PostMapping("/detUserList")
    @ApiOperation("删除用户商品信息列表")
    public Result<TUserInformation> DetUserList(String id){
        Result result=new Result();
        Integer cont = userInformationService.DetUserList(id);
        if (cont==1){
            result.setCode("true");
            result.setMsg("删除成功");
            return result;
        }
        result.setCode("false");
        result.setMsg("删除失败,正在维护 ");
        return result;
    }

    @RequestMapping("/toChannel")
    @ApiOperation("跳转渠道管理")
    public ModelAndView ToChannel(){
        ModelAndView mv=new ModelAndView();
        mv.setViewName("channel");
        return mv;
    }

    @RequestMapping("/toUser")
    @ApiOperation("跳转个人中心")
    public ModelAndView ToUser(){
        ModelAndView mv=new ModelAndView();
        try {

            String id = StpUtil.getLoginIdAsString();
            mv.addObject("id",id);
            mv.setViewName("user");
            return mv;
        } catch (Exception e) {
            mv.setViewName("login");
            return mv;
        }
    }

    @RequestMapping("/toSaveUser")
    @ApiOperation("跳转信息添加页面")
    public ModelAndView ToSaveUser(){
        ModelAndView mv=new ModelAndView();
        mv.setViewName("saveuser");
        return mv;
    }
    @RequestMapping("/isIdNull")
    @ApiOperation("当前信息列表Id是否为空")
    public Result IsIdNull(String id){
        Result result=new Result();
        SaSession session = StpUtil.getSession();
        if (StringUtils.isEmpty(id)){
            result.setCode("false");
            result.setMsg("ID为空,系统正在维护");
            return result;
        }
        result.setCode("true");
        session.setAttribute("listId",id);
        return result;
    }

    @RequestMapping("/toUpdateList")
    @ApiOperation("跳转列表商品信息修改页面")
    public ModelAndView ToUpdateList(){
        ModelAndView mv=new ModelAndView();
        SaSession session = StpUtil.getSession();
        String listId = (String)session.getAttribute("listId");
        mv.addObject("listId",listId);
        mv.setViewName("updateList");
        return mv;
    }
}

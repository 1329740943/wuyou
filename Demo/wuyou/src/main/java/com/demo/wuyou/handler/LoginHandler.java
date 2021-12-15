package com.demo.wuyou.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Component
public class LoginHandler implements HandlerInterceptor {
    @Autowired
    private StringRedisTemplate redisTemplate;
    //在请求发生前执行
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
          // 拿到方法上是否标记了注解
//        HandlerMethod method = (HandlerMethod) handler;
//        LoginRequired loginRequired = method.getMethodAnnotation(LoginRequired.class);
        //拦截处理代码
//        if(loginRequired!=null){
//            System.out.println("--------------注解拦截------------");
//            return true;
//        }

        System.out.println("----------------------------开始拦截------------------------");
        HttpSession session = request.getSession(true);
        Object userId = session.getAttribute("userId");
//        if (userId==null){
//            System.out.println("----------------------------未登录------------------------");
//            response.sendRedirect("/login");
//            return false;
//        }
        return true;
    }
    //整个请求完成之后发生
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
    //请求完成之后触发
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }
}

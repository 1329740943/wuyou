package com.demo.wuyou.test;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Log4jTest {
    public static final Logger log= LoggerFactory.getLogger(Log4jTest.class);
    public static void main(String[] args) {
        /**
         * trace (追踪)
         * debug (调试)
         * info (信息)
         * warn (警告)
         * error (错误)
         * fatal (严重错误)
         */
        log.trace("追踪");
        log.debug("调试");
        log.warn("警告");
        log.info("信息");
        log.error("错误");
        System.out.println("log4j测试!");
    }
}

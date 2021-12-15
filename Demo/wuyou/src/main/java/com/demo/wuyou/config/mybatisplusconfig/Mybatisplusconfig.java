package com.demo.wuyou.config.mybatisplusconfig;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Mybatis-Plus 分页配置
 */
@Configuration
@EnableTransactionManagement(proxyTargetClass = true)
public class Mybatisplusconfig {
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }
}

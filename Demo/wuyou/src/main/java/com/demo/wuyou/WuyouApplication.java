package com.demo.wuyou;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@MapperScan("com.demo.wuyou.mapper")
@EnableSwagger2
//@EnableCaching   redis 是否使用(使用缓存)
public class WuyouApplication {

	public static void main(String[] args) {
		SpringApplication.run(WuyouApplication.class, args);
	}

}

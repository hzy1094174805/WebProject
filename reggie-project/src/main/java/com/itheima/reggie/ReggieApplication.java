package com.itheima.reggie;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
@MapperScan(basePackages = "com.itheima.reggie.mapper")
public class ReggieApplication {
    public static void main(String[] args) {
            SpringApplication.run(ReggieApplication.class, args);

    }
}


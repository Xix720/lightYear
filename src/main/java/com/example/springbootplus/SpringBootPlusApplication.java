package com.example.springbootplus;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.springbootplus.mapper")
public class SpringBootPlusApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootPlusApplication.class, args);
    }

}

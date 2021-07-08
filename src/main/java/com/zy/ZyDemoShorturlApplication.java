package com.zy;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@MapperScan("com.zy.mapper")
@SpringBootApplication
public class ZyDemoShorturlApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZyDemoShorturlApplication.class, args);
    }

}

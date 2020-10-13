package com.bjfu.it.mipep;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(value="com.bjfu.it.mipep.dao")
public class MipepApplication {

    public static void main(String[] args) {
        SpringApplication.run(MipepApplication.class, args);
    }

}

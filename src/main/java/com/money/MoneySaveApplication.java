package com.money;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.money.mapper")
public class MoneySaveApplication {

    public static void main(String[] args) {
        SpringApplication.run(MoneySaveApplication.class, args);
    }

}

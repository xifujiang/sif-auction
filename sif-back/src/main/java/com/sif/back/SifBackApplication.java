package com.sif.back;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.sif.back.mapper")
@SpringBootApplication
public class SifBackApplication {

    public static void main(String[] args) {
        SpringApplication.run(SifBackApplication.class, args);
    }

}

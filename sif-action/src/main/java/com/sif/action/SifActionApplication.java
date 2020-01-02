package com.sif.action;

import com.sif.common.util.IdWorker;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@MapperScan("com.sif.action.mapper")
@SpringBootApplication
public class SifActionApplication {

    public static void main(String[] args) {
        SpringApplication.run(SifActionApplication.class, args);
    }

    @Bean
    public IdWorker getIdWorker(){
        return new IdWorker();
    }
}

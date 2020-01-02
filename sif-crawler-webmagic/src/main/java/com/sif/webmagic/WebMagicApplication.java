package com.sif.webmagic;

import com.sif.common.util.IdWorker;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @program: sif-auction
 * @description:
 * @author: xifujiang
 * @create: 2019-12-03 10:44
 **/
@SpringBootApplication
@EnableScheduling // 开启定时任务
public class WebMagicApplication {
    public static void main(String[] args) {
        SpringApplication.run(WebMagicApplication.class, args);
    }
    @Bean
    public IdWorker getIdWorker(){
        return new IdWorker();
    }
}

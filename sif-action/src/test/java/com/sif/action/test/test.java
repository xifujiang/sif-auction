package com.sif.action.test;

import com.sif.action.service.CommodityTypeTbService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @program: sif-auction
 * @description:
 * @author: xifujiang
 * @create: 2019-10-26 17:09
 **/
@SpringBootTest
@RunWith(SpringRunner.class)
public class test {

    @Autowired
    RedisTemplate redisTemplate;
    @Autowired
    CommodityTypeTbService commodityTypeTbService;

    @Test
    public void set(){
        redisTemplate.opsForValue().set("xifu","傻子");

    }

//    @Test
//    public void get(){
//        String jsonString  = (String)redisTemplate.opsForValue().get("11");
//
//        System.err.println(jsonString);
//
//        List<CommodityTypeResult> list = JSON.parseArray(jsonString, CommodityTypeResult.class);
//
//        list.forEach(item->{
//            System.err.println(item.getTitle());
//        });
//
//        list.forEach(item->{
//            item.getTags().forEach(temp->{
//                System.err.println(temp.getName());
//            });
//        });
//    }

    @Test
    public void findAll(){
//        List<CommodityForm> all = commodityTypeTbService.findAll(Integer.valueOf(1));
//        all.forEach(item->{
//            System.out.println(item.getName_1());
//        });
    }
}

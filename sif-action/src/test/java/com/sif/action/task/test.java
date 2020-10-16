package com.sif.action.task;

import com.sif.action.SifActionApplication;
import com.sif.action.entity.DepositPayTb;
import com.sif.action.pojo.CommodityPojoTb;
import com.sif.action.service.CommodityTbService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.Duration;
import java.util.List;

/**
 * @program: sif-auction
 * @description:
 * @author: shenyini
 * @create: 2020-04-25 18:24
 **/
@SpringBootTest(classes = SifActionApplication.class)
@RunWith(SpringRunner.class)
public class test {

    @Autowired
    private CommodityTbService commodityTbService;
    @Test
    @Scheduled(cron = "*/1 * * * * ?")
    public void executeInternal() {
        //获取所有仍在竞拍的商品
        List<CommodityPojoTb> commodityTbs = commodityTbService.getIsBiddingCommodity();
        for (CommodityPojoTb commodityTb : commodityTbs) {
            if (commodityTb.getCid().equals("1202520430678300000")) {
                //当前时间大于截止时间，说明商品已经
                long time = System.currentTimeMillis() - commodityTb.getEndTime().getTime();
                System.err.println(time);
            }
        }
    }
}
//-12506

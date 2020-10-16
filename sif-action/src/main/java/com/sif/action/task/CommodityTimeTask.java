package com.sif.action.task;

import com.sif.action.entity.CommodityTb;
import com.sif.action.service.CommodityTbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @program: sif-auction
 * @description:
 * @author: shenyini
 * @create: 2020-03-24 15:58
 **/
@Component
public class CommodityTimeTask{

    @Autowired
    CommodityTbService commodityTbService;

    @Scheduled(fixedDelayString = "20000")
    public void executeInternal() {
        //如果状态是0展示阶段,到达开拍时间，变成1；
        List<CommodityTb> commodityList = commodityTbService.selectStatus(0);
        if(commodityList ==null || commodityList.size()==0) {
            return;
        }
        for(CommodityTb commodityTb: commodityList) {
            long time = System.currentTimeMillis()- commodityTb.getBeginTime().getTime();
            if(time > 0) {
                commodityTbService.updateStatus(commodityTb.getCid(), 1);
            }
        }
    }
}
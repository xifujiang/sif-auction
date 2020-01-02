package com.sif.action.quartz.job;

import com.sif.action.entity.DepositPayTb;
import com.sif.action.pojo.BiddingTb;
import com.sif.action.pojo.CommodityTb;
import com.sif.action.pojo.DepositUserInfo;
import com.sif.action.service.*;
import lombok.extern.slf4j.Slf4j;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Slf4j
public class Job extends QuartzJobBean {
    @Autowired
    CommodityTbService commodityTbService;

    @Autowired
    BiddingTbService biddingTbService;

    @Autowired
    DepositPayTbService depositPayTbService;

    @Autowired
    PackageBillsTbService packageBillsTbService;

    @Autowired
    WebSocketService webSocketService;

    @Autowired
    OrderTbService orderTbService;

    /**
    * @Description:  执行任务
    * @Param: [jobExecutionContext]
    * @return: void
    * @Author: shenyini
    * @Date: 2019/12/22
    */
    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        List<CommodityTb> commodityTbs= commodityTbService.getIsBiddingCommodity();
        for(CommodityTb commodityTb : commodityTbs) {
            long time = System.currentTimeMillis() - commodityTb.getEndTime().getTime();
            //如果已过竞拍时间
            if(time > 0) {
                System.out.println(commodityTb.getCid() + "       竞购已结束");
                // 查找所有竞拍过该商品的人
                String cid = commodityTb.getCid();
//                List<DepositUserInfo> depositUserInfos = depositPayTbService.selectDepositUserByCid(cid);
                List<DepositPayTb> depositPayTbs = depositPayTbService.selectDepositPayByCid(commodityTb.getCid());
                if (depositPayTbs.size() != 0) {
                    // 如果该商品有被拍卖过
                    this.haveSomeOneBuy(commodityTb, depositPayTbs);
                } else {
                    // 没有人竞拍过
                    this.NoOneBuy(commodityTb);
                }
            }
        }
        // 获取参数
        JobDataMap jobDataMap = jobExecutionContext.getJobDetail().getJobDataMap();

        // 业务逻辑 ...
        log.info("------springboot quartz one job执行" + jobDataMap.get("name").toString() + "###############" + jobExecutionContext.getTrigger());

    }
    public void haveSomeOneBuy(CommodityTb commodityTb, List<DepositPayTb> depositPayTbs) {
        // 消息推送
        // 将竞拍成功消息推送给最高竞拍者
        BiddingTb biddingTb = biddingTbService.selectMaxBidding(commodityTb.getCid());
        String maxMessage = "恭喜，您成功竞购到"+commodityTb.getCname()+"商品，请您查看订单，并24小时内支付订单。";
        try {
            // 订单表中添加用户订单，状态未支付。
            orderTbService.addOrder(biddingTb,commodityTb);
            webSocketService.sendToUserByUid(biddingTb.getUid(), maxMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 将定金返还给所有竞拍者，并发给所有竞拍者（除了最高竞拍者外）
        BigDecimal deposit = commodityTb.getPrice().add(commodityTb.getAddprice());
        packageBillsTbService.returnMoney(biddingTb.getUid(), depositPayTbs, deposit);

        //通知买家已有最高竞拍者
        // 给卖家提示，没有人竞拍。
        String uid = commodityTbService.selectSeller(commodityTb.getCid());
        String message = "您的"+commodityTb.getCname()+"商品竞拍已结束。已有用户竞拍到最高价";
        try {
            // 发送信息给全部参与竞拍但不是最高价的用户
            webSocketService.sendToUserByUid(uid,message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void NoOneBuy(CommodityTb commodityTb) {
        // 给卖家提示，没有人竞拍。
        String uid = commodityTbService.selectSeller(commodityTb.getCid());
        String message = "您的"+commodityTb.getCname()+"商品竞拍已结束，很抱歉该商品没有人竞拍过。";
        try {
            webSocketService.sendToUserByUid(uid,message);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("该商品没有人竞拍过。");
    }
}
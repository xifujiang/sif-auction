package com.sif.action.task;

import com.sif.action.entity.DepositPayTb;
import com.sif.action.pojo.BiddingTb;
import com.sif.action.entity.CommodityTb;
import com.sif.action.pojo.CommodityPojoTb;
import com.sif.action.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

/**
 * @program: sif-auction
 * @description:竞购结束时向用户发送消息提醒
 * @author: shenyini
 * @create: 2020-01-29 16:44
 **/
@Component
public class BiddingTask {
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
    @Scheduled(cron = "*/1 * * * * ?")
    public void executeInternal() {
        //获取所有仍在竞拍的商品
        List<CommodityPojoTb> commodityTbs= commodityTbService.getIsBiddingCommodity();
            for(CommodityPojoTb commodityTb : commodityTbs) {
                //当前时间大于截止时间，说明商品已经
            long time = System.currentTimeMillis() - commodityTb.getEndTime().getTime();
            //如果已过竞拍时间
            if(time > 0) {
                System.out.println(commodityTb.getCid() + "       竞购已结束");
                // 查找所有竞拍过该商品的人
                String cid = commodityTb.getCid();
//                List<DepositUserInfo> depositUserInfos = depositPayTbService.selectDepositUserByCid(cid);
                List<DepositPayTb> depositPayTbs = depositPayTbService.selectDepositPayByCid(commodityTb.getCid());
                if (depositPayTbs.size() != 0) {
                    // 有被人竞拍过
                    this.haveSomeOneBuy(commodityTb, depositPayTbs);
                } else {
                    // 没有人竞拍过
                    this.NoOneBuy(commodityTb);
                }
            }
        }
    }
    /**
    * @Description: 有人竞拍
    * @Param: [commodityTb, depositPayTbs]
    * @return: void
    * @Author: shenyini
    * @Date: 2020/1/29
    */
    public void haveSomeOneBuy(CommodityPojoTb commodityTb, List<DepositPayTb> depositPayTbs) {
        // 消息推送
        // 将竞拍成功消息推送给最高竞拍者
        BiddingTb biddingTb = biddingTbService.selectMaxBidding(commodityTb.getCid());
        String maxMessage = "恭喜，您成功竞购到"+commodityTb.getCname()+"商品，请您查看订单，并24小时内支付订单。";
        System.err.println(maxMessage);
        try {
            // 订单表中添加用户订单，状态未支付。
            orderTbService.addOrder(biddingTb,commodityTb);
            //修改商品状态 (待付款状态)
            commodityTbService.updateStatus(commodityTb.getCid(), 3);
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

    /**
    * @Description: 没有人竞拍
    * @Param: [commodityTb]
    * @return: void
    * @Author: shenyini
    * @Date: 2020/1/29
    */
    public void NoOneBuy(CommodityPojoTb commodityTb) {
        // 给卖家提示，没有人竞拍。
        String uid = commodityTbService.selectSeller(commodityTb.getCid());
        String message = "您的"+commodityTb.getCname()+"商品竞拍已结束，很抱歉该商品没有人竞拍过。";
        commodityTbService.updateStatus(commodityTb.getCid(), 3);
        System.err.println(message);
        try {
            webSocketService.sendToUserByUid(uid,message);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("该商品没有人竞拍过。");
    }
}

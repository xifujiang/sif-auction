package com.sif.action.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sif.action.entity.OrderTb;
import com.sif.action.pojo.BiddingTb;
import com.sif.action.pojo.CommodityTb;
import com.sif.action.pojo.MyOrderInfo;
import com.sif.action.pojo.OrderInfo;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Sif
 * @since 2019-10-26
 */
public interface OrderTbService extends IService<OrderTb> {

    void insertOrder(OrderInfo orderInfo, String payway,boolean flag);

    void updateOrderStatus(String trade_no);

    /*如果竞拍成功，添加竞拍*/
    void addOrder(BiddingTb biddingTb, CommodityTb commodityTb);

    List<MyOrderInfo> getMyOrder(String uid);
}

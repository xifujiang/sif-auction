package com.sif.action.service.impl;

import com.sif.action.entity.OrderTb;
import com.sif.action.mapper.OrderTbMapper;
import com.sif.action.pojo.BiddingTb;
import com.sif.action.entity.CommodityTb;
import com.sif.action.pojo.CommodityPojoTb;
import com.sif.action.pojo.MyOrderInfo;
import com.sif.action.pojo.OrderInfo;
import com.sif.action.repository.OrderTbRepository;
import com.sif.action.service.CommodityTbService;
import com.sif.action.service.OrderTbService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sif.common.util.EntityUtils;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Sif
 * @since 2019-10-26
 */
@Service
public class OrderTbServiceImpl extends ServiceImpl<OrderTbMapper, OrderTb> implements OrderTbService {

    @Autowired
    private OrderTbMapper orderTbMapper;

    @Autowired
    private OrderTbRepository orderTbRepository;

    @Autowired
    private CommodityTbService commodityTbService;

    @Transactional
    @Override
    public void insertOrder(OrderInfo orderInfo, String payway,boolean flag) {
        OrderTb orderTb = new OrderTb();
        orderTb.setOid(orderInfo.getTradeNo());
        orderTb.setAddressid(orderInfo.getAddressid());
        orderTb.setPayway(payway);
        orderTb.setOrderStatus(flag == true ? 1 : 0);
        orderTbMapper.updateById(orderTb);
    }

    @Transactional
    @Override
    public void updateOrderStatus(String trade_no) {
        OrderTb orderTb = new OrderTb();
        orderTb.setOid(trade_no);
        orderTb.setOrderStatus(1);
        orderTbMapper.updateById(orderTb);

        OrderTb orderTb1 = orderTbMapper.selectById(trade_no);
        String cid = orderTb1.getCid();
        //更新商品状态 待发货
        commodityTbService.updateStatus(cid, 4);

    }

    /** 
    * @Description: 竞拍到时间后，添加到用户订单表
    * @Param: [biddingTb, commodityTb] 
    * @return: void 
    * @Author: shenyini
    * @Date: 2020/3/31 
    */ 
    @Override
    public void addOrder(BiddingTb biddingTb, CommodityPojoTb commodityTb) {
        OrderTb orderTb = new OrderTb();
        orderTb.setOid("2"+biddingTb.getBidid());
        orderTb.setCid(commodityTb.getCid());
        orderTb.setUid(biddingTb.getUid());
        orderTb.setTime(new Date());
        BigDecimal deposit = commodityTb.getPrice().add(commodityTb.getAddprice());
        orderTb.setDeposit(deposit);
        orderTb.setPrice(biddingTb.getBidprice());
        orderTb.setOrderStatus(-1);
        orderTbMapper.insert(orderTb);
    }

    @Override
    public List<MyOrderInfo> getMyOrder(String uid) {
        System.err.println(uid);
        MyOrderInfo myOrderInfo = new MyOrderInfo();
        List<MyOrderInfo> myOrderInfos = EntityUtils.castEntity(orderTbRepository.getMyOrder(uid), MyOrderInfo.class, myOrderInfo);
//        if(myOrderInfos.size() != 0) {
//            myOrderInfos.forEach(item->{
//                System.err.println(item);
//            });
//        } else {
//            System.out.println("没有订单");
//        }
        return myOrderInfos;
    }

}

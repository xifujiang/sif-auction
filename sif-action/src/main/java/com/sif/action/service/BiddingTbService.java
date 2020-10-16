package com.sif.action.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sif.action.entity.BiddingTb;
import com.sif.action.entity.CommodityTb;
import com.sif.action.result.MyBidding;
import com.sif.common.entity.result.Result;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Sif
 * @since 2019-10-26
 */
public interface BiddingTbService extends IService<BiddingTb> {


    void updateBiddingStatus(String trade_no);

    public void updateBiddingStatus(String bid, Integer statu);

    List<MyBidding> findMyBidding(String uid);

    boolean isMaxBidder(String cid, String uid);

    /** 
    * @Description: 获取竞拍状态 
    * @Param: [cid, uid] 
    * @return: com.sif.common.entity.result.Result 
    * @Author: shenyini
    * @Date: 2019/12/12 
    */ 
    Result getBiddingStatus(String cid, String uid);

    void insertBidding(BiddingTb bidding);

    com.sif.action.pojo.BiddingTb selectMaxBidding(String cid);


}

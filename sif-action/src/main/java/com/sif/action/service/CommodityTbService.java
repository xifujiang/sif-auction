package com.sif.action.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sif.action.pojo.GoodDetail;
import com.sif.action.entity.CommodityTb;
import com.sif.action.result.GoodDetailResult;
import com.sif.action.result.HistoryBidding;
import com.sif.action.result.HistoryCommodity;
import com.sif.action.result.ShopForm;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Sif
 * @since 2019-10-26
 */
public interface CommodityTbService extends IService<CommodityTb> {

    void addCommodity(ShopForm shopForm,String uid);

    GoodDetailResult getGoodDetail(String cid);

    ShopForm getCommodity(String cid);

    void delCommodity(String cid);

    void editCommodity(ShopForm shopForm, String cid);

    CommodityTb getOrderCommodity(String cid);

    List<HistoryCommodity> findHistoryGoodList(String cid);

    List<HistoryBidding> selectHistoryBidding(String cid);

    /*商品详情数据*/
    GoodDetail getGood(String cid);

    /*获取所有仍在竞拍的商品*/
    List<com.sif.action.pojo.CommodityTb> getIsBiddingCommodity();

    /*通过商品id查询商家*/
    String selectSeller(String cid);
}

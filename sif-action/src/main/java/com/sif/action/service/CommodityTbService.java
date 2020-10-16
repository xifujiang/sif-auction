package com.sif.action.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sif.action.entity.LogisticsTb;
import com.sif.action.pojo.GoodDetail;
import com.sif.action.entity.CommodityTb;
import com.sif.action.result.*;

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



    void addCommodity(ShopForm shopForm, String uid);

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
    List<com.sif.action.pojo.CommodityPojoTb> getIsBiddingCommodity();

    /*通过商品id查询商家*/
    String selectSeller(String cid);

    void deleteFavorite(String uid, String cid);

    boolean addFavorite(String uid, String cid);

    /*更新状态*/
    void updateStatus(String cid,Integer statu);

    List<CommodityTb> selectStatus(Integer statu);

    void updateNowPrice(CommodityTb commodityTb);

    int sellerCannalOrder(String cid, String uid);

    void insertLogistics(LogisticsTb logisticsTb);

    LogisticsTb selectLogistics(String cid);

    int buyerCannalOrder(String cid, String uid);

    void addComment(CommitPojo commit);

    List<SellerComment> sellerComment(String sellerid);

    List<ItemFour> queryHotCommodity();

    List<ItemFour> queryRecommendCommodity(String uid);


}

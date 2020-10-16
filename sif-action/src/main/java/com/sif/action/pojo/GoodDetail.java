package com.sif.action.pojo;

import com.sif.action.result.GoodDetailResult;
import com.sif.action.result.HistoryBidding;
import com.sif.action.result.HistoryCommodity;
import com.sif.action.result.SellerComment;
import lombok.Data;

import java.util.List;

/**
 * @program: sif-auction
 * @description:
 * @author: shenyini
 * @create: 2019-12-11 15:18
 **/
@Data
public class GoodDetail {
    /*商品详情*/
    private GoodDetailResult goodDetailResult;
    /*该用户出售的历史商品*/
    private List<HistoryCommodity> historyCommodityList;
    /*该商品的竞购记录*/
    private List<HistoryBidding> historyBiddingList;
    /*商家的历史评论*/
    private List<SellerComment> sellerCommentList;
}

package com.sif.action.constants;

/**
 * @program: sif-auction
 * @description:
 * @author: shenyini
 * @create: 2019-12-06 11:43
 **/
public final class Constants {
    private Constants() {}

    public static final String IMAGE_PATH = "static/aucimg/";
    public static final String IMG_URL = "D:\\03MyProgram\\项目\\商品竞拍系统设计与实现\\auction-Vue\\static\\aucimg";
    public static final String FORWARD_URL = "http://localhost:8080/#";


    //credit
//    信用规则：初始信用500，作为卖家，取消一次订单-5信用，完成一次交易+10信用
//    作为买家，退单-5，交易成功+10信用，评论+5信用，差评卖家-5信用
    public static final int CANNAL_ORDER = -5;
    public static final int TRADE_SUCCESS = 10;
    public static final int BUYER_COMMIT = 5;
    public static final int BAD_COMMIT = -5;
}
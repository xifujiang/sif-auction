package com.sif.back.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

/**
 * @program: sif-auction
 * @description:
 * @author: shenyini
 * @create: 2020-01-30 14:49
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Count {
    /*访问数*/
//    private Integer allVisiterCount;
    /*新增访问数*/
//    private Integer newVisiterCount;
    /*总商品*/
    private Object commodityCount;
    /*未处理商品*/
    private Object notDealCount;
    /*未开始商品*/
    private Object notBeginCount;
    /*正在竞购的商品*/
    private Object biddingCount;
    /*结束的商品*/
    private Object endCount;
    /*审核未通过数量*/
    private Object notPassCount;
}

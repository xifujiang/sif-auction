package com.sif.action.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @program: sif-auction
 * @description:
 * @author: xifujiang
 * @create: 2019-11-28 21:21
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderInfo implements Serializable {
    /*订单号*/
    private String tradeNo;
    /*主题名*/
    private String subject;
    /*总价钱*/
    private String totalPrice;
    /*竞购价格*/
    private BigDecimal bidprice;
    /*类型*/
    private String nature;
    /*地址id*/
    private Integer addressid;
    /*备注*/
    private String remarks;
    /*商品id*/
    private String cid;
    /*用户id*/
    private String uid;
    /*用户会员等级id*/
    private Integer memberid;
}

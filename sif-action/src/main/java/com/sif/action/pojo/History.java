package com.sif.action.pojo;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @program: sif-auction
 * @description: 拍卖历史
 * @author: xifujiang
 * @create: 2019-11-14 19:00
 **/
@Data
public class History {
    private String cid;
    private String image;
    private BigDecimal price;
    private Integer score;
}

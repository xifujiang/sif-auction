package com.sif.action.result;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @program: sif-auction
 * @description: 某一用户的历史商品
 * @author: shenyini
 * @create: 2019-12-11 14:43
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class HistoryCommodity implements Serializable {
    private String cid;
    private String image;
    private BigDecimal price;
    private BigDecimal addprice;
}

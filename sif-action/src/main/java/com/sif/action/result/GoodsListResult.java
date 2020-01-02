package com.sif.action.result;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @program: sif-auction
 * @description:
 * @author: xifujiang
 * @create: 2019-11-12 21:42
 **/

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GoodsListResult implements Serializable {
    private String cid;
    private String image;
    private BigDecimal price;
    private String intro;
    private int score;
    private String userName;
}

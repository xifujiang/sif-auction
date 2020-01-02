package com.sif.action.result;

import lombok.Data;

import java.util.List;

/**
 * @program: sif-auction
 * @description:
 * @author: xifujiang
 * @create: 2019-10-26 16:15
 **/
@Data
public class CommodityTypeResult {

    private String name_1;
    private List<CommodityTTResult> twoNav;  //二级title
}

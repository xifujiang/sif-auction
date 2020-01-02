package com.sif.action.result;

import lombok.Data;

import java.util.List;

/**
 * @program: sif-auction
 * @description: 封装 二三级对象
 * @author: xifujiang
 * @create: 2019-10-27 15:41
 **/
@Data
public class CommodityTTResult {
    private String name_2;  //二级目录
    private List<String> name_3; // 三级目录
}

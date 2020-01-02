package com.sif.action.result;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @program: sif-auction
 * @description:
 * @author: xifujiang
 * @create: 2019-10-26 21:46
 **/
@Data
public class CommodityForm implements Serializable {

    private Integer typeid;
    private String name_1;
    private String name_2;
    private String name_3;

}

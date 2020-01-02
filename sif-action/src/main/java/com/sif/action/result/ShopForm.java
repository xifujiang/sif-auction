package com.sif.action.result;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


/**
 * @program: sif-auction
 * @description:
 * @author: xifujiang
 * @create: 2019-11-05 19:43
 **/
@Data
@ToString
public class ShopForm implements Serializable {
    private String cid;
    private String cname;
    private BigDecimal price;
    private BigDecimal addprice;
    private String image;
    private String desc;
    private Object part_time;
    private String type_id;
    private String brand_name;
    private String images;

}

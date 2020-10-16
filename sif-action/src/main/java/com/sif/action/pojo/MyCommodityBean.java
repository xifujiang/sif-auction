package com.sif.action.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @program: sif-auction
 * @description:
 * @author: shenyini
 * @create: 2020-03-30 16:22
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MyCommodityBean {
    private String cid;
    private String cname;
    private BigDecimal price;
    private BigDecimal addprice;
    private BigDecimal nowprice;
    private String image;
    private String images;
    private String des;
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date beginTime;
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date endTime;
    private String typeId;
    private String brandName;
    private String statu;
}

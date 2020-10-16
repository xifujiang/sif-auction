package com.sif.back.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
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
 * @create: 2020-01-30 18:49
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommodityResult {

    /**商品id*/
    private String cid;

    /*商品名称*/
    private String cname;

    /*拍卖用户名*/
    private String uname;

    /*起始价格*/
    private BigDecimal price;

    /*每次加价*/
    private BigDecimal addprice;

    /*创建时间*/
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    /*起拍时间*/
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date beginTime;

    /*结束时间*/
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date endTime;

    /*格式为 一级类型id_二级类型id_三级类型id*/
    private String typeId;

}

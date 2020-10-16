package com.sif.action.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @program: sif-auction
 * @description:
 * @author: xifujiang
 * @create: 2019-11-07 15:03
 **/
@Data
@Entity
@Table(name = "commodity_tb")
public class CommodityPojoTb implements Serializable {

    @Id
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
    private Integer statu;
//    private BigDecimal bidprice;
}

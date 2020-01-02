package com.sif.webmagic.pojo;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @program: sif-auction
 * @description:
 * @author: xifujiang
 * @create: 2019-12-03 10:17
 **/
@Data
@Entity
@Table(name = "webmagic_commodity_tb")
public class WebMagicCommodity {
    @Id
    private String cid;
    private String url;
    private String cnum;
    private String cname;
    private BigDecimal nowprice;
    private BigDecimal addprice;
    private String image;
    private String images;
    private String des;
    private Date begin_time;
    private Date end_time;
    private String type_id;
    private String brand_name;
}

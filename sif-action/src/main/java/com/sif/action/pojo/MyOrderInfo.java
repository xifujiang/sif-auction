package com.sif.action.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @program: sif-auction
 * @description:
 * @author: shenyini
 * @create: 2019-12-25 13:23
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MyOrderInfo implements Serializable {
    private String oid;
    private String uid;
    private String cid;
    private String image;
    private String cname;
    private BigDecimal deposit;
    private BigDecimal price;
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date time;
    private String statu;
}

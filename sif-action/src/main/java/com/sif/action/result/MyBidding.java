package com.sif.action.result;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.criteria.CriteriaBuilder;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @program: sif-auction
 * @description:
 * @author: shenyini
 * @create: 2019-12-10 10:26
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MyBidding implements Serializable {
    private String bidid;
    private String cid;
    private String image;
    private String cname;
    private BigDecimal bidprice;
    private BigDecimal nowprice;
//    private Long bidcount;
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date bidtime;
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date endtime;
    private String nowstatus;


}

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
 * @author: shenyini
 * @create: 2019-12-09 10:28
 **/
@Data
@Entity
@Table(name = "bidding_tb")
public class BiddingTb implements Serializable {
    @Id
    private String bidid;

    /**
     * 拍卖物id
     */
    private String cid;

    /**
     * 用户id
     */
    private String uid;

    /**
     * 竞购价
     */
    private BigDecimal bidprice;

    /**
     * 竞购时间
     */
    private Date bidtime;

    /**
     * 状态
     */
    private Integer statu;
}

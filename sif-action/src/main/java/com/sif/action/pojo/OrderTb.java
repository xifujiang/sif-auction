package com.sif.action.pojo;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 *
 * </p>
 *
 * @author Sif
 * @since 2019-12-09
 */
@Data
@Entity
@Table(name = "order_tb")
public class OrderTb implements Serializable {

    /**
     * 订单id
     */
    @Id
    private String oid;

    /**
     * 拍卖物id
     */
    private String cid;

    /**
     * 购买者id
     */
    private String uid;

    /**
     * 下单时间
     */
    private Date time;

    /**
     * 押金
     */
    private BigDecimal deposit;

    /*成交金额*/
    private BigDecimal price;

    /**
     * 订单状态 竞得物品0（默认），下单1，发货2，收货3
     */
    private Integer orderStatus;

    private Integer addressid;

    private String payway;
}

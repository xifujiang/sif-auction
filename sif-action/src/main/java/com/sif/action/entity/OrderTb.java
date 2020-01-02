package com.sif.action.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

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
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class OrderTb implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 订单id
     */
    @TableId(value = "oid", type = IdType.ID_WORKER_STR)
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

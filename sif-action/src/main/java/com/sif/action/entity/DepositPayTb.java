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
 * @since 2019-12-12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class DepositPayTb implements Serializable {

private static final long serialVersionUID=1L;

    /**
     * 押金支付id
     */
    @TableId(value = "did", type = IdType.ID_WORKER_STR)
    private String did;

    /**
     * 商品id
     */
    private String cid;

    /**
     * 用户id
     */
    private String uid;

    /**
     * 押金
     */
    private BigDecimal deposit;

    /**
     * 支付时间
     */
    private Date paytime;

    /**
     * 支付方式
     */
    private String payway;

    /**
     * 未支付-0，已支付-1，押金退还-2
     */
    private Integer statu;


}

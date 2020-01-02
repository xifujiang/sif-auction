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
public class BiddingTb implements Serializable {

private static final long serialVersionUID=1L;

    /**
     * 竞购id
     */
    @TableId(value = "bidid", type = IdType.ID_WORKER_STR)
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
     * 未付款-0，已付款-1
     */
    private Integer statu;

}

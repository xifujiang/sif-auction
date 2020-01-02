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
 * @since 2019-12-13
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class PackageBillsTb implements Serializable {

private static final long serialVersionUID=1L;

    /**
     * 钱包账单记录表
     */
    @TableId(value = "billid", type = IdType.ID_WORKER_STR)
    private String billid;

    /**
     * 用户id
     */
    private String uid;

    /**
     * 价格 进账为正，出账为负
     */
    private BigDecimal price;

    /**
     * 时间
     */
    private Date time;

    /**
     * 进出账类型
     */
    private String type;

    /**
     * 详细信息
     */
    private String info;

    /**
     * 状态
     */
    private Integer statu;


}

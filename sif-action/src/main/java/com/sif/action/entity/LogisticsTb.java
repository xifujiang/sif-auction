package com.sif.action.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author Sif
 * @since 2020-04-01
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class LogisticsTb implements Serializable {

private static final long serialVersionUID=1L;

    @TableId(value = "lid", type = IdType.AUTO)
    private Integer lid;

    /**
     * 物流id
     */
    private String logisticsid;

    /**
     * 物流类型
     */
    private String logisticstype;

    /**
     * 商品id
     */
    private String cid;

    private String oid;

    /**
     * 收货地址
     */
    private Integer addid;

    /**
     * 是否修改地址（0未修改，1修改）
     */
    private Integer ischange;

    /**
     * 修改的地址id
     */
    private Integer changeid;

    /**
     * 备注
     */
    private String info;


}

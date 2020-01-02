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
 * @since 2019-12-09
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class UserCommodityTb implements Serializable {

private static final long serialVersionUID=1L;

    /**
     * id
     */
    @TableId(value = "ucid", type = IdType.AUTO)
    private Integer ucid;

    /**
     * 用户id
     */
    private String uid;

    /**
     * 商品id
     */
    private String cid;


}

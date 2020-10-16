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
 * @since 2020-03-23
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class FavoriteCommodityTb implements Serializable {

private static final long serialVersionUID=1L;

    /**
     * 唯一标识
     */
    @TableId(value = "fid", type = IdType.AUTO)
    private Integer fid;

    /**
     * 商品id
     */
    private String cid;

    /**
     * 用户id
     */
    private String uid;


}

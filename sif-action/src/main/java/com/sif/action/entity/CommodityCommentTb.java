package com.sif.action.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
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
public class CommodityCommentTb implements Serializable {

private static final long serialVersionUID=1L;

    /**
     * 评论id
     */
    @TableId(value = "commentid", type = IdType.ID_WORKER_STR)
    private Integer commentid;

    /**
     * 拍卖物id
     */
    private String cid;

    /**
     * 商品质量
     */
    private Integer cquality;

    /**
     * 物流速度
     */
    private Integer cspeed;

    /**
     * 卖家态度
     */
    private Integer cattitude;

    /**
     * 评论语
     */
    private String comment;

    /**
     * 时间
     */
    private Date time;


}

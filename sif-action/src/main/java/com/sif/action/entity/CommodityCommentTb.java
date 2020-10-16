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
 * @since 2020-04-06
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class CommodityCommentTb implements Serializable {

private static final long serialVersionUID=1L;

    /**
     * 评论id
     */
    @TableId(value = "commentid", type = IdType.AUTO)
    private Integer commentid;

    /**
     * 拍卖物id
     */
    private String cid;

    private String sellerid;

    private String buyerid;

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

    public CommodityCommentTb(){}

    public CommodityCommentTb(Integer commentid, String cid, String sellerid, String buyerid, Integer cquality, Integer cspeed, Integer cattitude, String comment, Date time) {
        this.commentid = commentid;
        this.cid = cid;
        this.sellerid = sellerid;
        this.buyerid = buyerid;
        this.cquality = cquality;
        this.cspeed = cspeed;
        this.cattitude = cattitude;
        this.comment = comment;
        this.time = time;
    }
}

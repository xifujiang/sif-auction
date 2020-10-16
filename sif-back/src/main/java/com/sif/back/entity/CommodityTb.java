package com.sif.back.entity;

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
public class CommodityTb implements Serializable {

private static final long serialVersionUID=1L;

    /**
     * 商品id
     */
    @TableId(value = "cid", type = IdType.ID_WORKER_STR)
    private String cid;

    /**
     * 商品名称
     */
    private String cname;

    /**
     * 起始价格
     */
    private BigDecimal price;

    /**
     * 每次加价
     */
    private BigDecimal addprice;

    /**
     * 商品图片
     */
    private String image;

    /**
     * 商品图片列表
     */
    private String images;

    /**
     * 详细描述
     */
    private String des;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 起拍时间
     */
    private Date beginTime;

    /**
     * 结束时间
     */
    private Date endTime;

    /**
     * 格式为 一级类型id_二级类型id_三级类型id
     */
    private String typeId;

    /**
     * 品牌名称
     */
    private String brandName;

    /**
     * 商品状态 0-未审核，1-未开始拍卖 2-正在竞拍，3-拍卖完成，4-下架，5-删除
     */
    private Integer statu;


}

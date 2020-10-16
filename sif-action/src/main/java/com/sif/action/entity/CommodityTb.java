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
 * @since 2020-03-30
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

    private BigDecimal nowprice;

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
     * 商品状态 -1-未审核0-展示阶段 1-开拍 2-在拍 3-竞拍结束 4-待发货 5-已发货 6-已收货 7-交易成功 8-下架，9-删除 10-流拍
     */
    private Integer statu;


}

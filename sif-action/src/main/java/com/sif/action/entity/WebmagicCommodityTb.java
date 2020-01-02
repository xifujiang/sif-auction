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
public class WebmagicCommodityTb implements Serializable {

private static final long serialVersionUID=1L;

    @TableId(value = "cid", type = IdType.ID_WORKER_STR)
    private String cid;

    private String url;

    private String cnum;

    private String cname;

    private BigDecimal nowprice;

    private BigDecimal addprice;

    private String image;

    private String images;

    private String des;

    private Date beginTime;

    private Date endTime;

    private String typeId;

    private String brandName;


}

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
public class MemberPayTb implements Serializable {

private static final long serialVersionUID=1L;

    @TableId(value = "mid", type = IdType.ID_WORKER_STR)
    private String mid;

    private String uid;

    private Integer memberid;

    private BigDecimal price;

    private Integer statu;

    private Date paytime;
    private String payway;
}

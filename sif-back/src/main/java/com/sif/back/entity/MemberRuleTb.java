package com.sif.back.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;

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
public class MemberRuleTb implements Serializable {

private static final long serialVersionUID=1L;

    /**
     * 会员规则id
     */
    @TableId(value = "memberid", type = IdType.AUTO)
    private Integer memberid;

    /**
     * 会员等级
     */
    private String rank;

    /**
     * 保证金
     */
    private BigDecimal cautionmoney;

    /**
     * 可投标件数
     */
    private Integer amount;

    /**
     * 单词金额限制
     */
    private Integer pricepremiss;

    /**
     * 出价账号
     */
    private String premiss;


}

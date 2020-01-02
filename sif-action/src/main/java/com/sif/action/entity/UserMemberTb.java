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
public class UserMemberTb implements Serializable {

private static final long serialVersionUID=1L;

    /**
     * 用户_会员表唯一标识
     */
    @TableId(value = "mid", type = IdType.AUTO)
    private Integer mid;

    /**
     * 用户id
     */
    private String uid;

    /**
     * 会员级别id
     */
    private Integer memberid;


}

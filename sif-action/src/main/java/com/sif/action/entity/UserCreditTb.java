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
public class UserCreditTb implements Serializable {

private static final long serialVersionUID=1L;

    /**
     * 信用表id
     */
    @TableId(value = "creditid", type = IdType.AUTO)
    private Integer creditid;

    /**
     * 用户id
     */
    private String uid;

    /**
     * 信用值，默认500
     */
    private Integer score;


}

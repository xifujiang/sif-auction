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
public class AdminTb implements Serializable {

private static final long serialVersionUID=1L;

    /**
     * 管理员id
     */
    @TableId(value = "adminid", type = IdType.ID_WORKER_STR)
    private Integer adminid;

    /**
     * 管理员名
     */
    private String name;

    /**
     * 管理员密码
     */
    private String password;

    /**
     * 权限
     */
    private Integer permiss;


}

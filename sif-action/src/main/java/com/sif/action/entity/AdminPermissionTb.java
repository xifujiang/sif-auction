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
public class AdminPermissionTb implements Serializable {

private static final long serialVersionUID=1L;

    /**
     * 管理员权限表id
     */
    @TableId(value = "permissionid", type = IdType.ID_WORKER_STR)
    private Integer permissionid;

    /**
     * 权限名
     */
    private String name;

    /**
     * 可使用权限介绍
     */
    private String intro;


}

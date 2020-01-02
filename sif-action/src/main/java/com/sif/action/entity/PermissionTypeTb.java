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
public class PermissionTypeTb implements Serializable {

private static final long serialVersionUID=1L;

    /**
     * 权限id
     */
    @TableId(value = "ptypeid", type = IdType.ID_WORKER_STR)
    private Integer ptypeid;

    /**
     * 权限
     */
    private String permission;


}

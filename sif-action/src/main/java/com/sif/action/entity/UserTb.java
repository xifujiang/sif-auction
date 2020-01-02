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
public class UserTb implements Serializable {

private static final long serialVersionUID=1L;

    /**
     * 账号id
     */
    @TableId(value = "uid", type = IdType.ID_WORKER_STR)
    private String uid;

    /**
     * 用户名
     */
    private String name;

    /**
     * 密码
     */
    private String password;

    private Integer status;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 年龄
     */
    private Integer age;

    /**
     * 真实姓名
     */
    private String truename;

    private String idcard;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 邮箱
     */
    private String mail;


}

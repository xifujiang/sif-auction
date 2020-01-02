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
public class UserAddressTb implements Serializable {

private static final long serialVersionUID=1L;

    /**
     * 地址id
     */
    @TableId(value = "addid", type = IdType.AUTO)
    private Integer addid;

    /**
     * 用户id
     */
    private String userid;

    /**
     * 收件人
     */
    private String addressee;

    /**
     * 省份
     */
    private String province;

    /**
     * 市
     */
    private String city;

    /**
     * 区
     */
    private String part;

    /**
     * 详细地址
     */
    private String detail;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 邮政编码
     */
    private String postalcode;

    /**
     * 是否为默认地址，默认为0
     */
    private Integer isdefault;


}

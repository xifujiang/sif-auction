package com.sif.action.pojo;

import lombok.Data;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @program: sif-auction
 * @description:
 * @author: shenyini
 * @create: 2019-12-17 15:16
 **/
@Data
@Entity
@Table(name = "user_package_tb")
public class UserPackageTb implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 钱包id
     */
    @Id
    private Integer pid;

    /**
     * 用户id
     */
    private String uid;

    /**
     * 6位支付密码
     */
    private String passwd;

    /**
     * 金额id
     */
    private BigDecimal money;


}


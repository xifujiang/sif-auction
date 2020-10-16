package com.sif.back.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @program: sif-auction
 * @description:
 * @author: shenyini
 * @create: 2020-01-30 15:37
 **/
@Data
@Entity
@Table(name = "admin_tb")
public class Admin implements Serializable {
    /**
     * 管理员id
     */
    @Id
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

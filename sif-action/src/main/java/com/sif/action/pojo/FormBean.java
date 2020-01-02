package com.sif.action.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @program: sif-auction
 * @description:
 * @author: shenyini
 * @create: 2019-12-23 22:40
 **/
public class FormBean {
    private String uid;
    private String name;
    private String password;
    private Integer status;
    private String nickname;
    private Integer age;
    private String truename;
    private String idcard;
    private String phone;
    private String mail;
    private String buyer;
    private String seller;
    private String bidid;
    private BigDecimal bidprice;
    private String cname;
    private BigDecimal price;
    private BigDecimal addprice;
    private String image;
    private String images;
    private String des;
    private Date createTime;
    private Date updateTime;
    private Date beginTime;
    private Date endTime;
    private String typeId;
    private String brandName;
    private Integer memberid;


}

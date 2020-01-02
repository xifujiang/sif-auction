package com.sif.action.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @program: sif-auction
 * @description:
 * @author: shenyini
 * @create: 2019-12-22 13:58
 **/
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class MessageTb implements Serializable {
    private static final long serialVersionUID=1L;

    /**
     * 消息id
     */
    @TableId(value = "messageid", type = IdType.AUTO)
    private String messageid;

    /**
     * 用户id
     */
    private String uid;


    /**
     * 信息
     */
    private String message;

    /**
     * 发送时间
     */
    private Date time;

}

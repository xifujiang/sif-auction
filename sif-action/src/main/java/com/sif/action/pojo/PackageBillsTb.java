package com.sif.action.pojo;


import lombok.Data;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @program: sif-auction
 * @description:
 * @author: shenyini
 * @create: 2019-12-17 15:14
 **/
@Data
@Entity
@Table(name = "package_bills_tb")
public class PackageBillsTb implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 钱包账单记录表
     */
    @Id
    private String billid;

    /**
     * 用户id
     */
    private String uid;

    /**
     * 价格 进账为正，出账为负
     */
    private BigDecimal price;

    /**
     * 时间
     */
    private Date time;

    /**
     * 进出账类型
     */
    private String type;

    /**
     * 详细信息
     */
    private String info;

    /**
     * 状态
     */
    private Integer statu;


}

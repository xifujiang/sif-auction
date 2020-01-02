package com.sif.action.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author Sif
 * @since 2019-11-11
 */
@Data
@Entity
@Table(name = "user_commodity_tb")
public class UserCommodityTb implements Serializable {

    @Id
    private Integer ucid;

    /**
     * 用户id
     */
    private String uid;

    /**
     * 商品id
     */
    private String cid;


}

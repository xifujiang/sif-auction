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
@Table(name = "user_credit_tb")
public class UserCreditTb implements Serializable {

private static final long serialVersionUID=1L;

    /**
     * 信用表id
     */
    @Id
    private Integer creditid;

    /**
     * 用户id
     */
    private String uid;

    /**
     * 信用值，默认500
     */
    private Integer score;


}

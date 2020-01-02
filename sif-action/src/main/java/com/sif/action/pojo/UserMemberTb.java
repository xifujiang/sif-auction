package com.sif.action.pojo;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @program: sif-auction
 * @description:
 * @author: xifujiang
 * @create: 2019-11-19 14:22
 **/
@Data
@Entity
@Table(name = "user_member_tb")
public class UserMemberTb implements Serializable {
    /**
     * 用户_会员表唯一标识
     */
    @Id
    private Integer mid;

    /**
     * 用户id
     */
    private String uid;

    /**
     * 会员级别id
     */
    private Integer memberid;

}

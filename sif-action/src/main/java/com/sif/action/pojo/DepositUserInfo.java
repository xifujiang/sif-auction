package com.sif.action.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @program: sif-auction
 * @description:
 * @author: shenyini
 * @create: 2019-12-23 22:14
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DepositUserInfo implements Serializable {
    private String cid;
    private String uid;
    private String mail;
}

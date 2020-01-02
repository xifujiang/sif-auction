package com.sif.action.result;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @program: sif-auction
 * @description:
 * @author: xifujiang
 * @create: 2019-11-06 21:23
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginResult implements Serializable {

    /*账号id*/
    private String uid;
    /*用户名*/
    private String name;
    /*用户等级*/
    private Integer memberid;
    /*用户信用*/
    private Integer score;

}

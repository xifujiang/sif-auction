package com.sif.action.result;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @program: sif-auction
 * @description:
 * @author: shenyini
 * @create: 2019-12-10 15:34
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDetail implements Serializable {
    private String uname;
    private Integer age;
    private String phone;
    private String mail;
    private String rank;
    private Integer score;
    private BigDecimal money;
}

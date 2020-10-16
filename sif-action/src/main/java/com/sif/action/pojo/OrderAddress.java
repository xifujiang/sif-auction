package com.sif.action.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @program: sif-auction
 * @description:
 * @author: shenyini
 * @create: 2020-04-01 13:48
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderAddress {

    private String oid;
    private int addressid;
    private String addressee;
    private String province;
    private String city;
    private String part;
    private String detail;
    private String phone;
}

package com.sif.back.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @program: sif-auction
 * @description:
 * @author: shenyini
 * @create: 2020-01-31 14:28
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Type {
    private String name3; //当前商品类型
    private Integer typeid3; //当前商品类型id
    private String name2; // 父类型
    private Integer typeid2;
    private String name1; // 父父类型
    private Integer typeid1;
}

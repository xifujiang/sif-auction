package com.sif.action.result;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @program: sif-auction
 * @description:
 * @author: shenyini
 * @create: 2020-04-06 22:19
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SellerComment implements Serializable {
    private String cid;
    private String comment;
    private String quality;
    private String speed;
    private String attitude;
    private String name;
    private String cname;
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date time;
}

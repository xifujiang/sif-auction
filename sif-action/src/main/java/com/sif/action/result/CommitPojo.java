package com.sif.action.result;

import lombok.Data;

import java.io.Serializable;

/**
 * @program: sif-auction
 * @description:
 * @author: shenyini
 * @create: 2020-04-06 18:06
 **/
@Data
public class CommitPojo implements Serializable {
    private String buyerid;
    private String cid;
    private String comment;
    private Integer cattitude;
    private Integer cquality;
    private Integer cspeed;

    public CommitPojo() {
    }

    public CommitPojo(String buyerid, String cid, String comment, Integer cattitude, Integer cquality, Integer cspeed) {
        this.buyerid = buyerid;
        this.cid = cid;
        this.comment = comment;
        this.cattitude = cattitude;
        this.cquality = cquality;
        this.cspeed = cspeed;
    }
}

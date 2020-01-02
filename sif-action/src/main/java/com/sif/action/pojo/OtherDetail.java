package com.sif.action.pojo;

import lombok.Data;

/**
 * @program: sif-auction
 * @description:
 * @author: xifujiang
 * @create: 2019-12-01 09:53
 **/
@Data
public class OtherDetail {
    private String title;
    private String content;

    public OtherDetail(String title, String content) {
        this.title = title;
        this.content = content;
    }
}

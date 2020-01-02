package com.sif.action.result;

import lombok.Data;

import java.io.Serializable;

/**
 * @program: sif-auction
 * @description:
 * @author: xifujiang
 * @create: 2019-11-02 18:20
 **/
@Data
public class FileForm implements Serializable {
    private String name;
    private String url;
}

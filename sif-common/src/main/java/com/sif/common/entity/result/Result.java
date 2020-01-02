package com.sif.common.entity.result;

import com.sif.common.entity.code.ResultCodeEnum;
import lombok.Data;

/**
 * @program: sif-auction
 * @description:   返回结果
 * @author: xifujiang
 * @create: 2019-10-15 14:34
 **/
@Data
public class Result {
    private boolean flag;
    private Integer code;
    private String message;
    private Object data;

    public Result(){

    }
    public Result(Integer code){
        this.code = code;
    }
    public Result(boolean flag, Integer code, String message){
        this.flag = flag;
        this.code = code;
        this.message = message;
    }

    public Result(boolean flag, Integer code, String message, Object data) {
        this.flag = flag;
        this.code = code;
        this.message = message;
        this.data = data;
    }

    // 接收枚举类型
    public Result(ResultCodeEnum resultCodeEnum) {
        this.flag = resultCodeEnum.getSuccess();
        this.code = resultCodeEnum.getCode();
        this.message = resultCodeEnum.getMessage();
        this.data = null;
    }

    // 接收枚举类型
    public Result(ResultCodeEnum resultCodeEnum, Object data) {
        this.flag = resultCodeEnum.getSuccess();
        this.code = resultCodeEnum.getCode();
        this.message = resultCodeEnum.getMessage();
        this.data = data;
    }

}

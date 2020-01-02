package com.sif.common.exception;

import com.sif.common.entity.code.ResultCodeEnum;
import lombok.Data;

/**
 * @program: sif-auction
 * @description: 自定义异常类
 * @author: xifujiang
 * @create: 2019-10-15 14:34
 **/
@Data
public class AuctionException extends RuntimeException{

    //状态码
    private Integer code;


    /**
     * @Description //TODO 接受状态码和消息
     * @Params [code, message]
     * @Return
     **/
    public AuctionException(Integer code, String message) {
        super(message);
        this.code = code;
    }

    /**
     * @Description //TODO 接受枚举类型
     * @Params [resultCodeEnum]
     * @Return
     **/
    public AuctionException(ResultCodeEnum resultCodeEnum){
        super(resultCodeEnum.getMessage());
        this.code = resultCodeEnum.getCode();
    }

    @Override
    public String toString() {
        return "AuctionException{" +
                "message=" + this.getMessage() +
                ", code=" + code +
                '}';
    }
}

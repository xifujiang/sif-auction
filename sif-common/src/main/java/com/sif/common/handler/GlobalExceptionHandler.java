package com.sif.common.handler;


import com.sif.common.entity.code.ResultCodeEnum;
import com.sif.common.entity.result.Result;
import com.sif.common.exception.AuctionException;
import com.sif.common.util.ExceptionUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Result error(Exception e){
        //打印堆栈信息
        log.error(ExceptionUtil.getMessage(e));
        return new Result(ResultCodeEnum.SERVER_BUSY);
    }


    @ExceptionHandler(AuctionException.class)
    @ResponseBody
    public Result error(AuctionException e){
        log.error(ExceptionUtil.getMessage(e));
        return new Result(false,e.getCode(),e.getMessage());

    }
}

package com.sif.action.controller;


import com.sif.action.pojo.MyOrderInfo;
import com.sif.action.service.OrderTbService;
import com.sif.common.entity.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Sif
 * @since 2019-10-26
 */
@RestController
public class OrderTbController {

    @Autowired
    OrderTbService orderTbService;

    /**
    * @Description: 支付订单
    * @Param: [uid, bid]
    * @return: com.sif.common.entity.result.Result
    * @Author: xifujiang
    * @Date: 2019/11/1
    */
    public Result payOrder(String uid, String bid){
        return new Result();
    }


    /**
    * @Description: 修改订单状态
    * @Param:
    * @return:
    * @Author: xifujiang
    * @Date: 2019/11/1
    */
    public Result updateOrderStatus(String name){
        return new Result();
    }

    /**
    * @Description: 获取我的订单信息
    * @Param: [uid]
    * @return: com.sif.common.entity.result.Result
    * @Author: shenyini
    * @Date: 2019/12/25
    */
    @GetMapping("/api/getMyOrder")
    public Result getMyOrder(String uid) {
        List<MyOrderInfo> myOrder = orderTbService.getMyOrder(uid);
        return new Result(true, 200, "获取成功", myOrder);
    }
}


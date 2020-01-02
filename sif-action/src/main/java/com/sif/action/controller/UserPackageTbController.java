package com.sif.action.controller;


import com.sif.action.entity.UserPackageTb;
import com.sif.action.pojo.OrderInfo;
import com.sif.action.service.*;
import com.sif.common.entity.result.Result;
import com.sif.common.util.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Sif
 * @since 2019-12-13
 */
@RestController
public class UserPackageTbController {

    @Autowired
    UserPackageTbService userPackageTbService;
    @Autowired
    DepositPayTbService depositPayTbService;
    @Autowired
    MemberPayTbService memberPayTbService;
    @Autowired
    OrderTbService orderTbService;
    @PostMapping("/api/getMoney")
    public Result getMoney(String uid) {
        UserPackageTb userPackageTb = userPackageTbService.getMoney(uid);
        String md5 = userPackageTb.getPasswd();
        return new Result(true, 200, "获取成功", userPackageTb);
    }

    @PostMapping("/api/changePackageMoney")
    public Result changePackageMoney(@RequestBody OrderInfo orderInfo) {
        userPackageTbService.changePackageMoney(orderInfo);
        // 将支付订单存入到对应的表中
        if("支付竞拍保证金".equals(orderInfo.getNature())) {
            depositPayTbService.insertDeposit(orderInfo, "package", true);
        } else if("升级会员".equals(orderInfo.getNature())) {
            memberPayTbService.insertMemberPay(orderInfo, "package", true);
        } else if("订单支付".equals(orderInfo.getNature())){
            orderTbService.insertOrder(orderInfo, "package", true);
        }
        return new Result(200);
    }

    /**
    * @Description: 钱包充值
    * @Param: []
    * @return: com.sif.common.entity.result.Result
    * @Author: shenyini
    * @Date: 2019/12/14
    */
    @PostMapping("/api/addPackageMoney")
    public Result addPackageMoney(String uid, BigDecimal money) {

        return new Result(200);
    }

    /** 
    * @Description: 返还押金，修改个人竞购状态
    * @Param: [cid] 
    * @return: com.sif.common.entity.result.Result 
    * @Author: shenyini
    * @Date: 2019/12/14 
    */ 
    @PostMapping("/api/returnPackageMoney")
    public Result returnPackageMoney(String cid) {
        return new Result(200);
    }
}


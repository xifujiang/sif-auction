package com.sif.action.controller;


import com.sif.action.entity.UserAddressTb;
import com.sif.action.service.UserAddressTbService;
import com.sif.action.service.UserTbService;
import com.sif.common.entity.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
public class UserAddressTbController {

    @Autowired
    UserTbService userTbService;

    @Autowired
    UserAddressTbService userAddressTbService;

    /** 
    * @Description: 添加收货地址 
    * @Param: [address, userid] 
    * @return: com.sif.common.entity.result.Result 
    * @Author: shenyini
    * @Date: 2020/1/1 
    */ 
    @PostMapping(value = "api/addAddress")
    @ResponseBody
    public Result addAddress(@RequestBody UserAddressTb address, String userid){
        address.setUserid(userid);
        userAddressTbService.addAddress(address);
        return new Result(200);
    }

    /**
    * @Description: 获取地址列表
    * @Param: [name]
    * @return: com.sif.common.entity.result.Result
    * @Author: xifujiang
    * @Date: 2019/10/31
    */
    @GetMapping(value = "api/getAddress")
    @ResponseBody
    public Result getAddress(String userid){
        List<UserAddressTb> addList =  userAddressTbService.selectAddress(userid);
        return new Result(true,200,"成功", addList);
    }

    /**
    * @Description: 修改地址
    * @Param: [address]
    * @return: com.sif.common.entity.result.Result
    * @Author: xifujiang
    * @Date: 2019/10/31
    */
    @PostMapping(value = "api/editAddress")
    public Result editAddress(@RequestBody UserAddressTb address){
        userAddressTbService.updateAddress(address);
        return new Result(200);
    }

    @GetMapping(value = "api/delAddress")
    @ResponseBody
    public Result delAddress(String addid){
        userAddressTbService.delAddress(Integer.valueOf(addid));
        return new Result(200);
    }
}


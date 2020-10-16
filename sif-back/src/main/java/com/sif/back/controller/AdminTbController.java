package com.sif.back.controller;


import com.sif.back.entity.AdminTb;
import com.sif.back.entity.CommodityTb;
import com.sif.back.pojo.CommodityResult;
import com.sif.back.pojo.Count;
import com.sif.back.pojo.Type;
import com.sif.back.service.AdminTbService;
import com.sif.common.entity.result.Result;
import com.sif.common.util.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Sif
 * @since 2019-11-11
 */
@RestController
public class AdminTbController {

    @Autowired
    private AdminTbService adminTbService;

    @PostMapping("/api/login")
    public Result login(String name, String password) {
        String pwd = MD5Utils.MD5(password);
        System.err.println(pwd);
        AdminTb adminTb = new AdminTb();
        adminTb.setName(name);
        adminTb.setPassword(pwd);
        AdminTb admin = adminTbService.login(adminTb);
        if(null != admin) {
            return new Result(true, 200, "登录成功", admin);
        }
        return new Result(true, 200, "登录失败");
    }

    @GetMapping("/api/info/Count")
    public Result count() {
        Count count = adminTbService.getCount();
        return new Result(true, 200, "获取成功", count);
    }

    @GetMapping("/api/commodity/notDeal")
    public Result notDealCommodity() {
        List<CommodityResult> commodityResults = adminTbService.getNotDealCommodity();
        return new Result(true, 200, "获取成功", commodityResults);
    }

    @GetMapping("/api/commodity/changeStatu")
    public Result changeCommodityStatus(String cid, Integer statu) {
        adminTbService.changeCommodityStatus(cid,statu);
        return new Result(true, 200, "修改成功");
    }


}


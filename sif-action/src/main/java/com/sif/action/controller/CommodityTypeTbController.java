package com.sif.action.controller;

import com.sif.action.entity.CommodityTypeTb;
import com.sif.action.result.CommodityTypeResult;
import com.sif.action.service.CommodityTypeTbService;
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
//@CrossOrigin
public class CommodityTypeTbController {

    @Autowired
    CommodityTypeTbService commodityTypeTbService;

    /** 
    * @Description: 返回三级
     * {"flag":true,"code":200,
     * "message":"查询成功","data":{"name_1":"二手名表","twoNav":[
     * {"name_2":"品牌手表","name_3":["迪奥","欧米伽","万国","爱马仕","华生","双师","劳力士","浪琴","雷达","梭曼","卡西欧","卡地亚","古驰","昆仑","精工","积家","西铁城","真力时","香奈儿","百年灵","宝格丽","芬迪","伯爵","名士"]},
     * {"name_2":"男士手表","name_3":["电子表","石英表","手动上链","自动上链"]},
     * {"name_2":"女士手表","name_3":["SWATCH","石英表","手动上链"]}]}}
    * @Param: [typeId] 
    * @return: com.sif.common.entity.result.Result 
    * @Author: xifujiang 
    * @Date: 2019/11/3 
    */ 
    @GetMapping("/api/ctype/{typeid}")
    public Result getByTypeId(@PathVariable("typeid") Integer typeId){
        List<CommodityTypeResult> data = commodityTypeTbService.getByTypeId(typeId);
        return new Result(true,200,"查询成功",data.get(0));
    }


    /**
    * @Description: 返回一级商品对象
    * @Param: []
    * @return: com.sif.common.entity.result.Result
    * @Author: xifujiang
    * @Date: 2019/10/26
    */
    @GetMapping("api/oneType")
    public Result getAllOneType(){
        //  typeid; name; parentid;
        List<CommodityTypeTb> data = commodityTypeTbService.getAllOneType();
        return new Result(true, 200, "查询成功", data);
    }

    /** 
    * @Description: 查找类别
    * @Param: [parentid] 
    * @return: com.sif.common.entity.result.Result 
    * @Author: xifujiang 
    * @Date: 2019/11/3 
    */ 
    @GetMapping("api/selectByParentid/{name}")
    public Result selectByParentid(@PathVariable("name") String name){
        CommodityTypeTb ct = commodityTypeTbService.selectByName(name);
        List<CommodityTypeTb> list = commodityTypeTbService.selectByParentid(ct.getTypeid());
        return new Result(true, 200, "查询成功", list);
    }

}


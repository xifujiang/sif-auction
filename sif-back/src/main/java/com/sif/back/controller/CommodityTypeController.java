package com.sif.back.controller;

import com.sif.back.entity.CommodityTypeTb;
import com.sif.back.pojo.Type;
import com.sif.back.service.CommodityTypeService;
import com.sif.common.entity.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @program: sif-auction
 * @description:
 * @author: shenyini
 * @create: 2020-02-01 19:20
 **/
@RestController
public class CommodityTypeController {

    @Autowired
    CommodityTypeService commodityTypeService;

    @GetMapping("/api/query/commodityType")
    public Result queryCommodityType(){
        List<Type> types = commodityTypeService.getCommodityType();
        return new Result(true, 200, "查询成功",types);
    }
    @GetMapping("/api/add/commodityType")
    public Result addCommodityType(String type, String parent) {
        commodityTypeService.addCommodityType(type, parent);
        return new Result(true, 200, "添加成功");
    }

    @GetMapping("/api/query/oneCommodityType")
    public Result getOneCommodityType(Integer parentid) {
        List<CommodityTypeTb> list = commodityTypeService.getOneCommodityType(parentid);
        return new Result(true, 200, "获取类型列表成功",list);
    }

    @GetMapping("/api/query/twoCommodityType")
    public Result getTwoCommodityType(String parentName) {
        List<CommodityTypeTb> list = commodityTypeService.getTwoCommodityType(parentName);
        return new Result(true,200,"获取二级列表成功",list);
    }

    @GetMapping("/api/edit/commodityType")
    public Result editCommodityType(Integer typeid, String name1, String name2) {
        commodityTypeService.editCommodityType(typeid,name1,name2);
        return new Result(true, 200, "编辑成功");
    }

    @GetMapping("/api/delete/commodityType")
    public Result deleteCommodityType(Integer typeid) {
        commodityTypeService.deleteCommodityType(typeid);
        return new Result(true, 200, "删除成功");
    }

    @GetMapping("/api/add/sunCommodityType")
    public Result addSunCommodityType(String name,Integer parentid){
        commodityTypeService.addSunCommodityType(name, parentid);
        return new Result(true, 200, "添加成功");
    }

}

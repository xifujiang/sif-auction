package com.sif.action.controller;


import com.sif.action.constants.Constants;
import com.sif.action.entity.CommodityTb;
import com.sif.action.pojo.GoodDetail;
import com.sif.action.repository.CommodityTbRepository;
import com.sif.action.result.*;
import com.sif.action.service.CommodityTbService;
import com.sif.common.entity.result.Result;
import com.sif.common.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
public class CommodityTbController {

    @Autowired
    CommodityTbService commodityTbService;

    @Autowired
    private CommodityTbRepository commodityTbRepository;

    /** 
    * @Description: 添加拍卖物
    * @Param: [commodityTb] 
    * @return: com.sif.common.entity.result.Result 
    * @Author: xifujiang 
    * @Date: 2019/11/1 
    */
    @PostMapping("/api/addCommodity")
    public Result addCommodity(@RequestBody ShopForm shopForm, String uid){
        commodityTbService.addCommodity(shopForm,uid);
        return new Result(true,200,"添加成功");
    }

    /**
    * @Description: 获取某个商品信息 用于编辑
    * @Param: [cid]
    * @return: com.sif.common.entity.result.Result
    * @Author: shenyini
    * @Date: 2019/12/8
    */
    @GetMapping("/api/getCommodity")
    public Result getCommodity(String cid) {
        ShopForm shopForm = commodityTbService.getCommodity(cid);
        return new Result(true, 200, "获取成功",shopForm);
    }

    /**
    * @Description: 获取订单商品
    * @Param: [cid]
    * @return: com.sif.common.entity.result.Result
    * @Author: shenyini
    * @Date: 2019/12/8
    */
    @GetMapping("/api/getOrderCommodity")
    public Result getOrderCommodity(String cid) {
        CommodityTb commodityTb = commodityTbService.getOrderCommodity(cid);
        return new Result(true, 200, "获取成功",commodityTb);
    }

    /** 
    * @Description: 删除商品
    * @Param: [cid] 
    * @return: com.sif.common.entity.result.Result 
    * @Author: shenyini
    * @Date: 2019/12/7 
    */ 
    @GetMapping("/api/delCommodity")
    public Result delCommodity(String cid){
        commodityTbService.delCommodity(cid);
        return new Result(true, 200, "删除成功");
    }

    /** 
    * @Description: 更新拍卖物 
    * @Param: [commodityTb] 
    * @return: com.sif.common.entity.result.Result 
    * @Author: xifujiang 
    * @Date: 2019/11/1 
    */ 
    public Result updateCommodity(CommodityTb commodityTb){
        return new Result();
    }


    /** 
    * @Description: 更新拍卖物状态 
    * @Param: [status] 
    * @return: com.sif.common.entity.result.Result 
    * @Author: xifujiang
    * @Date: 2019/11/1 
    */ 
    public Result updateCommodityStatus(String status){
        return new Result();
    }
    

    /**
    * @Description: 根据关键字获取商品列表
    * @Param: [ctype]
    * @return: com.sif.common.entity.result.Result
    * @Author: xifujiang
    * @Date: 2019/11/14
    */
    @GetMapping("/api/getGoodsList")
    public Result getGoodsList(String ctype){
        GoodsListResult good = new GoodsListResult();
        List<GoodsListResult> goodList = EntityUtils.castEntity(commodityTbRepository.findGoodList(ctype) , GoodsListResult.class, good);
        goodList.forEach( item -> {
           if(item.getImage()!="" && item.getImage().length() > 4 &&!"http".equals(item.getImage().substring(0,4))) {
               item.setImage(Constants.IMAGE_PATH + item.getImage());
           }
        });
        return new Result(true, 200, "添加成功", goodList);
    }

    @GetMapping("/api/getGoodDetail")
    public Result getGoodDetail(String cid){
        GoodDetail goodDetail = commodityTbService.getGood(cid);
        return new Result(true, 200, "获取成功", goodDetail);
    }

    /** 
    * @Description: 获取某用户发布的商品列表
    * @Param: [uid]
    * @return: com.sif.common.entity.result.Result 
    * @Author: xifujiang 
    * @Date: 2019/11/1 
    */
    @GetMapping("/api/getUserCommodity")
    public Result getUserCommodity(String uid){
        List<com.sif.action.pojo.CommodityTb> list = commodityTbRepository.findAllComodityByUid(uid);
        if(list.size() !=0 ) {
            list.forEach(item -> {
                // 查看当前时间
//            List<BigDecimal> nowPrices = commodityTbRepository.getNowPrice(item.getCid());
//            if(nowPrices.size()==0) {
//                item.setBidprice(item.getPrice());
//            }else {
//                System.err.println(nowPrices.get(0));
//                item.setBidprice(nowPrices.get(0));
//            }
                //更改图片路径
                if (null != item.getImage() && item.getImage().length()>=4 &&!"http".equals(item.getImage().substring(0, 4))) {
                    item.setImage(Constants.IMAGE_PATH + item.getImage());
                }
                //更改时间格式
                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                try {
                    item.setBeginTime(dateFormat.parse(dateFormat.format(item.getBeginTime())));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            });
        }
        return new Result(true,200,"查询成功", list);
    }


    @PostMapping("api/editCommodity")
    public Result editCommodity(@RequestBody ShopForm shopForm,String cid){
        commodityTbService.editCommodity(shopForm,cid);
        return new Result(true,200,"修改成功");
    }

    /**
    * @Description: 上传图片
    * @Param: [name]
    * @return: com.sif.common.entity.result.Result
    * @Author: xifujiang
    * @Date: 2019/11/2
    */
    @PostMapping("api/uploadImage")
    public Result uploadImage(MultipartFile file){
        //设置url
        String url = Constants.IMG_URL;
        String line = Constants.IMAGE_PATH;
        try {
            //D:\03MyProgram\项目\商品竞拍系统设计与实现\auction-Vue\static
           //流处理 先创建，再写入，关闭
           BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(new File(url + "\\" + file.getOriginalFilename())));
           bos.write(file.getBytes());
           bos.flush();
           bos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        //相应值
        FileForm fileForm = new FileForm();
        fileForm.setName(file.getOriginalFilename());
        fileForm.setUrl( line + file.getOriginalFilename());
        return new Result(true,200,"上传成功", fileForm);
    }

    @GetMapping("/api/findHistoryGoodList")
    public Result findHistoryGoodList(String cid){
        List<HistoryCommodity> list = commodityTbService.findHistoryGoodList(cid);
        return new Result(true,200,"获取成功", list);
    }
}



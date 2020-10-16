package com.sif.action.controller;


import com.sif.action.config.JsonUtil;
import com.sif.action.config.SpringHttpClient;
import com.sif.action.constants.Constants;
import com.sif.action.entity.CommodityCommentTb;
import com.sif.action.entity.CommodityTb;
import com.sif.action.entity.LogisticsTb;
import com.sif.action.pojo.CommodityPojoTb;
import com.sif.action.pojo.GoodDetail;
import com.sif.action.pojo.MyCommodityBean;
import com.sif.action.pojo.OrderAddress;
import com.sif.action.repository.CommodityTbRepository;
import com.sif.action.repository.OrderTbRepository;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @Autowired
    private  OrderTbRepository orderTbRepository;

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
    * @Description: 下架商品 
    * @Param: [cid] 
    * @return: com.sif.common.entity.result.Result 
    * @Author: shenyini
    * @Date: 2020/3/26 
    */ 
    @GetMapping("/api/takeOffCommodity")
    public Result takeOffCommodity(String cid) {
        commodityTbService.updateStatus(cid,8);
        return new Result();
    }
    /**
    * @Description: 重新上架
    * @Param: [cid]
    * @return: com.sif.common.entity.result.Result
    * @Author: shenyini
    * @Date: 2020/3/31
    */
    @GetMapping("/api/takeOnCommodity")
    public Result takeOnCommodity(String cid) {
        commodityTbService.updateStatus(cid,-1);
        return new Result();
    }

    /**
    * @Description: 添加物流信息，发货
    * @Param: [cid]
    * @return: com.sif.common.entity.result.Result
    * @Author: shenyini
    * @Date: 2020/4/1
    */
    @GetMapping("/api/transportCommodity")
    public Result transportCommodity(String logisticsid, String logisticstype, String cid, int addid, String info,String oid) {
        //记录物流信息
        LogisticsTb logisticsTb = new LogisticsTb();
        logisticsTb.setLogisticsid(logisticsid);
        logisticsTb.setLogisticstype(logisticstype);
        logisticsTb.setCid(cid);
        logisticsTb.setOid(oid);
        logisticsTb.setAddid(addid);
        logisticsTb.setInfo(info);
        commodityTbService.insertLogistics(logisticsTb);
        commodityTbService.updateStatus(logisticsTb.getCid(),5);
        return new Result(true,200,"发货成功");
    }

    /**
    * @Description: 显示物流
    * @Param: [cid]
    * @return: com.sif.common.entity.result.Result
    * @Author: shenyini
    * @Date: 2020/4/1
    */
    @GetMapping("/api/showTransport")
    public Result showTransport(String cid) {
        LogisticsTb logisticsTb = commodityTbService.selectLogistics(cid);
        Map<String, String> headerMap = new HashMap<String, String>();
        Map<String, String> paramMap = new HashMap<String, String>();
        paramMap.put("type","tnt");
        paramMap.put("postid",logisticsTb.getLogisticsid());
        String str = SpringHttpClient.post("https://www.kuaidi100.com/query", headerMap, paramMap);
        Map<String, Object> stringObjectMap = JsonUtil.JsonToMap(str);
        Object data = stringObjectMap.get("data");
        System.err.println(data.toString());
        return new Result(true,200,"显示物流成功", data.toString());
    }


    /**
    * @Description:  查询订单地址
    * @Param: [cid, uid]
    * @return: com.sif.common.entity.result.Result
    * @Author: shenyini
    * @Date: 2020/4/1
    */
    @GetMapping("/api/selectOrderAddress")
    public Result selectOrderAddress(String cid) {
        List<OrderAddress> orderAddresses = EntityUtils.castEntity(orderTbRepository.selectOrderAddress(cid), OrderAddress.class, new OrderAddress());
        System.out.println(orderAddresses.size());
        OrderAddress orderAddress = null;
        if(orderAddresses != null && orderAddresses.size() != 0) {
            orderAddress = orderAddresses.get(0);
        }
        System.out.println(orderAddress.toString());
        return new Result(true, 200, "查询订单地址成功", orderAddress);
    }

    /**
    * @Description: 取消订单
    * @Param: [cid]
    * @return: com.sif.common.entity.result.Result
    * @Author: shenyini
    * @Date: 2020/4/1
    */
    @GetMapping("/api/sellerCannalOrder")
    public Result sellerCannalOrder(String cid, String uid) {
        int credit = commodityTbService.sellerCannalOrder(cid, uid);
        return new Result(true,200,"取消订单成功",credit);
    }


    /**
    * @Description: 买家取消订单
    * @Param: [cid]
    * @return: com.sif.common.entity.result.Result
    * @Author: shenyini
    * @Date: 2020/4/3
    */
    @GetMapping("/api/buyerCannalOrder")
    public Result buyerCannalOrder(String cid, String uid) {
        int credit = commodityTbService.buyerCannalOrder(cid, uid);
        return new Result(true,200,"取消订单成功",credit);
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
    * @Description: 根据关键字获取商品列表
    * @Param: [ctype,current//页码,pageSize//每页数量]
    * @return: com.sif.common.entity.result.Result
    * @Author: xifujiang
    * @Date: 2019/11/14
    */
    @GetMapping("/api/getGoodsList")
    public Result getGoodsList(String ctype){
        List<GoodsListResult> goodList = EntityUtils.castEntity(commodityTbRepository.findGoodList(ctype) , GoodsListResult.class, new GoodsListResult());
        goodList.forEach( item -> {
           if(item.getImage()!="" && item.getImage().length() > 4 &&!"http".equals(item.getImage().substring(0,4))) {
               item.setImage(Constants.IMAGE_PATH + item.getImage());
           }
        });

        return new Result(true, 200, "", goodList);
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
        List<MyCommodityBean> list = EntityUtils.castEntity(commodityTbRepository.findAllComodityByUid(uid) , MyCommodityBean.class, new MyCommodityBean());

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

    @GetMapping("/api/deleteFavorite")
    public Result deleteFavorite(String uid, String cid){
        commodityTbService.deleteFavorite(uid,cid);
        return new Result(true,200,"删除成功");
    }

    @GetMapping("/api/addFavorite")
    public Result addFavorite(String uid, String cid) {
        boolean flag = commodityTbService.addFavorite(uid, cid);
        if (flag) {
            return new Result(true, 200, "添加成功");
        }
        else {
            return new Result(true, 400, "已收藏过");
        }
    }


    @GetMapping("/api/beReceipt")
    public Result beReceipt(String cid) {
        commodityTbService.updateStatus(cid, 6);
        return new Result(true, 200,"收货成功");
    }

    @PostMapping("/api/commit")
    public Result commit(@RequestBody CommitPojo commitPojo){
        commodityTbService.addComment(commitPojo);
        return new Result(true, 200, "评论");
    }

    @GetMapping("/api/queryHotCommodity")
    public Result queryHotCommodity(){
        List<ItemFour> itemFour = commodityTbService.queryHotCommodity();
        return new Result(true, 200, "热拍商品", itemFour);
    }

    @GetMapping("/api/queryRecommendCommodity")
    public Result queryRecommendCommodity(String uid) {
        List<ItemFour> itemFour = commodityTbService.queryRecommendCommodity(uid);
        return new Result(true, 200, "推荐商品", itemFour);
    }
}



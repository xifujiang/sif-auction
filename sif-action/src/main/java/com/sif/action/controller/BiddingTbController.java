package com.sif.action.controller;


import com.sif.action.entity.BiddingTb;
import com.sif.action.entity.MemberRuleTb;
import com.sif.action.result.HistoryBidding;
import com.sif.action.result.MyBidding;
import com.sif.action.service.BiddingTbService;
import com.sif.common.entity.result.Result;
import com.sif.common.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
public class BiddingTbController {

    @Autowired
    BiddingTbService biddingTbService;

    /**
    * @Description: 判断是否符合竞购要求
    * @Param: [cid]
    * @return: com.sif.common.entity.result.Result
    * @Author: xifujiang
    * @Date: 2019/11/11
    */
    public Result canBid(String uid){
        return new Result();
    }
    /**
    * @Description: 竞购拍卖物
    * @Param: [cid]
    * @return: com.sif.common.entity.result.Result
    * @Author: xifujiang
    * @Date: 2019/11/1
    */
    public Result bidCommodity(String cid){
        return new Result();
    }

    /**
    * @Description: 查看当前竞购物
    * @Param: [uid]
    * @return: com.sif.common.entity.result.Result
    * @Author: xifujiang
    * @Date: 2019/11/1
    */
    @GetMapping("/api/findMyBidding")
    public Result findMyBidding(String uid){
        List<MyBidding> myBiddingList  = biddingTbService.findMyBidding(uid);
        return new Result(true,200, "获取成功", myBiddingList);
    }


    /**
    * @Description: 是否是当前最高竞拍者
    * @Param: [cid, uid]
    * @return: com.sif.common.entity.result.Result
    * @Author: shenyini
    * @Date: 2019/12/11
    */
    @PostMapping("/api/isMaxBidder")
    public Result isMaxBidder(String cid, String uid){
        boolean flag = biddingTbService.isMaxBidder(cid, uid);
        return new Result(true,200, "获取成功", flag);
    }

    @PostMapping("/api/getBiddingStatus")
    public Result getBiddingStatus(String cid, String uid) {
        Result result = biddingTbService.getBiddingStatus(cid, uid);
        return result;
    }

    @PostMapping("/api/addBidding")
    public Result addBidding(BiddingTb bidding){
        System.err.println(bidding.toString());
        biddingTbService.insertBidding(bidding);
        return new Result(200);
    }

}


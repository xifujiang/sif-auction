package com.sif.action.service.impl;

import com.sif.action.entity.BiddingTb;
import com.sif.action.entity.CommodityTb;
import com.sif.action.entity.DepositPayTb;
import com.sif.action.mapper.BiddingTbMapper;
import com.sif.action.mapper.DepositPayTbMapper;
import com.sif.action.repository.BiddingTbRepository;
import com.sif.action.result.MyBidding;
import com.sif.action.service.BiddingTbService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sif.action.service.CommodityTbService;
import com.sif.common.entity.result.Result;
import com.sif.common.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Sif
 * @since 2019-10-26
 */
@Service
public class BiddingTbServiceImpl extends ServiceImpl<BiddingTbMapper, BiddingTb> implements BiddingTbService {

    @Autowired
    BiddingTbMapper biddingTbMapper;

    @Autowired
    BiddingTbRepository biddingTbRepository;

    @Autowired
    DepositPayTbMapper depositPayTbMapper;

    @Autowired
    CommodityTbService commodityTbService;

    @Transactional
    @Override
    public void updateBiddingStatus(String trade_no) {
        BiddingTb biddingTb = new BiddingTb();
        biddingTb.setBidid(trade_no);
        biddingTb.setStatu(1);
        biddingTbMapper.updateById(biddingTb);
    }

    @Transactional
    @Override
    public void updateBiddingStatus(String bid, Integer statu) {
        BiddingTb biddingTb = new BiddingTb();
        biddingTb.setBidid(bid);
        biddingTb.setStatu(statu);
        biddingTbMapper.updateById(biddingTb);
    }

    @Override
    public List<MyBidding> findMyBidding(String uid) {
        MyBidding myBidding = new MyBidding();
//        List<Object[]> myBidding1 = biddingTbRepository.findMyBidding(uid);
//        myBidding1.forEach(item -> {
//            System.err.println(item.toString());
//        });
        List<MyBidding> myBiddingList = EntityUtils.castEntity(biddingTbRepository.findMyBidding(uid),MyBidding.class, myBidding);
        if(myBiddingList.size() != 0) {
            myBiddingList.forEach(item ->{
                System.out.println(item.toString());
            });
        } else {
            System.err.println("没有结果哦");
        }
        return myBiddingList;
    }

    @Override
    public boolean isMaxBidder(String cid, String uid) {
        List<com.sif.action.pojo.BiddingTb> biddingList= biddingTbRepository.isMaxBidder(cid);
        if(biddingList.size() != 0) {
            if(uid.equals(biddingList.get(0).getUid())) {
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

    ///需要修改
    @Override
    public Result getBiddingStatus(String cid, String uid) {
        Map map = new HashMap();
        map.put("cid", cid);
        map.put("uid", uid);
        List<DepositPayTb> depositPayTbs = depositPayTbMapper.selectByMap(map);
        if(depositPayTbs.size() == 0) {
            return new Result(true, 200, "第一次竞拍该商品，支付押金即可参加竞拍，返回状态-1",1);
        }
        List<com.sif.action.pojo.BiddingTb> biddingList= biddingTbRepository.isMaxBidder(cid);
        if(biddingList.size() != 0) {
            if(uid.equals(biddingList.get(0).getUid())) {
                return new Result(true, 200, "您当前已是拍卖最高价，不得重复竞拍！请耐心等待。返回状态-2",2);
            } else {
                return new Result(true, 200, "您已支付过押金，可继续竞拍。返回状态-3",3);
            }
        }
        return new Result(true, 200, "第一次竞拍该商品，支付押金即可参加竞拍，返回状态-1",1);
    }

    @Transactional
    @Override
    public void insertBidding(BiddingTb bidding) {
        bidding.setBidtime(new Date());
        biddingTbMapper.insert(bidding);

        //更新当前价格
        CommodityTb commodityTb = new CommodityTb();
        commodityTb.setCid(bidding.getCid());
        commodityTb.setNowprice(bidding.getBidprice());
        commodityTbService.updateNowPrice(commodityTb);
    }

    @Override
    public com.sif.action.pojo.BiddingTb selectMaxBidding(String cid) {
        List<com.sif.action.pojo.BiddingTb> biddingTbs = biddingTbRepository.selectMaxBiddingByCid(cid);
        com.sif.action.pojo.BiddingTb biddingTb = new com.sif.action.pojo.BiddingTb();
        if(biddingTbs.size() != 0) {
            biddingTb = biddingTbs.get(0);
        }
        return biddingTb;
    }

}

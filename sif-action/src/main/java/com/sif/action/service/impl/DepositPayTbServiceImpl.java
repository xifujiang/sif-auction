package com.sif.action.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sif.action.entity.BiddingTb;
import com.sif.action.entity.CommodityTb;
import com.sif.action.entity.DepositPayTb;
import com.sif.action.entity.OrderTb;
import com.sif.action.mapper.BiddingTbMapper;
import com.sif.action.mapper.CommodityTbMapper;
import com.sif.action.mapper.DepositPayTbMapper;
import com.sif.action.pojo.DepositUserInfo;
import com.sif.action.pojo.OrderInfo;
import com.sif.action.repository.BiddingTbRepository;
import com.sif.action.service.CommodityTbService;
import com.sif.action.service.DepositPayTbService;
import com.sif.common.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
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
 * @since 2019-12-12
 */
@Service
public class DepositPayTbServiceImpl extends ServiceImpl<DepositPayTbMapper, DepositPayTb> implements DepositPayTbService {

    @Autowired
    DepositPayTbMapper depositPayTbMapper;

    @Autowired
    BiddingTbMapper biddingTbMapper;

    @Autowired
    BiddingTbRepository biddingTbRepository;

    @Autowired
    CommodityTbService commodityTbService;

    @Transactional
    @Override
    public void insertDeposit(OrderInfo orderInfo, String payway, boolean flag) {
        System.err.println(orderInfo.toString());
        DepositPayTb depositPayTb = new DepositPayTb();
        depositPayTb.setDid(orderInfo.getTradeNo());
        depositPayTb.setCid(orderInfo.getCid());
        depositPayTb.setUid(orderInfo.getUid());
        depositPayTb.setDeposit(new BigDecimal(orderInfo.getTotalPrice()));
        depositPayTb.setPayway(payway);
        depositPayTb.setPaytime(new Date());
        depositPayTb.setStatu(flag == true ?1 : 0);
        depositPayTbMapper.insert(depositPayTb);

        BiddingTb biddingTb = new BiddingTb();
        biddingTb.setBidid(orderInfo.getTradeNo());
        biddingTb.setCid(orderInfo.getCid());
        biddingTb.setUid(orderInfo.getUid());
        biddingTb.setBidprice(orderInfo.getBidprice());
        biddingTb.setBidtime(new Date());
        biddingTb.setStatu(flag == true ? 1 : 0); // 未交押金，设置为0
        biddingTbMapper.insert(biddingTb);
    }


    @Transactional
    @Override
    public void updateDepositStatus(String trade_no, String total_amount) {
        DepositPayTb depositPayTb = new DepositPayTb();
        depositPayTb.setDid(trade_no);
        depositPayTb.setStatu(1);
        depositPayTbMapper.updateById(depositPayTb);

        //修改竞购状态为正常状态
        BiddingTb biddingTb = new BiddingTb();
        biddingTb.setBidid(trade_no);
        biddingTb.setStatu(1);
        biddingTbMapper.updateById(biddingTb);

        DepositPayTb depositPayTb1 = depositPayTbMapper.selectById(trade_no);
        String cid = depositPayTb1.getCid();
//        Map map = new HashMap();
//        map.put("bidid", trade_no);
//        List<BiddingTb> biddingTbs = biddingTbMapper.selectByMap(map);
//        if(biddingTbs != null && biddingTbs.size() != 0) {
//            BiddingTb biddingTb1 = biddingTbs.get(0);
            //更新当前价格
            CommodityTb commodityTb = new CommodityTb();
            commodityTb.setCid(cid);
            commodityTb.setNowprice(new BigDecimal(total_amount));
            commodityTb.setStatu(2); //状态变成 2
            commodityTbService.updateNowPrice(commodityTb);
//        }
    }

    @Override
    public List<DepositPayTb> selectDepositPayByCid(String cid) {
        Map map = new HashMap ();
        map.put("cid", cid);
        List<DepositPayTb> depositPayTbs = depositPayTbMapper.selectByMap(map);
        return depositPayTbs;
    }

    @Override
    public List<DepositUserInfo> selectDepositUserByCid(String cid) {
        DepositUserInfo depositUserInfo = new DepositUserInfo();
        List<DepositUserInfo> list = EntityUtils.castEntity(biddingTbRepository.selectDepositUserByCid(cid), DepositUserInfo.class, depositUserInfo);
        return list;
    }
}

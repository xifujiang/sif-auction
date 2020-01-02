package com.sif.action.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sif.action.entity.*;
import com.sif.action.mapper.PackageBillsTbMapper;
import com.sif.action.mapper.UserPackageTbMapper;
import com.sif.action.pojo.DepositUserInfo;
import com.sif.action.pojo.OrderInfo;
import com.sif.action.repository.PackageBillsTbRepository;
import com.sif.action.repository.UserPackageTbRepository;
import com.sif.action.service.PackageBillsTbService;
import com.sif.action.service.WebSocketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Sif
 * @since 2019-12-13
 */
@Service
public class PackageBillsTbServiceImpl extends ServiceImpl<PackageBillsTbMapper, PackageBillsTb> implements PackageBillsTbService {


    @Autowired
    PackageBillsTbMapper packageBillsTbMapper;

    @Autowired
    UserPackageTbMapper userPackageTbMapper;

    @Autowired
    UserPackageTbRepository userPackageTbRepository;

    @Autowired
    PackageBillsTbRepository packageBillsTbRepository;

    @Autowired
    WebSocketService webSocketService;

    @Transactional
    @Override
    public void insertPackageBills(OrderInfo orderInfo, String alipay, boolean flag) {
        PackageBillsTb packageBillsTb = new PackageBillsTb();
        packageBillsTb.setBillid(orderInfo.getTradeNo());
        packageBillsTb.setUid(orderInfo.getUid());
        packageBillsTb.setPrice(new BigDecimal(orderInfo.getTotalPrice()));
        packageBillsTb.setType("充值");
        packageBillsTb.setTime(new Date());
        packageBillsTb.setInfo(orderInfo.getNature());

        packageBillsTbMapper.insert(packageBillsTb);
    }

    @Transactional
    @Override
    public void updateBillsStatus(String trade_no) {
        //改变订单状态
        PackageBillsTb packageBillsTb = new PackageBillsTb();
        packageBillsTb.setBillid(trade_no);
        packageBillsTb.setStatu(1);
        packageBillsTbMapper.updateById(packageBillsTb);

        // 修改用户钱包
        PackageBillsTb packageBillsTb1 = packageBillsTbMapper.selectById(trade_no);
        Map map = new HashMap<>();
        map.put("uid",packageBillsTb1.getUid());
        List<UserPackageTb> userPackageTbList = userPackageTbMapper.selectByMap(map);
        if(userPackageTbList.size() != 0) {
            UserPackageTb userPackageTb = new UserPackageTb();
            userPackageTb.setPid(userPackageTbList.get(0).getPid());
            userPackageTb.setUid(userPackageTbList.get(0).getUid());
            userPackageTb.setMoney(userPackageTbList.get(0).getMoney().add(packageBillsTb1.getPrice()));
            userPackageTbMapper.updateById(userPackageTb);
        }
    }

    @Transactional
    @Override
    public void returnMoney(String uid, List<DepositPayTb> depositPayTbs, BigDecimal deposit) {
        // 用户账单列表
        List<com.sif.action.pojo.PackageBillsTb> packageBillsTbs = new ArrayList<>();
        // 用户钱包表
        List<com.sif.action.pojo.UserPackageTb> userPackageTbs = new ArrayList<>();
        depositPayTbs.forEach(item ->{
            if(!uid.equals(item.getUid())) {
                //退还押金
                com.sif.action.pojo.PackageBillsTb packageBillsTb = new com.sif.action.pojo.PackageBillsTb();
                packageBillsTb.setBillid(String.valueOf(System.currentTimeMillis()));
                packageBillsTb.setUid(item.getUid());
                packageBillsTb.setPrice(deposit);
                packageBillsTb.setTime(new Date());
                packageBillsTb.setType("退还押金");
                packageBillsTb.setInfo("竞拍失败，退还押金");
                packageBillsTb.setStatu(1);
                packageBillsTbs.add(packageBillsTb);

                //给竞拍失败者发送信息
                String message = "您竞拍的商品竞拍已结束，很抱歉您未竞拍到该商品";
                try {
                    webSocketService.sendToUserByUid(item.getUid(),message);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                Map map = new HashMap();
                map.put("uid", item.getUid());
                List<com.sif.action.pojo.UserPackageTb> userPackageTbs1 = userPackageTbMapper.selectByMap(map);
                if(userPackageTbs1.size()!=0) {
                    userPackageTbs.add(userPackageTbs1.get(0));
                }
            }
        });
        //钱包记录更新
        packageBillsTbRepository.saveAll(packageBillsTbs);
        userPackageTbs.forEach(item->{
            item.setMoney(item.getMoney().add(deposit));
        });
        //用户钱包更新
        userPackageTbRepository.saveAll(userPackageTbs);

    }


}

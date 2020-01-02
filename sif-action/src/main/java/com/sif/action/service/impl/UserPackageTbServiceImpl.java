package com.sif.action.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sif.action.entity.BiddingTb;
import com.sif.action.entity.DepositPayTb;
import com.sif.action.entity.PackageBillsTb;
import com.sif.action.entity.UserPackageTb;
import com.sif.action.mapper.BiddingTbMapper;
import com.sif.action.mapper.DepositPayTbMapper;
import com.sif.action.mapper.PackageBillsTbMapper;
import com.sif.action.mapper.UserPackageTbMapper;
import com.sif.action.pojo.OrderInfo;
import com.sif.action.service.UserPackageTbService;
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
 * @since 2019-12-13
 */
@Service
public class UserPackageTbServiceImpl extends ServiceImpl<UserPackageTbMapper, UserPackageTb> implements UserPackageTbService {

    @Autowired
    UserPackageTbMapper userPackageTbMapper;

    @Autowired
    PackageBillsTbMapper packageBillsTbMapper;

    @Autowired
    DepositPayTbMapper depositPayTbMapper;

    @Autowired
    BiddingTbMapper biddingTbMapper;

    @Override
    @Transactional
    public UserPackageTb getMoney(String uid) {
        Map map = new HashMap();
        map.put("uid",uid);
        BigDecimal money = new BigDecimal(0);
        List<UserPackageTb> list = userPackageTbMapper.selectByMap(map);
        UserPackageTb userPackageTb = new UserPackageTb();
        if(list.size() != 0) {
            userPackageTb = list.get(0);
        }
        return userPackageTb;
    }

    @Transactional
    @Override
    public void changePackageMoney(OrderInfo orderInfo) {
        System.err.println(orderInfo.toString());
        PackageBillsTb packageBillsTb = new PackageBillsTb();
        packageBillsTb.setBillid(orderInfo.getTradeNo());
        packageBillsTb.setUid(orderInfo.getUid());
        packageBillsTb.setPrice(new BigDecimal(orderInfo.getTotalPrice()));
        packageBillsTb.setTime(new Date());
        packageBillsTb.setInfo(orderInfo.getNature());
        packageBillsTb.setType("支出");
        packageBillsTbMapper.insert(packageBillsTb);

        //用户钱包金额修改
        Map map = new HashMap();
        map.put("uid",orderInfo.getUid());
        BigDecimal money = new BigDecimal(0);
        List<UserPackageTb> list = userPackageTbMapper.selectByMap(map);
        if(list.size() != 0) {
            money = list.get(0).getMoney();
        }
        UserPackageTb userPackageTb = new UserPackageTb();
        userPackageTb.setPid(list.get(0).getPid());
        userPackageTb.setUid(orderInfo.getUid());
        userPackageTb.setMoney(money.subtract(new BigDecimal(orderInfo.getTotalPrice())));
        userPackageTbMapper.updateById(userPackageTb);
    }
}

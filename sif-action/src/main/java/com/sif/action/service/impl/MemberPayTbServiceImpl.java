package com.sif.action.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sif.action.entity.MemberPayTb;
import com.sif.action.entity.UserMemberTb;
import com.sif.action.mapper.MemberPayTbMapper;
import com.sif.action.mapper.UserMemberTbMapper;
import com.sif.action.pojo.OrderInfo;
import com.sif.action.service.MemberPayTbService;
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
 * @since 2019-12-09
 */
@Service
public class MemberPayTbServiceImpl extends ServiceImpl<MemberPayTbMapper, MemberPayTb> implements MemberPayTbService {

    @Autowired
    private MemberPayTbMapper memberPayTbMapper;

    @Autowired
    private UserMemberTbMapper userMemberTbMapper;

    @Transactional
    @Override
    public void insertMemberPay(OrderInfo orderInfo, String payway,boolean flag) {
        MemberPayTb memberPayTb = new MemberPayTb();
        memberPayTb.setMid(orderInfo.getTradeNo());
        memberPayTb.setUid(orderInfo.getUid());
        memberPayTb.setMemberid(orderInfo.getMemberid());
        memberPayTb.setPrice(new BigDecimal(orderInfo.getTotalPrice()));
        memberPayTb.setPaytime(new Date());
        memberPayTb.setPayway(payway);
        memberPayTb.setStatu(flag == true ?1 :0);
        memberPayTbMapper.insert(memberPayTb);
    }

    @Transactional
    @Override
    public void updateMemberStatus(String trade_no) {
        //改变订单状态
        MemberPayTb memberPayTb = new MemberPayTb();
        memberPayTb.setMid(trade_no);
        memberPayTb.setStatu(1);
        memberPayTbMapper.updateById(memberPayTb);

        // 修改用户会员等级
        MemberPayTb memberPayTb1 = memberPayTbMapper.selectById(trade_no);
        Map map = new HashMap<>();
        map.put("uid",memberPayTb1.getUid());
        List<UserMemberTb> userMemberTbList = userMemberTbMapper.selectByMap(map);
        if(userMemberTbList.size() != 0) {
            UserMemberTb userMemberTb = new UserMemberTb();
            userMemberTb.setMid(userMemberTbList.get(0).getMid());
            userMemberTb.setMemberid(memberPayTb1.getMemberid());
            userMemberTbMapper.updateById(userMemberTb);
        }
    }
}

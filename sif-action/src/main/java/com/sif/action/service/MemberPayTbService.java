package com.sif.action.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sif.action.entity.MemberPayTb;
import com.sif.action.pojo.OrderInfo;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Sif
 * @since 2019-12-09
 */
public interface MemberPayTbService extends IService<MemberPayTb> {

    void insertMemberPay(OrderInfo orderInfo, String payway,boolean flag);

    void updateMemberStatus(String trade_no);
}

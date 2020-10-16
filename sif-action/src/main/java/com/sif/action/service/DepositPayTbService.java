package com.sif.action.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sif.action.entity.DepositPayTb;
import com.sif.action.pojo.DepositUserInfo;
import com.sif.action.pojo.OrderInfo;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Sif
 * @since 2019-12-12
 */
public interface DepositPayTbService extends IService<DepositPayTb> {

    void insertDeposit(OrderInfo orderInfo, String payway, boolean flag);

    void updateDepositStatus(String trade_no, String total_amount);

    /*根据cid查押金记录*/
    List<DepositPayTb> selectDepositPayByCid(String cid);

    List<DepositUserInfo> selectDepositUserByCid(String cid);
}

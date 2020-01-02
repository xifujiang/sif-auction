package com.sif.action.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sif.action.entity.DepositPayTb;
import com.sif.action.entity.PackageBillsTb;
import com.sif.action.pojo.DepositUserInfo;
import com.sif.action.pojo.OrderInfo;

import java.math.BigDecimal;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Sif
 * @since 2019-12-13
 */
public interface PackageBillsTbService extends IService<PackageBillsTb> {

    /** 
    * @Description: 钱包充值 
    * @Param: [orderInfo, alipay, b] 
    * @return: void 
    * @Author: shenyini
    * @Date: 2019/12/16 
    */ 
    void insertPackageBills(OrderInfo orderInfo, String alipay, boolean b);

    /** 
    * @Description: 修改支付状态 
    * @Param: [trade_no] 
    * @return: void 
    * @Author: shenyini
    * @Date: 2019/12/16 
    */ 
    void updateBillsStatus(String trade_no);

    /**
    * @Description:  退还除竞拍最高价的所有人的押金
    * @Param: [cid, depositPayTbs, deposit]
    * @return: void
    * @Author: shenyini
    * @Date: 2019/12/17
    */
    void returnMoney(String uid, List<DepositPayTb> depositPayTbs, BigDecimal deposit);
}

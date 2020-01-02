package com.sif.action.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sif.action.entity.UserPackageTb;
import com.sif.action.pojo.OrderInfo;

import java.math.BigDecimal;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Sif
 * @since 2019-12-13
 */
public interface UserPackageTbService extends IService<UserPackageTb> {

    UserPackageTb getMoney(String uid);

//    void changeMoney(String billid, String uid,String cid,  BigDecimal price, BigDecimal bidprice,String info);

    void changePackageMoney(OrderInfo orderInfo);
}

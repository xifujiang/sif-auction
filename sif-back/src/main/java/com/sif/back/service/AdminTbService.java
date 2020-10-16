package com.sif.back.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sif.back.entity.AdminTb;
import com.sif.back.entity.CommodityTb;
import com.sif.back.pojo.CommodityResult;
import com.sif.back.pojo.Count;
import com.sif.back.pojo.Type;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Sif
 * @since 2019-11-11
 */
public interface AdminTbService extends IService<AdminTb> {

    AdminTb login(AdminTb adminTb);

    Count getCount();

    List<CommodityResult> getNotDealCommodity();

    void changeCommodityStatus(String cid, Integer statu);


}

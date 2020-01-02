package com.sif.action.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sif.action.entity.CommodityTypeTb;
import com.sif.action.result.CommodityTypeResult;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Sif
 * @since 2019-10-26
 */
public interface CommodityTypeTbService extends IService<CommodityTypeTb> {

    public List<CommodityTypeResult> getByTypeId(Integer typeId);

    List<CommodityTypeTb> getAllOneType();


    public List<CommodityTypeTb> selectByParentid(Integer typeid);

    public CommodityTypeTb
    selectByName(String name);
}

package com.sif.back.service;

import com.sif.back.entity.CommodityTypeTb;
import com.sif.back.pojo.Type;

import java.util.List;

public interface CommodityTypeService {
    List<Type> getCommodityType();

    void addCommodityType(String type, String parent);

    List<CommodityTypeTb> getOneCommodityType(Integer parent);

    List<CommodityTypeTb> getTwoCommodityType(String parentName);

    int editCommodityType(Integer typeid, String name1, String name2);

    int deleteCommodityType(Integer typeid);

    int addSunCommodityType(String name, Integer parentid);
}

package com.sif.back.service.impl;

import com.sif.back.entity.CommodityTb;
import com.sif.back.entity.CommodityTypeTb;
import com.sif.back.mapper.CommodityTypeTbMapper;
import com.sif.back.pojo.Type;
import com.sif.back.repository.AdminRepository;
import com.sif.back.service.CommodityTypeService;
import com.sif.common.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @program: sif-auction
 * @description:
 * @author: shenyini
 * @create: 2020-02-01 19:21
 **/
@Service
public class CommodityTypeServiceImpl implements CommodityTypeService {
    @Autowired
    CommodityTypeTbMapper commodityTypeTbMapper;
    @Autowired
    AdminRepository adminRepository;

    @Override
    public List<Type> getCommodityType() {
        List<Type> types = EntityUtils.castEntity(adminRepository.getCommodityType(), Type.class, new Type());
        return types;
    }

    @Override
    public void addCommodityType(String type, String parent) {
        if("/".equals(parent)) {
            CommodityTypeTb commodityTypeTb = new CommodityTypeTb();
            commodityTypeTb.setParentid(0);
            commodityTypeTb.setName(type);
            int insert = commodityTypeTbMapper.insert(commodityTypeTb);
        }else {
            Map map = new HashMap();
            map.put("name", parent);
            List<CommodityTypeTb> list = commodityTypeTbMapper.selectByMap(map);
            if (list.size() != 0) {
                CommodityTypeTb commodityTypeTb = list.get(0);
                CommodityTypeTb newCommodityType = new CommodityTypeTb();
                newCommodityType.setName(type);
                newCommodityType.setParentid(commodityTypeTb.getTypeid());
                int insert = commodityTypeTbMapper.insert(newCommodityType);
            }
        }
    }

    @Override
    public List<CommodityTypeTb> getOneCommodityType(Integer parentid){
        Map map = new HashMap();
        map.put("parentid",parentid);
        List<CommodityTypeTb> list = commodityTypeTbMapper.selectByMap(map);
        return list;
    }

    @Override
    public List<CommodityTypeTb> getTwoCommodityType(String parentName) {
        Map map = new HashMap();
        map.put("name",parentName);
        List<CommodityTypeTb> oneType = commodityTypeTbMapper.selectByMap(map);
        if(oneType.size()!=0) {
            Integer typeid = oneType.get(0).getTypeid();
            Map map1 = new HashMap();
            map1.put("parentid",typeid);
            List<CommodityTypeTb> list = commodityTypeTbMapper.selectByMap(map1);
            return list;
        }
        return null;
    }

    @Override
    public int editCommodityType(Integer typeid, String name1, String name2) {
        if(name2 == "/"){
            CommodityTypeTb commodityTypeTb = new CommodityTypeTb();
            commodityTypeTb.setTypeid(typeid);
            commodityTypeTb.setName(name1);
            commodityTypeTbMapper.updateById(commodityTypeTb);
        } else {
            Map map = new HashMap();
            map.put("name",name2);
            List<CommodityTypeTb> list = commodityTypeTbMapper.selectByMap(map);
            if(list.size() != 0) {
                int parentid = list.get(0).getParentid();
                CommodityTypeTb commodityTypeTb = new CommodityTypeTb();
                commodityTypeTb.setTypeid(typeid);
                commodityTypeTb.setName(name1);
                commodityTypeTb.setParentid(parentid);
                commodityTypeTbMapper.updateById(commodityTypeTb);
            }
        }
        return 0;
    }

    @Override
    public int deleteCommodityType(Integer typeid) {
        Map map = new HashMap();
        map.put("parentid", typeid);
        //查询删除数据
        CommodityTypeTb commodityTypeTb = commodityTypeTbMapper.selectById(typeid);
        if(commodityTypeTb != null && commodityTypeTb.getParentid() == 0) {
            List<CommodityTypeTb> list = commodityTypeTbMapper.selectByMap(map);
            list.forEach(item->{
                Integer parentid = item.getTypeid();
                Map map2 = new HashMap();
                map2.put("parentid",parentid);
                //删除下下级
                commodityTypeTbMapper.deleteByMap(map2);
            });
        }
        //删除下级
        commodityTypeTbMapper.deleteByMap(map);
        //删除本身
        commodityTypeTbMapper.deleteById(typeid);
        return 0;
    }

    @Override
    public int addSunCommodityType(String name, Integer parentid) {
        CommodityTypeTb commodityTypeTb = new CommodityTypeTb();
        commodityTypeTb.setName(name);
        commodityTypeTb.setParentid(parentid);
        commodityTypeTbMapper.insert(commodityTypeTb);
        return 0;
    }
}
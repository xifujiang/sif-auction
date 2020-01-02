package com.sif.action.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sif.action.entity.CommodityTypeTb;
import com.sif.action.mapper.CommodityTypeTbMapper;
import com.sif.action.result.CommodityTTResult;
import com.sif.action.result.CommodityTypeResult;
import com.sif.action.service.CommodityTypeTbService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Sif
 * @since 2019-10-26
 */
@Service
public class CommodityTypeTbServiceImpl extends ServiceImpl<CommodityTypeTbMapper, CommodityTypeTb> implements CommodityTypeTbService {

    @Autowired
    RedisTemplate redisTemplate;

    @Autowired
    CommodityTypeTbMapper commodityTypeTbMapper;
    /**
    * @Description:  一级取出二、三级商品
    * @Param: [typeId]
    * @return: java.util.List<com.sif.action.result.CommodityTypeResult>
    * @Author: xifujiang
    * @Date: 2019/10/26
    */
    @Override
    public List<CommodityTypeResult> getByTypeId(Integer typeId) {
        List<CommodityTypeResult> list = new ArrayList<>();
        String jsonString  = (String)redisTemplate.opsForValue().get(String.valueOf(typeId)+"9999");
        /// redis里能取到值
        if(jsonString != null){
            list = JSON.parseArray(jsonString, CommodityTypeResult.class);
            return list;
        }
        //未取到值
        QueryWrapper<CommodityTypeTb> queryWrapper_1 = new QueryWrapper<>();
        queryWrapper_1.eq("typeid", typeId);
        //这个查询的结果是  1	二手名表	0
        List<CommodityTypeTb> commodityTypeTbList = baseMapper.selectList(queryWrapper_1);
        QueryWrapper<CommodityTypeTb> queryWrapper_2 = new QueryWrapper<>();
        //对commodityTypeTbList 做遍历
        List<CommodityTypeResult> finalList = list;
        commodityTypeTbList.forEach(commodityType1 ->{
            //总封装
            CommodityTypeResult commodityTypeResult = new CommodityTypeResult();
            //二级封装
            List<CommodityTTResult> ttList = new ArrayList<>();
            //匹配parentid是该类型的
            queryWrapper_2.eq("parentid", commodityType1.getTypeid());
            //  查出二级 列表
            List<CommodityTypeTb> list_2 = baseMapper.selectList(queryWrapper_2);
            //查三级列表
            list_2.forEach(commodityType2 ->{
                QueryWrapper<CommodityTypeTb> queryWrapper_3 = new QueryWrapper<>();
                queryWrapper_3.eq("parentid", commodityType2.getTypeid());
                List<CommodityTypeTb> list_3 = baseMapper.selectList(queryWrapper_3);
                //建立 二三级的商品列表
                CommodityTTResult commodityTTResult = new CommodityTTResult();
                commodityTTResult.setName_2(commodityType2.getName());
                List<String> name_3List = new ArrayList<>();
                list_3.forEach(item ->{
                    name_3List.add(item.getName());
                });
                commodityTTResult.setName_3(name_3List);
                ttList.add(commodityTTResult);
            });
            commodityTypeResult.setName_1(commodityType1.getName());
            commodityTypeResult.setTwoNav(ttList);
            finalList.add(commodityTypeResult);
        });
        redisTemplate.opsForValue().set(typeId.intValue() + "9999", JSON.toJSONString(finalList));
        return finalList;
    }

    /**
    * @Description: 取一级商品
    * @Param: []
    * @return: java.util.List<com.sif.action.entity.CommodityTypeTb>
    * @Author: xifujiang
    * @Date: 2019/10/26
    */
    @Override
    public List<CommodityTypeTb> getAllOneType() {
        List<CommodityTypeTb> commodityTypeTbList = null;

        String jsonString  = (String)redisTemplate.opsForValue().get("onetype");
        if(jsonString != null){
            commodityTypeTbList = JSON.parseArray(jsonString, CommodityTypeTb.class);
        }else {
            QueryWrapper<CommodityTypeTb> queryWrapper_1 = new QueryWrapper<>();
            queryWrapper_1.eq("parentid", Integer.valueOf(0));
            commodityTypeTbList = baseMapper.selectList(queryWrapper_1);

            redisTemplate.opsForValue().set("onetype", JSON.toJSONString(commodityTypeTbList));
        }

        return commodityTypeTbList;
    }


    /**
    * @Description: 通过parentid查找类别列表
    * @Param: [parentid]
    * @return: java.util.List<com.sif.action.entity.CommodityTypeTb>
    * @Author: xifujiang
    * @Date: 2019/11/3
    */
    @Override
    public List<CommodityTypeTb> selectByParentid(Integer typeid) {
        List<CommodityTypeTb> list;

        String jsonString  = (String)redisTemplate.opsForValue().get("1111"+typeid);
        /// redis里能取到值
        if(jsonString != null){
            list = JSON.parseArray(jsonString, CommodityTypeTb.class);
            return list;
        }

        Map map = new HashMap();
        map.put("parentid",typeid);
        list = commodityTypeTbMapper.selectByMap(map);
        redisTemplate.opsForValue().set("1111"+typeid, JSON.toJSONString(list));
        return list;

    }

    @Override
    public CommodityTypeTb selectByName(String name) {
        Map map = new HashMap();
        map.put("name",name);
        return (CommodityTypeTb) commodityTypeTbMapper.selectByMap(map).get(0);
    }
}

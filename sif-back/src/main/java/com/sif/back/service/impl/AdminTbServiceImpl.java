package com.sif.back.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.sif.back.entity.AdminTb;
import com.sif.back.entity.CommodityTb;
import com.sif.back.entity.CommodityTypeTb;
import com.sif.back.mapper.AdminTbMapper;
import com.sif.back.mapper.CommodityTbMapper;
import com.sif.back.mapper.CommodityTypeTbMapper;
import com.sif.back.pojo.CommodityResult;
import com.sif.back.pojo.Count;
import com.sif.back.pojo.Type;
import com.sif.back.repository.AdminRepository;
import com.sif.back.service.AdminTbService;
import com.sif.common.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Sif
 * @since 2019-11-11
 */
@Service
public class AdminTbServiceImpl extends ServiceImpl<AdminTbMapper, AdminTb> implements AdminTbService {

    @Autowired
    private AdminTbMapper adminTbMapper;
    @Autowired
    private CommodityTbMapper commodityTbMapper;
    @Autowired
    private CommodityTypeTbMapper commodityTypeTbMapper;
    @Autowired
    private AdminRepository adminRepository;

    @Override
    public AdminTb login(AdminTb adminTb) {
        Map map = new HashMap();
        map.put("name", adminTb.getName());
        map.put("password", adminTb.getPassword());
        List<AdminTb> list = adminTbMapper.selectByMap(map);
        if(list.size() != 0) {
            return list.get(0);
        }
        return null;
    }

    @Override
    public Count getCount() {
        List<Count> list = EntityUtils.castEntity(adminRepository.getCount(), Count.class, new Count());
        if(list.size()!=0) {
            return list.get(0);
        }
        return null;
    }

    @Override
    public List<CommodityResult> getNotDealCommodity() {
        List<CommodityResult> list = EntityUtils.castEntity(adminRepository.getNotDealCommodity(), CommodityResult.class, new CommodityResult());
        return list;
    }

    @Override
    public void changeCommodityStatus(String cid, Integer statu) {
        CommodityTb commodityTb = new CommodityTb();
        commodityTb.setCid(cid);
        commodityTb.setStatu(statu);
        int i = commodityTbMapper.updateById(commodityTb);
    }
}

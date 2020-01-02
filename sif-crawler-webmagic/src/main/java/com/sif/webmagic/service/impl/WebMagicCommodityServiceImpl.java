package com.sif.webmagic.service.impl;

import com.sif.webmagic.dao.WebMagicCommodityDao;
import com.sif.webmagic.pojo.WebMagicCommodity;
import com.sif.webmagic.service.WebMagicCommodityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: sif-auction
 * @description:
 * @author: xifujiang
 * @create: 2019-12-03 10:28
 **/
@Service
public class WebMagicCommodityServiceImpl implements WebMagicCommodityService {
    @Autowired
    private WebMagicCommodityDao webMagicCommodityDao;
    @Override
    @Transactional
    public void save(WebMagicCommodity webMagicCommodity) {
        //根据url和商品编号查询数据
        WebMagicCommodity param = new WebMagicCommodity();
        param.setUrl(webMagicCommodity.getUrl());
        param.setCnum(webMagicCommodity.getCnum());

        //执行查询
        List<WebMagicCommodity> list = this.findWebMagicCommodity(param);
        //判断查询结果是否为空
        if(list.size() == 0 && webMagicCommodity != null){
            //如果查询结果为空，则新增数据库。
            webMagicCommodityDao.saveAndFlush(webMagicCommodity);
        }
    }

    @Override
    public List<WebMagicCommodity> findWebMagicCommodity(WebMagicCommodity webMagicCommodity) {
        //设置查询条件
        Example example = Example.of(webMagicCommodity);

        List list = new ArrayList();
//        list = webMagicCommodityDao.findAll(example);

        return list;
    }
}

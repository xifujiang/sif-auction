package com.sif.webmagic.service;

import com.sif.webmagic.pojo.WebMagicCommodity;

import java.util.List;

public interface WebMagicCommodityService {
    /**
    * @Description:保存工作信息
    * @Param:
    * @return:
    * @Author: xifujiang
    * @Date: 2019/12/3
    */
    public void save(WebMagicCommodity webMagicCommodity);

    
    /** 
    * @Description: 根据条件查询工作信息 
    * @Param: [webMagicCommodity] 
    * @return: java.util.List<com.sif.webmagic.pojo.WebMagicCommodity> 
    * @Author: xifujiang 
    * @Date: 2019/12/3 
    */ 
    public List<WebMagicCommodity> findWebMagicCommodity(WebMagicCommodity webMagicCommodity);
}

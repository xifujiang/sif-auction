package com.sif.webmagic.task;

import com.sif.webmagic.pojo.WebMagicCommodity;
import com.sif.webmagic.service.WebMagicCommodityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

/**
 * @program: sif-auction
 * @description:
 * @author: xifujiang
 * @create: 2019-12-03 15:40
 **/
@Component
public class SpringDataPipeline implements Pipeline {
    @Autowired
    private WebMagicCommodityService webMagicCommodityService;

    @Override
    public void process(ResultItems resultItems, Task task) {
        // 获取封装好的商品对象
        WebMagicCommodity webMagicCommodity = resultItems.get("webMagicCommodity");
        // 判断数据是否不为空
        if(webMagicCommodity != null){
            // 如果不为空把数据保存到数据库中
            webMagicCommodityService.save(webMagicCommodity);
        }
    }
}

package com.sif.action.result;

import com.sif.action.pojo.History;
import com.sif.action.pojo.OtherDetail;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * @program: sif-auction
 * @description: 商品细节
 * @author: xifujiang
 * @create: 2019-11-14 18:45
 **/
@Data
public class GoodDetailResult {
    private String cid;
    private BigDecimal price;
    private BigDecimal addprice;
    private BigDecimal nowprice;
    private String[] images;
    private String cname;
    private String[] typeid;
    private String seller;
    private Integer count;
    private Integer score;
    private List<History> history;
    private String des;
    private List<OtherDetail> param1;
    private List<OtherDetail> param2;
    private Integer status;
}

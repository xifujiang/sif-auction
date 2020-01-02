package com.sif.action.service.impl;

import com.sif.action.constants.Constants;
import com.sif.action.pojo.GoodDetail;
import com.sif.action.entity.*;
import com.sif.action.mapper.*;
import com.sif.action.pojo.History;
import com.sif.action.pojo.OtherDetail;
import com.sif.action.repository.BiddingTbRepository;
import com.sif.action.repository.CommodityTbRepository;
import com.sif.action.result.GoodDetailResult;
import com.sif.action.result.HistoryBidding;
import com.sif.action.result.HistoryCommodity;
import com.sif.action.result.ShopForm;
import com.sif.action.service.CommodityTbService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sif.common.util.EntityUtils;
import com.sif.common.util.IdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author Sif
 * @since 2019-10-26
 */
@Service
public class CommodityTbServiceImpl extends ServiceImpl<CommodityTbMapper, CommodityTb> implements CommodityTbService {

    @Autowired
    private IdWorker idWorker;

    @Autowired
    private UserCommodityTbMapper userCommodityTbMapper;

    @Autowired
    private CommodityTbMapper commodityTbMapper;

    @Autowired
    private UserTbMapper userTbMapper;

    @Autowired
    private BiddingTbMapper biddingTbMapper;

    @Autowired
    private UserCreditTbMapper userCreditTbMapper;

    @Autowired
    private OrderTbMapper orderTbMapper;

    @Autowired
    CommodityTbRepository commodityTbRepository;

    @Autowired
    BiddingTbRepository biddingTbRepository;

    /**
    * @Description: 发布商品 
    * @Param: [shopForm, uid] 
    * @return: void 
    * @Author: shenyini
    * @Date: 2020/1/1 
    */ 
    @Transactional
    @Override
    public void addCommodity(ShopForm shopForm, String uid) {
        System.err.println(shopForm.getPart_time());
        String beginDate = ((List<String>) shopForm.getPart_time()).get(0).replace("Z", " UTC");
        String endDate = ((List<String>) shopForm.getPart_time()).get(1).replace("Z", " UTC");
        SimpleDateFormat df_1 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS Z");
        SimpleDateFormat df_2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println("beginDate:" + beginDate);
        System.out.println("endDate" + endDate);
        try {
            String cid = idWorker.nextId()+"";
            CommodityTb commodityTb = new CommodityTb();
            commodityTb.setCid(cid);
            commodityTb.setCname(shopForm.getCname());
            commodityTb.setPrice(shopForm.getPrice());
            commodityTb.setAddprice(shopForm.getAddprice());
            commodityTb.setImage(shopForm.getImage());
            commodityTb.setImages(shopForm.getImages());
            commodityTb.setDes(shopForm.getDesc());
            commodityTb.setTypeId(shopForm.getType_id());
            commodityTb.setBrandName(shopForm.getBrand_name());
            commodityTb.setCreateTime(new Date());
            commodityTb.setUpdateTime(new Date());
            commodityTb.setBeginTime(df_2.parse(df_2.format(df_1.parse(beginDate))));
            commodityTb.setEndTime(df_2.parse(df_2.format(df_1.parse(endDate))));
//            commodityTb.setStatu(0);

            baseMapper.insert(commodityTb);

            //user_commodity_tb 关系表
            UserCommodityTb userCommodityTb = new UserCommodityTb();
            userCommodityTb.setCid(cid);
            userCommodityTb.setUid(uid);
            userCommodityTbMapper.insert(userCommodityTb);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }


    /** 
    * @Description: 获取商品详情 
    * @Param: [cid] 
    * @return: com.sif.action.result.GoodDetailResult 
    * @Author: shenyini
    * @Date: 2020/1/1 
    */ 
    @Override
    public GoodDetailResult getGoodDetail(String cid) {
        CommodityTb commodityTb = commodityTbMapper.selectById(cid);
        System.out.println(commodityTb);

        GoodDetailResult goodDetailResult = new GoodDetailResult();

        /*cid*/
        goodDetailResult.setCid(commodityTb.getCid());
        /*price*/
        goodDetailResult.setPrice(commodityTb.getPrice());
        /*addprice*/
        goodDetailResult.setAddprice(commodityTb.getAddprice());
        /*nowprice 如果有竞购，取最近一次竞购的价格，否则取原价*/
        List<com.sif.action.pojo.BiddingTb> biddingList = biddingTbRepository.findBiddingRecord(cid);
        if(biddingList.size() == 0){
            System.err.println("未拍卖过");
            System.err.println(commodityTb.getPrice());
            goodDetailResult.setNowprice(commodityTb.getPrice());
        }else{
            System.err.println(biddingList.get(0).getBidprice());
            goodDetailResult.setNowprice(biddingList.get(0).getBidprice());
        }

        /*images*/
        String[] images = commodityTb.getImages().split("_");
        for(int i= 0; i < images.length; i++){
            if(images[i] != "" && !"http".equals(images[i].substring(0,4)))
            images[i] = Constants.IMAGE_PATH +images[i];
        }
        goodDetailResult.setImages(images);
        /*cname*/
        goodDetailResult.setCname(commodityTb.getCname());
        /*typeid*/
        String[] typeid = commodityTb.getTypeId().split("_");
        goodDetailResult.setTypeid(typeid);
        /*seller*/
        Map map = new HashMap();
        map.put("cid",cid);
        List<UserCommodityTb> userCommodityTb = userCommodityTbMapper.selectByMap(map);
        UserTb user = userTbMapper.selectById(userCommodityTb.get(0).getUid());
        goodDetailResult.setSeller(user.getName());
        /*count*/
        List<BiddingTb> list = biddingTbMapper.selectByMap(map);
        goodDetailResult.setCount(list.size());
        /*score*/
        Map map2 = new HashMap();
        map2.put("uid", user.getUid());
        UserCreditTb userCreditTb = (UserCreditTb) userCreditTbMapper.selectByMap(map2).get(0);
        goodDetailResult.setScore(userCreditTb.getScore());
        History history = new History();
        List<History> histories = EntityUtils.castEntity(commodityTbRepository.findGoodList(cid) , History.class, history);
        goodDetailResult.setHistory(histories);
        /*des*/
        goodDetailResult.setDes(commodityTb.getDes());
        /*param1*/
        List<OtherDetail> param1 = new ArrayList<>();
        OtherDetail o1 = new OtherDetail("商品件数","1");
        OtherDetail o2 = new OtherDetail("邮费","买家承担");
        OtherDetail o3 = new OtherDetail("可否退货","不可以");
        param1.add(o1);
        param1.add(o2);
        param1.add(o3);
        goodDetailResult.setParam1(param1);
        /*param2*/
        List<OtherDetail> param2 = new ArrayList<>();
                                                          // 2019-11-24 16:35:35
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        OtherDetail o4 = new OtherDetail("开始时间",dateFormat.format(commodityTb.getBeginTime()));
        OtherDetail o5 = new OtherDetail("结束时间",dateFormat.format(commodityTb.getEndTime()));
        OtherDetail o6 = new OtherDetail("提早结束","有可能");
        param2.add(o4);
        param2.add(o5);
        param2.add(o6);
        goodDetailResult.setParam2(param2);
        long beginTime = commodityTb.getBeginTime().getTime();
        long endTime = commodityTb.getEndTime().getTime();
        long nowTime = System.currentTimeMillis();
        if(beginTime > nowTime) { //竞拍还未开始
            goodDetailResult.setStatus(1);
        }
        if(endTime < nowTime) { //竞拍已经结束
            goodDetailResult.setStatus(2);
        }
        return goodDetailResult;
    }

    @Override
    public ShopForm getCommodity(String cid) {
        CommodityTb commodityTb = commodityTbMapper.selectById(cid);
        ShopForm shopForm = new ShopForm();
        shopForm.setCid(commodityTb.getCid());
        shopForm.setCname(commodityTb.getCname());
        shopForm.setPrice(commodityTb.getPrice());
        shopForm.setAddprice(commodityTb.getAddprice());
        shopForm.setImage(commodityTb.getImage());
        shopForm.setImages(commodityTb.getImages());
        shopForm.setDesc(commodityTb.getDes());
        //[2019-12-18T16:00:00.000Z, 2020-01-01T16:00:00.000Z]
        List<String> part_time = new ArrayList<>();
        SimpleDateFormat df_1 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS Z");
        SimpleDateFormat df_2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String begin_time = df_1.format(commodityTb.getBeginTime());
        String end_time = df_1.format(commodityTb.getEndTime());
        System.err.println(begin_time);
        System.err.println(end_time);
        part_time.add(begin_time);
        part_time.add(end_time);
        shopForm.setPart_time(part_time);
        shopForm.setType_id(commodityTb.getTypeId());
        shopForm.setBrand_name(commodityTb.getBrandName());
        return shopForm;
    }

    @Override
    public void delCommodity(String cid) {
        commodityTbMapper.deleteById(cid);
        Map map = new HashMap();
        map.put("cid",cid);
        userCommodityTbMapper.deleteByMap(map);
    }

    @Override
    public void editCommodity(ShopForm shopForm, String cid) {
        String beginDate = ((List<String>) shopForm.getPart_time()).get(0).replace("Z", " UTC");
        String endDate = ((List<String>) shopForm.getPart_time()).get(1).replace("Z", " UTC");
        SimpleDateFormat df_1 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS Z");
        SimpleDateFormat df_2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println("beginDate:" + beginDate);
        System.out.println("endDate" + endDate);
        try {
            CommodityTb commodityTb = new CommodityTb();
            commodityTb.setCid(cid);
            commodityTb.setCname(shopForm.getCname());
            commodityTb.setPrice(shopForm.getPrice());
            commodityTb.setAddprice(shopForm.getAddprice());
            commodityTb.setImage(shopForm.getImage());
            commodityTb.setImages(shopForm.getImages());

            System.err.println(shopForm.getImages());

            commodityTb.setDes(shopForm.getDesc());
            commodityTb.setTypeId(shopForm.getType_id());
            commodityTb.setBrandName(shopForm.getBrand_name());
            commodityTb.setCreateTime(new Date());
            commodityTb.setUpdateTime(new Date());
            commodityTb.setBeginTime(df_2.parse(df_2.format(df_1.parse(beginDate))));
            commodityTb.setEndTime(df_2.parse(df_2.format(df_1.parse(endDate))));
            commodityTb.setStatu(0);
            baseMapper.updateById(commodityTb);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Override
    public CommodityTb getOrderCommodity(String cid) {
        return commodityTbMapper.selectById(cid);
    }

    @Transactional
    @Override
    public List<HistoryCommodity> findHistoryGoodList(String cid) {
        HistoryCommodity historyCommodity = new HistoryCommodity();
        List<HistoryCommodity> list = EntityUtils.castEntity(commodityTbRepository.findHistoryGoodList(cid, cid), HistoryCommodity.class, historyCommodity);
        return list;
    }

    @Override
    public List<HistoryBidding> selectHistoryBidding(String cid) {
        HistoryBidding historyBidding = new HistoryBidding();
        List<HistoryBidding> list = EntityUtils.castEntity(biddingTbRepository.selectHistoryBidding(cid), HistoryBidding.class, historyBidding);
        System.out.println("selectHistoryBidding");
        list.forEach(item->{
            System.err.println(item.toString());
        });
        return list;
    }

    @Override
    public GoodDetail getGood(String cid) {
        GoodDetail goodDetail = new GoodDetail();
        goodDetail.setGoodDetailResult(this.getGoodDetail(cid));
        goodDetail.setHistoryCommodityList(this.findHistoryGoodList(cid));
        goodDetail.setHistoryBiddingList(this.selectHistoryBidding(cid));
        return goodDetail;
    }

    @Override
    public List<com.sif.action.pojo.CommodityTb> getIsBiddingCommodity() {
        return commodityTbRepository.getIsBiddingCommodity();
    }

    @Override
    public String selectSeller(String cid) {
        Map map = new HashMap();
        map.put("cid",cid);
        List<UserCommodityTb> list = userCommodityTbMapper.selectByMap(map);
        String uid = "";
        if(list.size() !=0) {
            uid = list.get(0).getUid();
        }
        return uid;
    }
}

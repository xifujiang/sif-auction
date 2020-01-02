package com.sif.webmagic.task;

import com.sif.common.util.IdWorker;
import com.sif.webmagic.WebMagicApplication;
import com.sif.webmagic.pojo.WebMagicCommodity;
import org.jsoup.Jsoup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.scheduler.BloomFilterDuplicateRemover;
import us.codecraft.webmagic.scheduler.QueueScheduler;
import us.codecraft.webmagic.selector.Html;
import us.codecraft.webmagic.selector.Selectable;

import java.math.BigDecimal;
import java.nio.file.WatchEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * @program: sif-auction
 * @description:
 * @author: xifujiang
 * @create: 2019-12-03 10:46
 **/
@Component
public class JobProcessor implements PageProcessor {
//    private String url = "http://www.ymi.cn/auccate-2084024535.html";
    private String url = "http://www.ymi.cn/";


    @Override
    public void process(Page page) {
        //获取类型的url地址
        List<Selectable> typelist = page.getHtml().xpath("//html/body/div[4]/div/ul/li[1]/ul").css("li div dl dd a").nodes();
        if(typelist.size() != 0){
            typelist.forEach(item -> {
                String typeInfoUrl = item.links().toString();
                page.addTargetRequest(typeInfoUrl);
            });
        } else {
            //解析页面，获取商品的url地址
            List<Selectable> list = page.getHtml().css("div.mingcheng a").nodes();
            //判断获取到的集合是否为空
            if (list.size() == 0) {
                //如果为空，则表示这是商品详情页,解析页面，获取商品详细信息，保存数据
                this.saveCommodity(page);
            } else {
                //如果不为空，表示这是列表页,解析出详情页面的url地址，放到任务队列中
                for (Selectable selectable : list) {
                    //获取url地址
                    String commodityInfoUrl = selectable.links().toString();
                    //把获取到的url地址放到任务队列中
                    page.addTargetRequest(commodityInfoUrl);

//                    //获取下一页的url
//                    String nextUrl = page.getHtml().css("div.yema div a").nodes().get(7).links().toString();
//                    //把url放到任务队列中
//                    page.addTargetRequest(nextUrl);
                }
            }
        }
    }


    IdWorker idWorker = new IdWorker();
    //解析页面，获取商品详细信息，保存数据
    private void saveCommodity(Page page){
        //创建商品对象
        WebMagicCommodity webMagicCommodity = new WebMagicCommodity();

        //解析页面
        Html html = page.getHtml();

        // 获取数据，封装到对象中
        webMagicCommodity.setCid(idWorker.nextId()+ "");
        webMagicCommodity.setUrl(page.getUrl().get());
//        String[] cnum = html.xpath("//html/body/div[4]/div[1]/div[2]/div[1]/div[2]/dl/dd/span/text()").get().split("：");
//        System.out.println(cnum);
        webMagicCommodity.setCnum(html.xpath("//html/body/div[4]/div[1]/div[2]/div[1]/div[2]/dl/dd/span/text()").get());

        webMagicCommodity.setCname(html.xpath("//dl[@class=title]/dt/text()").get());

        String nowPrice = html.xpath("//*[@id=rmbprice]/text()").get();
        if(nowPrice != null) {
            webMagicCommodity.setNowprice(new BigDecimal(nowPrice));
        }

        String addPrice = html.css("div.see_y font#addprice","text").toString();
        if(addPrice != null){
            webMagicCommodity.setAddprice(new BigDecimal(addPrice));
        }

        webMagicCommodity.setImage(html.xpath("//*[@id=\"thumblist\"]/li[1]/div").css("img","src").get());
        List<String> imgList = html.xpath("//*[@id=\"thumblist\"]").css("img", "src").all();
        StringBuilder images = new StringBuilder();
        imgList.forEach(item -> {
            if(images.length() != 0)
                images.append('_');
            images.append(item);
        });
        webMagicCommodity.setImages(images+"");

        //        webMagicCommodity.setDes();

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyy-MM-dd HH:mm:ss");
        String[] begin_time = html.xpath("//html/body/div[4]/div[1]/div[2]/div[1]/div[2]/div[4]/dl[2]/dd[1]/text()").get().split("：");
        String[] end_time = html.xpath("//html/body/div[4]/div[1]/div[2]/div[1]/div[2]/div[4]/dl[2]/dd[2]/text()").get().split("：");
        try {
            webMagicCommodity.setBegin_time(simpleDateFormat.parse(begin_time[1]));
            webMagicCommodity.setEnd_time(simpleDateFormat.parse(end_time[1]));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        String type_one = html.xpath("//html/body/div[4]/div[1]/div[1]/a[2]/text()").get();
        String type_two = html.xpath("//html/body/div[4]/div[1]/div[1]/a[3]/text()").get();
        String type_three = html.xpath("//html/body/div[4]/div[1]/div[1]/a[4]/text()").get();
        webMagicCommodity.setType_id(type_one + "_" + type_two + "_" + type_three);
        webMagicCommodity.setBrand_name(type_three);

        //把结果保存起来
        page.putField("webMagicCommodity",webMagicCommodity);

        //保存到数据库中

    }

    private Site site = Site.me()
        .setCharset("utf-8")//设置编码
        .setTimeOut(10*1000)//设置超时时间
        .setRetrySleepTime(3000)//设置重试的时间
        .setRetryTimes(3);//设置重试的次数
    @Override
    public Site getSite() {
        return site;
    }

    @Autowired
    private SpringDataPipeline springDataPipeline;

    //initialDelay当任务启动后，等多久执行方法
    //fixedDelay每隔多久执行方法
    @Scheduled(initialDelay = 1000, fixedDelay = 10000)
    public void process(){
        Spider.create(new JobProcessor())
            .addUrl(url)
            .setScheduler(new QueueScheduler()
                .setDuplicateRemover(new BloomFilterDuplicateRemover(1000000)))
            .thread(10)
            .addPipeline(springDataPipeline)
            .run();
    }
}

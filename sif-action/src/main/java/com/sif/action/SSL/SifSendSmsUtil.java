package com.sif.action.SSL;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author xifujiang
 * @Description //TODO 短信发送工具类
 * @Date 2019/10/18 12:29
 * @Version 1.0
 **/
public class SifSendSmsUtil {

    /**
     * @Description //TODO 根据params中的值发送指定验证码的短信
     * @Params [appId, appKey, params, nationCode, mobile, sign, templateId, SSLPath, SSLPassword]
     *  appId 应用appId
     *  appKey 应用 appKey
     *  params 短信中类似{1}的填充内容 这里是验证码
     *  nationCode  电话的区号 如+86
     *  mobile 手机号
     *  sign 签名内容 【签名内容】
     *  templateId 短信内容的模板ID
     *  SSLPath ssl文件的存放路径
     *  SSLPassword ssl文件对应的密码
     * @Return void
     **/
    public static void sendSms(Integer appId, String appKey, String params, String nationCode, String mobile, int sign, String templateId, String SSLPath, String SSLPassword){

        String random = RandomUtil.getRandom(9);
        String time = UnixTimeUtil.getUnixTime();

        Map mobileMap = new HashMap();
        mobileMap.put("mobile", mobile);
        mobileMap.put("nationcode", nationCode);
        List<Map> list = new ArrayList<>();
        list.add(mobileMap);

        String oriCode = "appkey=" + appKey + "&random=" + random +"&time="+ time +"&mobile=" + mobile;


        Map postMap = new HashMap();

        postMap.put("ext", "");
        postMap.put("extend", "");
        postMap.put("params", params);
        postMap.put("sig", SHA256Util.getSHA256(oriCode));
        postMap.put("tel", mobileMap);
        postMap.put("time", Integer.parseInt(time));
        postMap.put("tpl_id", Integer.parseInt(templateId));
        postMap.put("sign", sign);

        HttpClientUtils.doHttpsPostJson("https://yun.tim.qq.com/v5/tlssmssvr/sendsms?sdkappid=" + appId+ "&random=" + random, JSON.toJSONString(postMap),SSLPath ,SSLPassword);

    }


    /**
     * @Description //TODO 发送随机n位的验证码短信
     * @Params [appId, appKey, nationCode, mobile, sign, templateId, SSLPath, SSLPassword，randomCodeLen]
     *  appId 应用appId
     *  appKey 应用 appKey
     *  nationCode  电话的区号 如+86
     *  mobile 手机号
     *  sign 签名内容 【签名内容】
     *  templateId 短信内容的模板ID
     *  SSLPath ssl文件的存放路径
     *  SSLPassword ssl文件对应的密码
     *  randomCodeLen 验证码的位数
     * @Return void
     **/
    public static void sendSms(Integer appId, String appKey, String nationCode, String mobile, String sign, String templateId, String SSLPath, String SSLPassword, Integer randomCodeLen){

        String[] params = {RandomUtil.getRandom(randomCodeLen)};

        String random = RandomUtil.getRandom(randomCodeLen);

        //存储random到 redis



        String time = UnixTimeUtil.getUnixTime();

        Map mobileMap = new HashMap();
        mobileMap.put("mobile", mobile);
        mobileMap.put("nationcode", nationCode);
        List<Map> list = new ArrayList<>();
        list.add(mobileMap);

        String oriCode = "appkey=" + appKey + "&random=" + random +"&time="+ time +"&mobile=" + mobile;


        Map postMap = new HashMap();

        postMap.put("ext", "");
        postMap.put("extend", "");
        postMap.put("params", params);
        postMap.put("sig", SHA256Util.getSHA256(oriCode));
        postMap.put("tel", mobileMap);
        postMap.put("time", Integer.parseInt(time));
        postMap.put("tpl_id", Integer.parseInt(templateId));
        postMap.put("sign", sign);

        HttpClientUtils.doHttpsPostJson("https://yun.tim.qq.com/v5/tlssmssvr/sendsms?sdkappid=" + appId+ "&random=" + random, JSON.toJSONString(postMap),SSLPath ,SSLPassword);

    }
}

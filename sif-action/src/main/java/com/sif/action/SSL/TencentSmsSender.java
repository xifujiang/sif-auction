package com.sif.action.SSL;
import com.github.qcloudsms.SmsSingleSender;
import com.github.qcloudsms.SmsSingleSenderResult;
import org.json.JSONException;
import java.io.IOException;
import java.util.Random;

/** 
* @Description:  发送短信验证码
* @Param:  
* @return:  
* @Author: shenyini
* @Date: 2020/1/3 
*/ 
public class TencentSmsSender {

    // 短信应用 SDK AppID
    public static final int APPID = 1400273667; // SDK AppID 以1400开头
    // 短信应用 SDK AppKey
    public static final String APPKEY = "f0047d04f30e86823b03611f5ec047de";
    // 短信模板 ID，需要在短信应用中申请
    public static final int TEMPLATEID = 446834; // NOTE: 这里的模板 ID`7839`只是示例，真实的模板 ID 需要在短信控制台中申请
    // 签名
    public static final String SMSSIGN = "SIF竞拍网"; // NOTE: 签名参数使用的是`签名内容`，而不是`签名ID`。这里的签名"腾讯云"只是示例，真实的签名需要在短信控制台申请


    // "sign": "腾讯云", //短信签名，如果使用默认签名，则可以缺省此字段
    public static String sendMsg(String phone) {
        Random random = new Random();
        long rnd = random.nextInt(9999) % (9999 - 1000 + 1) + 1000;
        try {
            String[] params = {String.valueOf(rnd)};
            SmsSingleSender ssender = new SmsSingleSender(APPID, APPKEY);
            SmsSingleSenderResult result = null;
            try {
                result = ssender.sendWithParam("86", phone,
                    TEMPLATEID, params, SMSSIGN, "", "");
            } catch (com.github.qcloudsms.httpclient.HTTPException e) {
                e.printStackTrace();
            }
            System.out.println(result);
        } catch (JSONException e) {
            // JSON 解析错误
            e.printStackTrace();
        } catch (IOException e) {
            // 网络 IO 错误
            e.printStackTrace();
        }
        return String.valueOf(rnd);
    }


//    public static void main(String[] args) {
//        String s = TencentSmsSender.sendMsg("13058799005");
//        RedisTemplate redisTemplate = new RedisTemplate();
//        redisTemplate.opsForValue().set("13058799005",s,60);
//    }

}
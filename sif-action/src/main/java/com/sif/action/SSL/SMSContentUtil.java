package com.sif.action.SSL;

/**
 * @program: sifAuction
 * @description:
 * @author: xifujiang
 * @create: 2019-10-17 22:02
 **/
public class SMSContentUtil {
    // 短信应用 SDK AppID
    public static final int APPID = 1400273667; // SDK AppID 以1400开头
    // 短信应用 SDK AppKey
    public static final String APPKEY = "f0047d04f30e86823b03611f5ec047de";
    // 需要发送短信的手机号码
    public static final String[] phoneNumbers = {"13058799005"};
    // 短信模板 ID，需要在短信应用中申请
    public static final int TEMPLATEID = 446834; // NOTE: 这里的模板 ID`7839`只是示例，真实的模板 ID 需要在短信控制台中申请
    // 签名
    public static final String SMSSIGN = "SIF竞拍网"; // NOTE: 签名参数使用的是`签名内容`，而不是`签名ID`。这里的签名"腾讯云"只是示例，真实的签名需要在短信控制台申请

}

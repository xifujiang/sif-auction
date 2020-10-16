package com.sif.action.config;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/* *
 *类名：AlipayConfig
 *功能：基础配置类
 *详细：设置帐户有关信息及返回路径
 *修改日期：2017-04-05
 *说明：
 *以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 *该代码仅供学习和研究支付宝接口使用，只是提供一个参考。
 */

public class AlipayConfig {

//↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓

    // 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
    public static String app_id = "2016101400687323";

    // 商户私钥，您的PKCS8格式RSA2私钥
    public static String merchant_private_key = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCBDJN6XWQTTOXc9Oeo3yYKiUK2VcB6b7Exf58pkOBU/lxlgWc1+LypoBX+f/UYeaoGIy69kAuR6CKzkGmL6R2IbBYdrpevTRPdC0GHEyJLvHpXGpxZ7kwPlT9YK8oSNh+xl0QWD7rSSZbFu+X/PleAB/rtujAME1hIsW/RvNH4JIj0dusdIwIj2f4PhPvjkAf/5pCNdM7BsT8ra6dmj4acf+0hOFpFnK/RfEpF8RGvaQCNMTmpc89+jMJnNEf/qLo6vBIctO4GLHA2Bp8JPFQZQGMxZzbeRRJknsON2xhSo09xxqD7jl2ESPn2uYer8lYXOo42OJQlZTekMiSH50WTAgMBAAECggEAeKKY+rpy4tfvyS3noSV5GkBV7L0cwVfH/M2gq+qSyuE83mapZPqw4qCT7uUO6WJSXoplFy+goTSvu8wz3mZ9BRqFUhn0EhurhecPyoVIeiQm+WYW4rqwv6B1JqdIrt2JoyoUq8roicvg9ddAmepdQYI0QCbc42zyCoJUVWx9m8i3qza/UR5Ok0Dc+/Cm9TuIde2Z0Jew+ggW/ppHRZav9sBgVrTkbp7pD+aM6lvXAGFHUKAbIkcZ7rE6RghlNEcnIsRp4fDatiIpVM9DFSpnUeK4aFjhOAChXsh0fDh9/S2PCs1d+73cbr02vyopnBLFpn8naRetvjSprWdfZgjXAQKBgQC2S8fR7YIbIWtwu1fhE5flpV32d/PaF7eQuPuTKDOE78k3VomBRAddqxr6VAoIk/lfci3CYSYBWuXqJKecYQng6PVn3XK3qWo9o+YXNhsUbnNGtQBYx6GXO990/Xu/VDbiqa0A/ASTxNUfPGC1Wl+VQlLBbIykG/ZJHQ74aQvEeQKBgQC1OZKBhNDXSpL9IQogNAuqmsJNNGLLznEnGRwlwtBiKWceh2bcmhKl3IDh9ZwmEpOAALUPG3/l2Ya3IoNloguldn/xbK1mZ3gN1icd1gPpot64BMmCn2w3kWf5+APo6nOYNFvHI5eX8+VbT7RQUf1RaD2nlIXvAmc1ykI1OV2fawKBgGqbSpHz6aBgHOriRHQ19yCvYpHeeFbUg984rrbSTb6yU+Pu0Z8s33nhtHcsamm1z6IHrcb89TLZq5cQNIz5GsPSuKEzGgFaK+08SjTMyW1bYkK5WyiQJmnA898A1jt/zlEjlS78MtkPxOrtbkjgPwGUtbyz9FOMnq0HN1gCZoVRAoGAP59Wr+HQ78Relj9jLqsW7W4niLM7fBGd5/wIxbj/L3cZnL4sfoYYxeID3VA84aDj56xX5FXjMO6NPP4A401giVZw1HEy7yCkJTdpxC++b+eeCTylAha2B0wlFLzdooeszWKUVfVI4y/c9ZGl3Azswge2nX6nBqHxWhEH7WeWVsECgYEAkNHLM8q5d+rIXxmevaSbz9WM+dvKmYUN7v6c+j1hwrOscKBS4FYFCtLtj9QKrrDGuRhiZfMpUsQq5uQE2WVvYrSAY/0RIv5NMSpV2DCu6WCHH8yn6nZjRUtsj5cj8j3Yg213O5Tp6/pJUnCxwdMEf/CU8jFzaJ8BU7qly1sxiKs=";

    // 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
    public static String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAwqVF+SahnfwatC+3SXINQ5W3XVaT08wg548ht0lyw0Vu8ZACuKq1aXtxuP54FxPAbAu6Pp++m3MpvZioFVORVYf61Ns5S/QaPOGVLuZ6pCvc2kwGR+pau38RGhbaFviHhncDwtMgB++AE0jOrugR/PNZ5yItAr77+pnb05hOtLu7/UfxS9pzzHDMjbt88v2jBQcSyHtGSdQVNlt9ZM5EYOqntd0PAVyLiRlX2k/g71PQpaZs1Axm5u870nby2xmYRZptoK7Xml3p37++MCvud7a4vhPl1WA1UNKV6bzBMrqhaYkqjRimNUzSCS9RBJGIQECVOnm6GEfnouG6lZf3qwIDAQAB";

    // 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
//    public static String notify_url = "http://localhost:8443/notifyUrl";
    public static String notify_url = "http://localhost:8443/notifyUrl";

    // 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
//    public static String return_url = "http://localhost:8080/#/payDone";
    public static String return_url = "http://localhost:8443/api/returnUrl";

    // 签名方式
    public static String sign_type = "RSA2";

    // 字符编码格式
    public static String charset = "utf-8";

    // 支付宝网关
    public static String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";

    // 支付宝网关
    public static String log_path = "C:\\";


//↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑

    /**
     * 写日志，方便测试（看网站需求，也可以改成把记录存入数据库）
     * @param sWord 要写入日志里的文本内容
     */
    public static void logResult(String sWord) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(log_path + "alipay_log_" + System.currentTimeMillis()+".txt");
            writer.write(sWord);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}


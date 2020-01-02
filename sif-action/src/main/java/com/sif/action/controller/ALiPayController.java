package com.sif.action.controller;

/**
 * @program: sif-auction
 * @description:
 * @author: xifujiang
 * @create: 2019-11-20 16:47
 **/
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.sif.action.config.AlipayConfig;
import com.sif.action.constants.Constants;
import com.sif.action.pojo.OrderInfo;
import com.sif.action.service.*;
import com.sif.common.entity.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.SpringHandlerInstantiator;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

@Controller
@CrossOrigin
public class ALiPayController {

    @Autowired
    private DepositPayTbService depositPayTbService;

    @Autowired
    private OrderTbService orderTbService;

    @Autowired
    private MemberPayTbService memberPayTbService;

    @Autowired
    private PackageBillsTbService packageBillsTbService;

    //用于请求首页显示支付付款功能
    @ResponseBody
    @RequestMapping("/api/payindex")
    public String payindex(){
        return "index";
    }

    @ResponseBody
    @PostMapping("/api/tradePay")
    public Result tradePay(@RequestBody OrderInfo orderInfo)  {
        if (orderInfo == null){
            return new Result(400);
        }
        // 将支付订单存入到对应的表中
        if("支付竞拍保证金".equals(orderInfo.getNature())) {
            depositPayTbService.insertDeposit(orderInfo, "alipay", false);
        } else if("升级会员".equals(orderInfo.getNature())) {
            memberPayTbService.insertMemberPay(orderInfo, "alipay", false);
        } else if("订单支付".equals(orderInfo.getNature())) {
            orderTbService.insertOrder(orderInfo, "alipay", false);
        } else if("充值钱包".equals(orderInfo.getNature())) {
            packageBillsTbService.insertPackageBills(orderInfo, "alipay", false);
        }
        //获得初始化的AlipayClient
        AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.gatewayUrl, AlipayConfig.app_id, AlipayConfig.merchant_private_key, "json", AlipayConfig.charset, AlipayConfig.alipay_public_key, AlipayConfig.sign_type);

        //设置请求参数
        AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
        alipayRequest.setReturnUrl(AlipayConfig.return_url);
//        alipayRequest.setReturnUrl("http://localhost:8080/#/payDone");
        alipayRequest.setNotifyUrl(AlipayConfig.notify_url);

        //商户订单号，商户网站订单系统中唯一订单号，必填
        String out_trade_no = orderInfo.getTradeNo();
        //付款金额，必填
        String total_amount = orderInfo.getTotalPrice();
        //订单名称，必填
        String subject = orderInfo.getSubject();
        //商品描述，可空
        String body = orderInfo.getNature();

        alipayRequest.setBizContent("{\"out_trade_no\":\""+ out_trade_no +"\","
        + "\"total_amount\":\""+ total_amount +"\","
        + "\"subject\":\""+ subject +"\","
        + "\"body\":\""+ body +"\","
        + "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");

        //请求
        String result = null;
        try {
            result = alipayClient.pageExecute(alipayRequest).getBody();
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }

        //输出
        System.out.println(result);

        return new Result(true,200,"转发支付", result);
    }

//    @RequestMapping("/notifyUrl")
//    public Result notifyUrl(HttpServletRequest request, @PathVariable String orderId,HttpServletResponse response){
//
//        new Result();
//    }

    @RequestMapping("/api/returnUrl")
    public void returnUrl(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException, AlipayApiException {
        //获取支付宝GET过来反馈信息
        Map<String,String> params = new HashMap<String,String>();
        Map<String,String[]> requestParams = request.getParameterMap();
        for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext();) {
            String name = (String) iter.next();

            String[] values = (String[]) requestParams.get(name);

            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i]
                        : valueStr + values[i] + ",";
            }
            //乱码解决，这段代码在出现乱码时使用
            valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
            params.put(name, valueStr);
        }

        boolean signVerified = AlipaySignature.rsaCheckV1(params, AlipayConfig.alipay_public_key, AlipayConfig.charset, AlipayConfig.sign_type); //调用SDK验证签名

        //——请在这里编写您的程序（以下代码仅作参考）——
        if(signVerified) {
            //商户订单号
            String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"),"UTF-8");

            //支付宝交易号
            String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"),"UTF-8");

            //付款金额
            String total_amount = new String(request.getParameter("total_amount").getBytes("ISO-8859-1"),"UTF-8");

            String result = "trade_no:"+trade_no+"<br/>out_trade_no:"+out_trade_no+"<br/>total_amount:"+total_amount;
            request.getSession().setAttribute("session",result);

            //支付成功的结果写入数据库
            String trade_No = params.get("out_trade_no");
            if("1".equals(trade_No.substring(0,1))) {
                depositPayTbService.updateDepositStatus(trade_No);
            } else if("2".equals(trade_No.substring(0,1))) {
                orderTbService.updateOrderStatus(trade_No);
            } else if("3".equals(trade_No.substring(0,1))){
                memberPayTbService.updateMemberStatus(trade_No);
                String memberid = trade_No.substring(1,2); // 获取升级后的memberid
                result = result+ "<br/>memberid:" + memberid;
            } else if("4".equals(trade_No.substring(0,1))){
                packageBillsTbService.updateBillsStatus(trade_No);
            }
//            http://localhost:8080/#/payDone
            try {
                response.sendRedirect(Constants.FORWARD_URL + "/payDone?result="+result);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            String result = "验签失败";
            request.getSession().setAttribute("session",result);
        }
    }
}
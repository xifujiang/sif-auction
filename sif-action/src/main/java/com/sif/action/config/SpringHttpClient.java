package com.sif.action.config;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.text.MessageFormat;
import java.util.Iterator;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.ResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

import com.alibaba.fastjson.JSON;

/**
 * 
 * @ClassName: SpringHttpClient 
 * @author xiongbin
 * @date 2018年7月11日 下午4:19:53
 */
public class SpringHttpClient {
	
	private static Logger logger =  LoggerFactory.getLogger("exceptionLogger");
	
	/**
	 * post请求
	 * @Title: post 
	 * @param url			url地址
	 * @param paramMap		入参
	 * @return
	 * @date 2018年7月11日 下午4:19:30  
	 * @author xiongbin
	 */
	public static String post(String url,Map<String,String> paramMap) {
        return post(url, null, paramMap);
	}
	
	/**
	 * post请求
	 * @Title: post 
	 * @param url				url地址
	 * @param headerMap			header头信息
	 * @param paramMap			入参
	 * @return
	 * @date 2019年7月23日 上午9:44:40  
	 * @author xiongbin
	 */
	public static String post(String url,Map<String,String> headerMap,Map<String,String> paramMap) {
		MultiValueMap<String, String> body = new LinkedMultiValueMap<String, String>();
        Iterator<Map.Entry<String, String>> iterator = paramMap.entrySet().iterator();
        while (iterator.hasNext()) {
        	Map.Entry<String, String> i = iterator.next();
        	body.add(i.getKey(),i.getValue());
        }
		
		return exchange(url, HttpMethod.POST, MediaType.APPLICATION_FORM_URLENCODED, headerMap, body);
	}
	
	/**
	 * post请求,body入参,json格式
	 * @Title: postBodyJson 
	 * @param url				url地址
	 * @param headerMap			header头信息
	 * @param body				body入参
	 * @return
	 * @date 2019年7月25日 下午3:35:13  
	 * @author xiongbin
	 */
	public static String postBodyJson(String url,Map<String,String> headerMap,String body) {
		return exchange(url, HttpMethod.POST, MediaType.APPLICATION_JSON, headerMap, body);
	}
	/**
	 * put请求,body入参,json格式
	 * @Title: putBodyJson 
	 * @param url				url地址
	 * @param headerMap			header头信息
	 * @param body				body入参
	 * @return
	 */
	public static String putBodyJson(String url,Map<String,String> headerMap,String body) {
		return exchange(url, HttpMethod.PUT, MediaType.APPLICATION_JSON, headerMap, body);
	}
	
	/**
	 * get请求
	 * @Title: post 
	 * @param url			url地址
	 * @return
	 * @date 2018年7月11日 下午4:19:30  
	 * @author xiongbin
	 */
	public static String get(String url) {
		return get(url, null);
	}
	
	/**
	 * get请求
	 * @Title: get 
	 * @param url				url地址
	 * @param headerMap			header头信息
	 * @return
	 * @date 2019年7月22日 下午4:05:52  
	 * @author xiongbin
	 */
	public static String get(String url,Map<String,String> headerMap) {
		return exchange(url, HttpMethod.GET, MediaType.APPLICATION_FORM_URLENCODED, headerMap, null);
	}
	
	
	/**
	 * delete请求
	 * @Title: delete 
	 * @param url				url地址
	 * @param headerMap			header头信息
	 * @return
	 */
	public static String delete(String url,Map<String,String> headerMap) {
		return exchange(url, HttpMethod.DELETE, MediaType.APPLICATION_FORM_URLENCODED, headerMap, null);
	}
	
	/**
	 * put请求
	 * @Title: put 
	 * @param url				url地址
	 * @param headerMap			header头信息
	 * @param paramMap			入参
	 * @return
	 */
	public static String put(String url,Map<String,String> headerMap,Map<String,String> paramMap) {
		MultiValueMap<String, String> body = new LinkedMultiValueMap<String, String>();
        Iterator<Map.Entry<String, String>> iterator = paramMap.entrySet().iterator();
        while (iterator.hasNext()) {
        	Map.Entry<String, String> i = iterator.next();
        	body.add(i.getKey(),i.getValue());
        }
		
		return exchange(url, HttpMethod.PUT, MediaType.APPLICATION_FORM_URLENCODED, headerMap, body);
	}
	
	/**
	 * 请求地址
	 * @Title: exchange 
	 * @param url				url地址
	 * @param httpMethod		请求方式
	 * @param mediaType			请求格式
	 * @param headerMap			header头信息
	 * @param body				入参
	 * @return
	 * @date 2019年7月25日 下午3:37:18  
	 * @author xiongbin
	 */
	private static <T> String exchange(String url,HttpMethod httpMethod,MediaType mediaType,Map<String,String> headerMap,T body) {
		logger.info(MessageFormat.format("线程:{0},请求url:{1},httpMethod:{2},mediaType:{3},headerMap:{4},body:{5}", 
						Thread.currentThread().getName(),url,httpMethod.name(),mediaType.getType(),JSON.toJSONString(headerMap),JSON.toJSONString(body)));
		
		RestTemplate client = new RestTemplate();
		client.getMessageConverters().set(1, new StringHttpMessageConverter(StandardCharsets.UTF_8));
        client.setErrorHandler(new ResponseErrorHandler() {
        	@Override
            public boolean hasError(ClientHttpResponse clientHttpResponse) throws IOException {
                return true;
            }

            @Override
            public void handleError(ClientHttpResponse clientHttpResponse) throws IOException {

            }
        });
		
        HttpHeaders headers = new HttpHeaders();
        //请勿轻易改变此提交方式，大部分的情况下，提交方式都是表单提交
        headers.setContentType(mediaType);
        
        if (null != headerMap) {
	        Iterator<Map.Entry<String, String>> iterator = headerMap.entrySet().iterator();
	        while (iterator.hasNext()) {
	        	Map.Entry<String, String> i = iterator.next();
	        	headers.add(i.getKey(), i.getValue());
	        }
        }
        
        HttpEntity<?> requestEntity;
        if (null == body) {
        	MultiValueMap<String, String> multiValueMap = new LinkedMultiValueMap<String, String>();
        	requestEntity = new HttpEntity<MultiValueMap<String, String>>(multiValueMap, headers);
        } else {
        	requestEntity = new HttpEntity<T>(body, headers);;
        }
        
        //执行HTTP请求
        ResponseEntity<String> response = client.exchange(url, httpMethod, requestEntity, String.class);
        String reslut = response.getBody();

		logger.info(MessageFormat.format("线程:{0},出参:{1}", Thread.currentThread().getName(),reslut));
        return reslut;
	}
	
	public static void main(String[] args) {
//		String url = "http://118.190.119.83:8755/api/gateway";
//		
//		Map<String,String> map = new HashMap<String, String>();
////		map.put("out_trade_no", "");
//		map.put("sign", "ds");
//		map.put("sign_type", "hrtdfsd");
//		map.put("appId", "171128776516");
//		map.put("payment_id", "19062613324300819727");
////		map.put("trade_no", "");
////		map.put("Third_payment_id", "");
//		map.put("version", "V1.1");
//		map.put("function", "dabay.trade.query");
//		
//		post(url, map);
		System.out.println(JSON.toJSONString(null));
	}
}

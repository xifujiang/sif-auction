package com.sif.action.config;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;

public class JsonUtil {
    /**
     * 判断jsonstr2中是否包含了jsonstr1中的值  2>=1
     *
     * @param jsonstr1
     * @param jsonstr2
     * @return
     */
    private static Logger logger = LoggerFactory.getLogger("exceptionLogger");

    public static Boolean compareJsonStr(String jsonstr1, String jsonstr2) {
        if (StringUtils.isBlank(jsonstr1)) {//如果json1没有值返回ture
            return true;
        } else if (StringUtils.isBlank(jsonstr2) || 3 == isJSONValid(jsonstr2)) {//2没值或不是JSON
            return false;
        }


        Map<String, Object> map1 = JsonToMap(jsonstr1);//需要校验的值
        Map<String, Object> map2 = JsonToMap(jsonstr2);
        for (Object key : map1.keySet()) {//需要校验的对象
            Object value1 = map1.get(key);
            Object value2 = map2.get(key);
            if (!compareJsonObj(value1, value2)) {
                return false;
            }
        }
        return true;
    }
    
    /**
     * 仅判断json的key值
     * @param jsonstr1
     * @param jsonstr2
     * @return
     */
    public static Boolean compareJsonStrKey(String jsonstr1, String jsonstr2) {
        if (StringUtils.isBlank(jsonstr1)) {//如果json1没有值返回ture
            return true;
        } else if (StringUtils.isBlank(jsonstr2) || 3 == isJSONValid(jsonstr2)) {//2没值或不是JSON
            return false;
        }

        Map<String, Object> map1 = JsonToMap(jsonstr1);
        Map<String, Object> map2 = JsonToMap(jsonstr2);
        for (Entry<String, Object> vo : map1.entrySet()) {
            String key1 = vo.getKey();
            if (!map2.containsKey(key1)) {
                return false;
            }
        }
        return true;
    }


    public static Map<String, Object> JsonToMap(String jsonstr) {
        try {
            if (null == jsonstr) {
                return new HashMap<String, Object>();
            }
            Map<String, Object> map1 = JSON.parseObject(jsonstr);
            return map1;
        } catch (Exception e) {
            logger.info(jsonstr);
            e.printStackTrace();
        }
        return null;
    }


    public static JSONObject strToJson(String jsonStr) {
        if (StringUtils.isNotBlank(jsonStr)) {
            return JSON.parseObject(jsonStr);
        } else {
            return new JSONObject();
        }

    }

    /**
     * 判断值是否json
     *
     * @param test
     * @return 1 jsonObj   2 jsonArr 3 不是json
     */
    public static Integer isJSONValid(String test) {
        try {
            JSONObject.parseObject(test);
            return 1;
        } catch (Exception ex) {
            try {
                JSONObject.parseArray(test);
                return 2;
            } catch (Exception ex1) {
                return 3;
            }
        }
    }

    /**
     * @param o1
     * @param o2
     * @return 02 包含或等于o1 返回ture
     */
    public static boolean compareJsonObj(Object o1, Object o2) {
        Boolean bolean = true;
        if ((null == o1 && null != o2) || (null == o2 && null != o1)) {//一个有值，一个为null
            bolean = false;
        } else if (null != o1 && null != o2) {//两个都不是null
            //强转为string进行对比
            Integer int1 = isJSONValid(o1.toString());
            Integer int2 = isJSONValid(o2.toString());
            if (int1 != int2) {//参数类型不一致
                bolean = false;
            } else if (1 == int1) {//是jsonobjcat
                compareJsonStr(o1.toString(), o2.toString());
            } else if (2 == int1) {//是jsonarr
                JSONArray jsonarr1 = JSONObject.parseArray(o1.toString());
                JSONArray jsonarr2 = JSONObject.parseArray(o2.toString());
                for (Object object1 : jsonarr1) {//请求接口返回的数组中只包含json对象
                    Boolean arrBoolean = false;//默认数组2不包含数组1
                    for (Object object2 : jsonarr2) {
                        if (compareJsonStr(object1.toString(), object2.toString())) {//如果找到o2包含01的对象
                            arrBoolean = true;//默认数组2包含object1
                            break;//跳出循环
                        }
                    }
                    if (!arrBoolean) {//如果有一个对象没找到对应的值
                        bolean = false;
                        return bolean;//返回false
                    }
                }

            } else {//不是json
                return (o1.toString()).equals(o2.toString());
            }
        }
        return bolean;
    }
}
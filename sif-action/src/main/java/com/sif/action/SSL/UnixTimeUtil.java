package com.sif.action.SSL;

/**
 * @Author xifujiang
 * @Description //TODO Unix工具类
 * @Date 2019/10/18 12:29
 * @Version 1.0
 **/
public class UnixTimeUtil {

    /**
     * @Description //TODO 获取当前系统的UNIX时间
     * @Params []
     * @Return java.lang.String
     **/
    public static String getUnixTime(){
        return Long.toString(System.currentTimeMillis()/1000L);
    }
}

package com.sif.action.SSL;

import java.util.Random;

/**
 * @Author xifujiang
 * @Description //TODO 随机数生成工具类
 * @Date 2019/10/18 12:30
 * @Version 1.0
 **/
public class RandomUtil {
    public static String getRandom(int len) {
        Random r = new Random();
        StringBuilder rs = new StringBuilder();
        for (int i = 0; i < len; i++) {
            rs.append(r.nextInt(10));
        }
        return rs.toString();
    }
}

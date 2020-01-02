package com.sif.action.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.sif.action.entity.MemberRuleTb;
import com.sif.action.entity.UserMemberTb;
import com.sif.action.mapper.MemberRuleTbMapper;
import com.sif.action.mapper.UserMemberTbMapper;
import com.sif.action.service.MemberRuleTbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Sif
 * @since 2019-11-11
 */
@Service
public class MemberRuleTbServiceImpl extends ServiceImpl<MemberRuleTbMapper, MemberRuleTb> implements MemberRuleTbService {
    @Autowired
    RedisTemplate redisTemplate;
    @Autowired
    MemberRuleTbMapper memberRuleTbMapper;

    @Override
    public List<MemberRuleTb> selectMemberRule() {
        List<MemberRuleTb> list = new ArrayList<>();
        String jsonString  = (String)redisTemplate.opsForValue().get("membertype");
        /// redis里能取到值
        if(jsonString != null){
            list = JSON.parseArray(jsonString, MemberRuleTb.class);
            return list;
        }
        Map map = new HashMap();
        list = memberRuleTbMapper.selectByMap(map);
        redisTemplate.opsForValue().set("membertype", JSON.toJSONString(list));
        return list;
    }


}

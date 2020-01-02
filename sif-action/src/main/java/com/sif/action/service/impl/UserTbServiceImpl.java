package com.sif.action.service.impl;

import com.sif.action.entity.UserTb;
import com.sif.action.mapper.UserTbMapper;
import com.sif.action.service.UserTbService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Sif
 * @since 2019-10-26
 */
@Service
public class UserTbServiceImpl extends ServiceImpl<UserTbMapper, UserTb> implements UserTbService {

    @Autowired
    RedisTemplate redisTemplate;

    @Autowired
    UserTbMapper userTbMapper;

    /**
    * @Description: 判断手机号
    * @Param: [phone]
    * @return: java.util.List<com.sif.action.entity.UserTb>
    * @Author: xifujiang
    * @Date: 2019/10/30
    */
    @Override
    public List<UserTb> judgePhone(String phone) {
        Map map = new HashMap();
        map.put("phone",phone);
        List<UserTb> xx = userTbMapper.selectByMap(map);
        return userTbMapper.selectByMap(map);
    }

    /**
    * @Description: 添加用户
    * @Param: [user]
    * @return: void
    * @Author: xifujiang
    * @Date: 2019/10/30
    */
    @Override
    public void addUser(UserTb user) {
        userTbMapper.insert(user);
    }

    /**
    * @Description: 匹配账号密码，用于登录
    * @Param: [name, password]
    * @return: java.util.List<com.sif.action.entity.UserTb>
    * @Author: xifujiang
    * @Date: 2019/10/30
    */
    @Override
    public List<UserTb> selectNameAndPwd(String name, String password) {
        Map map = new HashMap();
        map.put("name",name);
        map.put("password",password);
        return userTbMapper.selectByMap(map);
    }

    /**
    * @Description: 读取用户id
    * @Param: [name]
    * @return: java.lang.String
    * @Author: xifujiang
    * @Date: 2019/10/30
    */
    @Override
    public String getUserId(String name) {
        //从redis中读取userid
        String userid  = (String)redisTemplate.opsForValue().get(name+"0000");
        if(userid != null){
            return userid;
        }else{
            Map map = new HashMap();
            map.put("name",name);
            List<UserTb> list = userTbMapper.selectByMap(map);
            userid = list.get(0).getUid();
            redisTemplate.opsForValue().set(name+"0000", userid);
            return userid;
        }
    }
}

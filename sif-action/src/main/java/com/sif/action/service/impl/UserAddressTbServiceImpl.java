package com.sif.action.service.impl;

import com.sif.action.entity.UserAddressTb;
import com.sif.action.mapper.UserAddressTbMapper;
import com.sif.action.service.UserAddressTbService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
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
public class UserAddressTbServiceImpl extends ServiceImpl<UserAddressTbMapper, UserAddressTb> implements UserAddressTbService {

    @Autowired
    UserAddressTbMapper userAddressTbMapper;

    /**
    * @Description: 添加地址
    * @Param: [address]
    * @return: void
    * @Author: xifujiang
    * @Date: 2019/10/30
    */
    @Override
    public void addAddress(UserAddressTb address) {
        userAddressTbMapper.insert(address);
    }

    @Override
    public List<UserAddressTb> selectAddress(String userid) {
        Map map = new HashMap();
        map.put("userid",userid);
        return userAddressTbMapper.selectByMap(map);
    }

    @Override
    public void updateAddress(UserAddressTb address) {
        userAddressTbMapper.updateById(address);
    }

    @Override
    public void delAddress(Integer addid) {
        userAddressTbMapper.deleteById(addid);
    }
}

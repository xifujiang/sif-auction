package com.sif.action.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.sif.action.entity.UserMemberTb;
import com.sif.action.mapper.UserMemberTbMapper;
import com.sif.action.service.UserMemberTbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Sif
 * @since 2019-11-11
 */
@Service
public class UserMemberTbServiceImpl extends ServiceImpl<UserMemberTbMapper, UserMemberTb> implements UserMemberTbService {

    @Autowired
    UserMemberTbMapper userMemberTbMapper;


    /**
    * @Description: 初始化会员等级
    * @Param: [uid]
    * @return: void
    * @Author: xifujiang
    * @Date: 2019/11/19
    */
    @Override
    public void unitMember(String uid) {
        UserMemberTb userMemberTb = new UserMemberTb();
        userMemberTb.setUid(uid);
        userMemberTb.setMemberid(1);
        userMemberTbMapper.insert(userMemberTb);
    }
}

package com.sif.action.service.impl;

import com.sif.action.entity.UserCreditTb;
import com.sif.action.mapper.UserCreditTbMapper;
import com.sif.action.service.UserCreditTbService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Sif
 * @since 2019-10-26
 */
@Service
public class UserCreditTbServiceImpl extends ServiceImpl<UserCreditTbMapper, UserCreditTb> implements UserCreditTbService {

    @Autowired
    UserCreditTbMapper userCreditTbMapper;

    @Override
    public void unitCredit(String uid) {
        UserCreditTb userCreditTb = new UserCreditTb();
        userCreditTb.setUid(uid);
        userCreditTb.setScore(500);
        userCreditTbMapper.insert(userCreditTb);
    }
}

package com.sif.action.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sif.action.entity.UserCreditTb;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Sif
 * @since 2019-10-26
 */
public interface UserCreditTbService extends IService<UserCreditTb> {

    public void unitCredit(String uid);
}

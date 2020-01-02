package com.sif.action.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sif.action.entity.UserMemberTb;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Sif
 * @since 2019-11-11
 */
public interface UserMemberTbService extends IService<UserMemberTb> {

    public void unitMember(String uid);
}

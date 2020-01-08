package com.sif.action.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sif.action.entity.UserTb;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Sif
 * @since 2019-10-26
 */
public interface UserTbService extends IService<UserTb> {
    List<UserTb> judgePhone(String phone);

    void addUser(UserTb requestUser);

    List<UserTb> selectNameAndPwd(String name, String password);

    String getUserId(String name);
}

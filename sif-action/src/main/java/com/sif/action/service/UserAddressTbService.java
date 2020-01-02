package com.sif.action.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sif.action.entity.UserAddressTb;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Sif
 * @since 2019-10-26
 */
public interface UserAddressTbService extends IService<UserAddressTb> {

    public void addAddress(UserAddressTb address);

    public List<UserAddressTb> selectAddress(String userid);

    public void updateAddress(UserAddressTb address);

    public void delAddress(Integer addid);
}

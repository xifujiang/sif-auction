package com.sif.action.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sif.action.entity.MemberRuleTb;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Sif
 * @since 2019-11-11
 */
public interface MemberRuleTbService extends IService<MemberRuleTb> {

     List<MemberRuleTb> selectMemberRule();

}

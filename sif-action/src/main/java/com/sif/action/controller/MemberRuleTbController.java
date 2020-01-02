package com.sif.action.controller;


import com.sif.action.entity.MemberRuleTb;
import com.sif.action.service.MemberRuleTbService;
import com.sif.common.entity.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Sif
 * @since 2019-11-11
 */
@RestController
public class MemberRuleTbController {

    @Autowired
    MemberRuleTbService memberRuleTbService;
    /**
    * @Description:  selectMemberRule 获取会员规则
    * @Param: []
    * @return: com.sif.common.entity.result.Result
    * @Author: xifujiang
    * @Date: 2019/11/19
    */
    @GetMapping("api/selectMemberRule")
    public Result selectMemberRule(){
        List<MemberRuleTb> list = memberRuleTbService.selectMemberRule();
        return new Result(true,200,"获取会员规则成功", list);
    }



}


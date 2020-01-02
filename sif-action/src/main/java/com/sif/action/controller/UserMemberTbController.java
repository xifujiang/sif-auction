package com.sif.action.controller;


import com.sif.action.repository.UserTbRepository;
import com.sif.action.service.UserMemberTbService;
import com.sif.common.entity.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Sif
 * @since 2019-11-11
 */
@RestController
public class UserMemberTbController {
    @Autowired
    private UserMemberTbService userMemberTbService;

    @Autowired
    private UserTbRepository userTbRepository;
    @GetMapping("/api/getUserBiddingCount")
    public Result getUserBiddingCount(String uid){
        Integer myCount = userTbRepository.getUserBiddingCount(uid);
        return new Result(true, 200, "获取成功", myCount);
    }


}


package com.sif.action.controller;


import com.sif.action.entity.UserTb;
import com.sif.action.repository.UserTbRepository;
import com.sif.action.result.LoginResult;
import com.sif.action.result.UserDetail;
import com.sif.action.service.UserCreditTbService;
import com.sif.action.service.UserMemberTbService;
import com.sif.action.service.UserTbService;
import com.sif.common.entity.result.Result;
import com.sif.common.util.EntityUtils;
import com.sif.common.util.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Sif
 * @since 2019-10-26
 */
@RestController
public class UserTbController {
    @Autowired
    UserTbService userTbService;

    @Autowired
    UserCreditTbService userCreditTbService;

    @Autowired
    UserMemberTbService userMemberTbService;

    @Autowired
    UserTbRepository userTbRepository;

    /**
     * @Description: 注册
     * @Param: [requestUser]
     * @return: com.sif.sifauction.result.Result
     * @Author: xifujiang
     * @Date: 2019/10/15
     */
    @PostMapping(value = "api/regist")
    @ResponseBody
    public Result regist(@RequestBody UserTb requestUser) {
        //查找数据库中是否有相同手机号，如果有，返回error
        if (userTbService.judgePhone(requestUser.getPhone()).size() != 0){
            return new Result(400);
        } else {
            //可以存入数据库
            // 1、密码加密
            String password = MD5Utils.MD5(requestUser.getPassword());
            // 2、添加密码、uid
            requestUser.setPassword(password);
            requestUser.setUid(UUID.randomUUID().toString());
            //3、把注册的信息存到数据库
            userTbService.addUser(requestUser);
            //4、初始化信用
            String uid = userTbService.getUserId(requestUser.getName());
            userCreditTbService.unitCredit(uid);
            //5、初始化用户vip等级
            userMemberTbService.unitMember(uid);
            return new Result(200);
        }
    }
    /**
     * @Description: 登录
     * @Param: [requestUser]
     * @return: com.sif.sifauction.result.Result
     * @Author: xifujiang
     * @Date: 2019/10/15
     */
    @PostMapping(value = "api/login")
    @ResponseBody
    public Result login(@RequestBody UserTb requestUser) {
        //查找数据库中是否有相同手机号，如果有，返回error
        String pwd = MD5Utils.MD5(requestUser.getPassword());
        LoginResult login = new LoginResult();
        List<LoginResult> loginResult = EntityUtils.castEntity(userTbRepository.getUserInfo(requestUser.getName(), pwd) , LoginResult.class, login);
        if(loginResult.size() != 0){
            return new Result(true, 200, "登录成功", loginResult.get(0));
        }
        return new Result(400);
    }

    //生产验证码 保持到redis数据库中，有效时间为一分钟
//    @RequestMapping("/doYzm")
//    @ResponseBody
//    public boolean doYzm(@RequestBody req) {
//        try {
//            String ip = req.getRemoteAddr();
//            String yzm = this.getYzm();
//            System.out.println(yzm);
//            ValueOperations forValue = rt.opsForValue();
//            forValue.set(ip+"_yzm", yzm);
//            rt.expire(ip+"_yzm", 60*1000, TimeUnit.MILLISECONDS);
//            return true;
//        } catch (Exception e) {
//            // TODO: handle exception
//            e.printStackTrace();
//        }
//        return false;
//    }


    @GetMapping("/api/getUserDetail")
    public Result getUserDetail(String uid){
        UserDetail userDetail = new UserDetail();
        List<UserDetail> userDetailList = EntityUtils.castEntity(userTbRepository.selectUserDetail(uid),UserDetail.class,userDetail);
        UserDetail u = userDetailList.get(0);
        return new Result(true,200,"获取成功", u);
    }

    @PostMapping("/api/userUpgrade")
    public Result userUpgrade(String uid){
        LoginResult login = new LoginResult();
        List<LoginResult> loginResult = EntityUtils.castEntity(userTbRepository.getUserUpgrade(uid) , LoginResult.class, login);
        return new Result(true, 200, "更新成功", loginResult);
    }
}




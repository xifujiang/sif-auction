package com.sif.action.test;

import com.sif.action.entity.UserTb;
import org.junit.Test;

import java.util.UUID;

/**
 * @program: sif-auction
 * @description:
 * @author: xifujiang
 * @create: 2019-12-01 15:01
 **/
public class UserCreateTest {
    @Test
    public void newUser(){
        UserTb user = new UserTb();
        user.setUid(UUID.randomUUID().toString());

    }
}

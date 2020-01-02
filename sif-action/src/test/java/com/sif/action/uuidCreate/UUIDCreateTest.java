package com.sif.action.uuidCreate;

import org.junit.Test;

import java.util.UUID;

/**
 * @program: sif-auction
 * @description:
 * @author: shenyini
 * @create: 2019-12-06 09:24
 **/
public class UUIDCreateTest {
    @Test
    public void createTest(){
        for(int i= 0; i< 614; i++){
            System.out.println(UUID.randomUUID().toString());
        }
    }
}

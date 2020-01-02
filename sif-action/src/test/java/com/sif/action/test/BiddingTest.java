package com.sif.action.test;

import com.sif.action.pojo.BiddingTb;
import com.sif.action.repository.BiddingTbRepository;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: sif-auction
 * @description:
 * @author: shenyini
 * @create: 2019-12-09 10:51
 **/
@Service
public class BiddingTest {
    @Autowired
    BiddingTbRepository biddingTbRepository;
    @Test
    public void getBiddingTest(){
        List<BiddingTb> biddingTbs = biddingTbRepository.findBiddingRecord("1202515940071890000");
        biddingTbs.forEach(item ->{
            System.out.println(item.toString());
        });
    }
}

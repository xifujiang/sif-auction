package com.sif.action.quartz;

import com.sif.action.quartz.job.Job;
import com.sif.action.service.QuartzService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.HashMap;

/**
 * @program: sif-auction
 * @description:
 * @author: shenyini
 * @create: 2019-12-16 14:27
 **/
@Component
public class MyStartupRunner implements CommandLineRunner {
    @Autowired
    QuartzService quartzService;
    @Override
    public void run(String... args) throws Exception {
        HashMap<String,Object> map = new HashMap<>();
        map.put("name",1);
        quartzService.deleteJob("job", "test");
        quartzService.addJob(Job.class, "job", "test", "* 2 * * * ?", map);

    }
}

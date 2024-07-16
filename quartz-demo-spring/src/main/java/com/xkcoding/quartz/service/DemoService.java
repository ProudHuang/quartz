package com.xkcoding.quartz.service;

import com.xkcoding.quartz.form.JobParamter;
import com.xkcoding.quartz.autoconfiguration.ScheduleManager;
import com.xkcoding.quartz.job.JobDemo3;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * <p>
 * 测试 Spring Bean
 * </p>
 *
 * @author yangkai.shen
 * @date Created in 2020-06-05 11:06
 */
@Slf4j
@RestController
@RequestMapping(value = "/api/portal")
public class DemoService {
    @Autowired
    private ScheduleManager scheduleManager;


    @RequestMapping(value = "/task", method = RequestMethod.GET)
    public void echo() {
        JobParamter jobParamter = new JobParamter();
        jobParamter.setJobName("task3");
        jobParamter.setStartTime(new Date());
        jobParamter.setEndTtime(new Date());
        jobParamter.setTimes(10);
        scheduleManager.start(jobParamter, JobDemo3.class);
    }
}

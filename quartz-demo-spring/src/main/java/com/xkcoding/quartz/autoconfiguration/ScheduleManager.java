package com.xkcoding.quartz.autoconfiguration;

import org.quartz.Job;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @Description 调度工具类
 * @Author junhao huang
 * @Date 2024/6/27 15:31
 */
@Component
public class ScheduleManager {
    private static final Logger log = LoggerFactory.getLogger(ScheduleManager.class);

    private Scheduler scheduler;

    public ScheduleManager(Scheduler scheduler) {
        this.scheduler = scheduler;
    }

    public void start(JobParamter jobParamter, Class<? extends Job> jobClass) {

        //创建任务
        JobDetail jobDetail = JobBuilder
                .newJob(jobClass)
                .withIdentity(jobParamter.getJobName())
                .build();
        //创建触发器
        Trigger trigger = TriggerBuilder.newTrigger()
                .withIdentity(jobParamter.getJobName())
                .startAt(new Date()) // 设置开始时间
                .withSchedule(SimpleScheduleBuilder.simpleSchedule()
                        .withRepeatCount(jobParamter.getTimes())) // 设置重复次数为0，即执行一次
                .endAt(new Date()) // 设置结束时间，也就是上面时间点执行完毕后
                .build();
        try {
            //将任务及其触发器放入调度器
            scheduler.scheduleJob(jobDetail, trigger);
            //调度器开始调度任务
            if (!scheduler.isStarted()) {
                scheduler.start();
            }

            log.info("启动任务：{}", jobParamter.getJobName());
        } catch (SchedulerException e) {
            log.error("任务：{}启动失败", jobParamter.getJobName(), e);
        }

    }


    /**
     * 停止任务--再启动需要从新初始化
     *
     * @param jobParamter
     */
    public void stop(JobParamter jobParamter) {
        try {
            // 停止触发器
            scheduler.pauseTrigger(new TriggerKey(jobParamter.getJobName()));
            scheduler.unscheduleJob(new TriggerKey(jobParamter.getJobName()));
            // 删除任务
            scheduler.deleteJob(new JobKey(jobParamter.getJobName()));
            log.info("停止任务：{}", jobParamter.getJobName());
        } catch (SchedulerException e) {
            log.error("任务：{}停止失败", jobParamter.getJobName(), e);
        }
    }
}
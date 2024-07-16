package com.xkcoding.quartz;

import com.xkcoding.quartz.form.JobParamter;
import com.xkcoding.quartz.job.JobDemo1;
import com.xkcoding.quartz.job.JobDemo2;
import com.xkcoding.quartz.job.JobDemo3;
import com.xkcoding.quartz.service.DemoService;
import com.xkcoding.quartz.util.SpringUtil;
import org.quartz.*;
import org.quartz.impl.StdScheduler;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.Date;

/**
 * <p>
 * 启动类
 * </p>
 *
 * @author yangkai.shen
 * @date Created in 2020-06-05 11:03
 */
@Configuration
@ComponentScan(basePackages = "com.xkcoding.quartz")
public class Bootstrap {

    public static void main(String[] args) throws SchedulerException {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(Bootstrap.class);
        ((AnnotationConfigApplicationContext) applicationContext).start();
        initQuartzJobs();
    }

    public static void initQuartzJobs() throws SchedulerException {
        DemoService demoService = SpringUtil.getBean(DemoService.class);
        demoService.echo();
    }
}

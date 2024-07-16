package com.xkcoding.quartz.autoconfiguration;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * @Description 定时任务参数
 * @Author junhao huang
 * @Date 2024/6/27 15:47
 */
@Setter
@Getter
public class JobParamter {

    /**
     * 任务名字
     */
    private String jobName;

    /**
     * 开始执行时间
     */
    private Date startTime;

    /**
     * 结束执行时间
     */
    private Date endTtime;

    /**
     * 执行次数
     */
    private int times;
}

package com.dwring.springboot.executor.config;

import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.dwring.springboot.executor.quartz.QuartzJob;

@Configuration
public class QuartzConfig {

	@Bean
	public JobDetail jobDetail() {
		return JobBuilder.newJob(QuartzJob.class).storeDurably().build();
	}

	@Bean
	public Trigger trigger() {
		SimpleScheduleBuilder scheduleBuilder = SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(1) // 每一秒执行一次
				.repeatForever(); // 永久重复，一直执行下去
		return TriggerBuilder.newTrigger().forJob(jobDetail()).withSchedule(scheduleBuilder).build();
	}

}
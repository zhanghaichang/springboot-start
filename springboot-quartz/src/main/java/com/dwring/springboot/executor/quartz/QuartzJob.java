package com.dwring.springboot.executor.quartz;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;


/**
* @ClassName: QuartzJob
* @Description: 打印时间
* @author zhanghaichang
* @date 2020年11月27日
*
*/
@DisallowConcurrentExecution
public class QuartzJob extends QuartzJobBean {

	@Override
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		System.out.println("QuartzJob1----" + sdf.format(new Date()));
	}

}

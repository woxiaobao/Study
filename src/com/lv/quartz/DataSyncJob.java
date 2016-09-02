package com.lv.quartz;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
*
* @author LvBaolin
* @date: 2016年7月11日
* @time: 上午10:45:52
*/

public class DataSyncJob {
	
	  protected void executeInternal(JobExecutionContext ctx) throws JobExecutionException {
		  Date now = new java.util.Date(System.currentTimeMillis());
		  DateFormat df = SimpleDateFormat.getInstance();
	      System.out.println("定时任务执行："+df.format(now));
	      
//	      CronTriggerBean dataSyncTrigger=(CronTriggerBean)ServiceLocator.getService("dataSyncTrigger");
//		  try {
//			  System.out.println("设定下次执行时间：0 0 17 03 30 ?");
//			  dataSyncTrigger.setCronExpression("0 0 17 03 30 ?");
//		  } catch (ParseException e) {
//			  e.printStackTrace();
//		  }
		  //dataSyncTrigger.setCronExpression(cronExpression);
	  }

}

package com.ibm.cit.employeeStatsWeb.EmployeeStatsWeb.schedule;

import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

public class JobTrigger {

	public static void main(String[] args) throws SchedulerException {
		JobDetail job = JobBuilder.newJob(HelloJob.class).build();
		//Trigger trigger =
		// TriggerBuilder.newTrigger().withIdentity("SimpleTrigger").startNow().build();
		Trigger t1 = TriggerBuilder.newTrigger().withIdentity("CronTrigger")
			.withSchedule(CronScheduleBuilder.cronSchedule("	0 0/1 * 1/1 * ? *")).build();
		//Trigger t1 = TriggerBuilder.newTrigger().withIdentity("SimpleTrigger")
					//	.withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(05).repeatForever()).build();
		Scheduler sc = StdSchedulerFactory.getDefaultScheduler();
		sc.start();
		sc.scheduleJob(job, t1);
	}
	// define the job and tie it to our HelloJob class

}

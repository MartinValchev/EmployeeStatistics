package com.ibm.cit.employeeStatsWeb.EmployeeStatsWeb.schedule;

import org.apache.log4j.Logger;
import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

public class SchedullerCleanJobMain {
	public static void main(String[] args) {

	}

	public void run() {
		JobDetail job = JobBuilder.newJob(TokenCleanJob.class).build();
		//Trigger t1 = TriggerBuilder.newTrigger().withIdentity("SimpleTrigger").startNow().build();
		Trigger t1 = TriggerBuilder.newTrigger().withIdentity("CronTrigger")
				.withSchedule(CronScheduleBuilder.cronSchedule("0 0/45 * 1/1 * ? *")).build();
		// Trigger t1 = TriggerBuilder.newTrigger().withIdentity("SimpleTrigger")
		// .withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(05).repeatForever()).build();
		Scheduler sc = null;
		try {
			sc = StdSchedulerFactory.getDefaultScheduler();
			sc.start();
			sc.scheduleJob(job, t1);
		} catch (SchedulerException e) {
			Logger log = Logger.getLogger(SchedullerCleanJobMain.class);
			log.error(e.toString());

		}

	}
}

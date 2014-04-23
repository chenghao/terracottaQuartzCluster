package com.hao;

import java.lang.reflect.Method;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.quartz.QuartzJobBean;

public class DetailQuartzJobBean extends QuartzJobBean {

	private String targetObject;
	private String targetMethod;
	private ApplicationContext cxt;

	@Override
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
		try {
			//targetObject和targetMethod是spring-quartz.xml文件中jobDataAsMap属性的key
			this.setTargetObject((String) context.getMergedJobDataMap().get("targetObject"));
			this.setTargetMethod((String) context.getMergedJobDataMap().get("targetMethod"));

			//applicationContextKey是spring-quartz.xml文件中applicationContextSchedulerContextKey属性的value
			cxt = (ApplicationContext) context.getScheduler().getContext().get("applicationContextKey");
			Object otargetObject = cxt.getBean(targetObject);

			Method m = otargetObject.getClass().getMethod(targetMethod, new Class[] {});
			m.invoke(otargetObject, new Object[] {});
		} catch (Exception e) {
			e.printStackTrace();
			throw new JobExecutionException(e);
		}
	}

	public void setTargetObject(String targetObject) {
		this.targetObject = targetObject;
	}

	public void setTargetMethod(String targetMethod) {
		this.targetMethod = targetMethod;
	}

	public void setCxt(ApplicationContext cxt) {
		this.cxt = cxt;
	}

}

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
			String targetObject1 = (String) context.getMergedJobDataMap().get("targetObject");
			this.setTargetObject(targetObject1);

			String targetMethod1 = (String) context.getMergedJobDataMap().get("targetMethod");
			this.setTargetMethod(targetMethod1);

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

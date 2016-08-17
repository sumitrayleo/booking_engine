package com.ehi.msi.enterprisecarshare.executor;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.MDC;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

import com.ehi.msi.enterprisecarshare.annotation.Schedulable;

/**
 * Aspect for Thread Logging for the service health check threads.
 * 
 */
@Aspect
@Component
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class ThreadLoggingAspect {

	private final static Logger LOGGER = LoggerFactory
			.getLogger(ThreadLoggingAspect.class);

	@Value("${csma.request.id.header.name}")
	private String csmaRequestId;

	/**
	 * Runs around the run method of every health check thread and performs the
	 * task of logging.
	 * 
	 * @param jp
	 * @param bean
	 * @throws Throwable
	 */
	@Around(value = "execution(public void com.ehi.msi.enterprisecarshare.executor.daemon.thread.*Executor.run()) && target(bean)", argNames = "bean")
	public void logForAuditing(ProceedingJoinPoint jp, Object bean)
			throws Throwable {
		if (bean instanceof Task) {
			MDC.put(csmaRequestId, ((Task) bean).getId());
			final Schedulable schedulable = bean.getClass().getAnnotation(
					Schedulable.class);
			if (schedulable != null) {
				long now = System.currentTimeMillis();
				LOGGER.info(buildMessage(true, (Task) bean, schedulable, now));
				jp.proceed();
				LOGGER.info(buildMessage(false, (Task) bean, schedulable, now));
			}
		} else {
			jp.proceed();
		}
	}

	private String buildMessage(final boolean isStart, final Task task,
			final Schedulable schedulable, final long now) {
		final StringBuilder stringBuilder = new StringBuilder();
		if (isStart) {
			stringBuilder.append(String.format(
					"Starting health check Thread ID:[%s] Group Name:[%s] ",
					task.getId(), schedulable.groupName()));
			appendInstanceAndCountryCode(stringBuilder, schedulable);
		} else {
			stringBuilder.append(String.format(
					"Ending health check Thread ID:[%s] Group Name:[%s]",
					task.getId(), schedulable.groupName()));
			appendInstanceAndCountryCode(stringBuilder, schedulable);
			stringBuilder.append(String.format(" Execution time:[%d] ms",
					(System.currentTimeMillis() - now)));
		}
		return stringBuilder.toString();
	}

	private void appendInstanceAndCountryCode(
			final StringBuilder stringBuilder, final Schedulable schedulable) {
		if (StringUtils.isNotBlank(schedulable.instanceName())
				&& StringUtils.isNotBlank(schedulable.countryCode())) {
			stringBuilder.append(String.format(
					" Instance Name:[%s] Country Code:[%s]",
					schedulable.instanceName(), schedulable.countryCode()));
		}
	}
}

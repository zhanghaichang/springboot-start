package com.qf.springboot.config;


import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @Title: PerformanceInterceptor.java
 * @Package com.qf.springboot.config
 * @Description: AOP 拦截器Controller-方法耗时统计
 * @author haichangzhang
 * @date 2017年7月7日 下午1:47:03
 * @version V1.0
 */
@Aspect
@Component
public class PerformanceInterceptor {

	private static final Logger logger = LoggerFactory.getLogger(PerformanceInterceptor.class);

	/** 
	* @Title: controllerInterceptor 
	* @Description: 定义一个切入点 
	* @param     设定文件 
	* @return void    返回类型 
	* @throws 
	*/
	@Pointcut("execution(public * com.qf.springboot.controller..*.*(..))")
	public void controllerInterceptor() {
	}

	/** 
	* @Title: interceptor 
	* @Description: 环绕通知：包围一个连接点的通知，环绕通知可以在方法调用前后完成自定义的行为。
	* 它也会选择是否继续执行连接点或直接返回它自己的返回值或抛出异常来结束执行。 
	* @param @param joinPoint
	* @param @return
	* @param @throws Throwable    设定文件 
	* @return Object    返回类型 
	* @throws 
	*/
	@Around("controllerInterceptor()")
	public Object interceptor(ProceedingJoinPoint joinPoint) throws Throwable {
		Long startTime = System.currentTimeMillis();
		try {
			Object object = joinPoint.proceed();
			Signature signature = joinPoint.getSignature();
			Long endTime = System.currentTimeMillis();
			logger.info("方法名称:{},总共耗时:{} ms", signature.getName(), endTime - startTime);
			return object;
		} catch (Throwable e) {
			Long endTime = System.currentTimeMillis();
			logger.error("interceptor Exception:{},总共耗时:{} ms", e.getMessage(), endTime - startTime);
			throw e;
		}
	}
}

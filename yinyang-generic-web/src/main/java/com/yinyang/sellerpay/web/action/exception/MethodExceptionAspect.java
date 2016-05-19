package com.yinyang.sellerpay.web.action.exception;

import org.aspectj.lang.JoinPoint;
import org.springframework.aop.ThrowsAdvice;

import com.dhgate.logging.Log;
import com.dhgate.logging.LogFactory;

/** 
* @Description: 截获异常信息
*
* @author zhouxiaosheng
* @version 1.0
* @create time 2013-3-11 下午02:01:52
*/
public class MethodExceptionAspect implements ThrowsAdvice{

	protected final Log logger = LogFactory.getLogger(getClass());
	

	public void afterThrowing(JoinPoint joinPoint, Throwable throwable) {
			String className = joinPoint.getSignature().getDeclaringTypeName()+ "." + joinPoint.getSignature().getName();
			StringBuffer sbError = new StringBuffer();
			String baseInfo = throwable.getClass().getName()+": "+throwable.getMessage();
			StackTraceElement[] stackElements = throwable.getStackTrace();
			sbError.append(baseInfo).append("\n");
			if(stackElements != null){
				for(int i = 0; i < stackElements.length; i++){
					sbError.append("    at ").append(stackElements[i].toString()).append("\n");
					if(i==50){
						break;
					}
				}
				sbError.append("    at " + System.currentTimeMillis() + "\n");
			}
			this.logger.error(throwable);
	}
	
}

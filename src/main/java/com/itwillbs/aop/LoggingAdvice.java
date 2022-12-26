package com.itwillbs.aop;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

// advice : 보조기능 - 로깅(출력)
public class LoggingAdvice	implements MethodInterceptor {

	// 주기능 + 보조기능 호출 메서드
	@Override
	public Object invoke(MethodInvocation invocation) throws Throwable { // Throwable : 예외의 최상위 객체
		
		System.out.println(" 주기능(메서드) 호출 전 LoggingAdvice");
		System.out.println(invocation.getMethod()+"호출 전");
		
		Object obj = invocation.proceed(); // 주기능의 메서드를 호출
		
		System.out.println(" 주기능(메서드) 호출 후 LoggingAdvice");
		
		
		return obj;
	}
	
	
	
}

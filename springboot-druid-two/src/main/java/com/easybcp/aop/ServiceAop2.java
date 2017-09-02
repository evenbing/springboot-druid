package com.easybcp.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by zhong on 2016/11/24.
 */
@Aspect
@Component
public class ServiceAop2 {
    private static Logger logger = LoggerFactory.getLogger(ServiceAop2.class);

   private ThreadLocal<Long> timeLocal = new ThreadLocal<Long>();

    @Pointcut("execution(public * com.easybcp.*.service.impl.*.*(..))")
    public void webRequestLog() {}

    @Before("webRequestLog()")
    public void doBefore(JoinPoint joinPoint) {
        timeLocal.set(System.currentTimeMillis());
    }

    @AfterReturning(returning = "result", pointcut = "webRequestLog()")
    public void doAfterReturning(Object result) {
        long startTime = timeLocal.get();
        logger.info("花费的时间为:"+(System.currentTimeMillis()-startTime)+"毫秒");
    }

    @Around("webRequestLog()")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        Object o = null;

        o = pjp.proceed();

        return o;
    }
}

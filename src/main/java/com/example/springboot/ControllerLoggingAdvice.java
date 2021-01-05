package com.example.springboot;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.CodeSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

/**
 * Created by Seunghyun.Baek
 * Since 2019-05-29
 */
@Slf4j
@Component
@Aspect
public class ControllerLoggingAdvice {

    @Before("execution(* com.example.springboot..*.*(..))")
    public void beforeExecution(JoinPoint joinPoint) {
        final long CURRENT_THREAD_ID = Thread.currentThread().getId();
        StringBuilder sb = new StringBuilder();
        sb.append("\n["+CURRENT_THREAD_ID+"]>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        sb.append("\n["+CURRENT_THREAD_ID+"]>>> Target:: "+joinPoint.getTarget().getClass().getName()+"."+joinPoint.getSignature().getName());
        if (RequestContextHolder.getRequestAttributes() != null) {
          HttpServletRequest request =
              ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            sb.append(
                "\n["
                    + CURRENT_THREAD_ID
                    + "]>>> URI:: ["
                    + request.getMethod()
                    + "] "
                    + request.getRequestURI());
            Enumeration headerNames = request.getHeaderNames();
            while (headerNames.hasMoreElements()) {
              String headerName = (String) headerNames.nextElement();
              String headerValue = request.getHeader(headerName);
              sb.append(
                  "\n[" + CURRENT_THREAD_ID + "]>>> Headers[" + headerName + "]:: " + headerValue);
            }
        }

        CodeSignature codeSignature = (CodeSignature) joinPoint.getSignature();
        String[] paramNames = codeSignature.getParameterNames();
        Object[] paramValues = joinPoint.getArgs();
        for (int i=0 ; i<paramNames.length ; i++) {
            sb.append("\n["+CURRENT_THREAD_ID+"]>>> Params["+paramNames[i]+"]:: " + ((paramValues[i] != null) ? paramValues[i].toString() : null));
        }
        sb.append("\n["+CURRENT_THREAD_ID+"]<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<\n");

        log.info(sb.toString());
    }

}

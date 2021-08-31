package com.aop.common.aspect;

import com.aop.exception.SystemException;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Method;

@Slf4j
@Aspect
@Component
public class SessionAspect {
    @Pointcut("@annotation(com.aop.common.aspect.CloseSession)")
    private void sessionClose() {
    }

    @Around("sessionClose()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        try {
            return point.proceed();
        } catch (Throwable throwable) {
            StringBuilder error = new StringBuilder();
            error.append(throwable.getClass().getName()).append(":").append(throwable.getMessage());
            Throwable temp = throwable;
            while ((temp = temp.getCause()) != null) {
                error.append("\n").append(temp.getClass().getName()).append(":").append(temp.getMessage());
            }
            log.error(throwable.getMessage(), throwable);
            throw new SystemException(500, error.toString());
        } finally {
            MethodSignature signature = (MethodSignature) point.getSignature();
            Method method = signature.getMethod();
            if (!method.isAnnotationPresent(Transactional.class)) {

            }
        }
    }
}

package com.example.smartparking.aspect;


import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class ServiceLogAspect {

    @Pointcut("bean(*Service*)")
    private void beanPointCut(){}

    @Pointcut("within(com.example.smartparking.service..*)")
    private void servicePointCut(){}

    @Pointcut("args(model)")
    private void argsPointCut(Object model){}

    @Around("beanPointCut() && servicePointCut() && argsPointCut(model)")
    public Object serviceLog(ProceedingJoinPoint proceedingJoinPoint, Object model) throws Throwable {
        log.info("Executing SERVICE METHOD with model {} ", model.toString());
        Object retValue = proceedingJoinPoint.proceed();
        log.info("Executed with return {}", retValue.toString());
        return retValue;
    }


}

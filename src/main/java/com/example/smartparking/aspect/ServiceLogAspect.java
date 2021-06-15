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

    @Pointcut("execution(public * com.example.smartparking.service..save*(..))")
    public void savePointCut(){}

    @Pointcut("execution(public * com.example.smartparking.service..update*(..))")
    public void updatePointCut(){}

    @Pointcut("execution(public * com.example.smartparking.service..delete*(..))")
    public void deletePointCut(){}

    @Pointcut("execution(public * com.example.smartparking.service..cancel*(..))")
    public void cancelPointCut(){}

    @Pointcut("args(model, ..)")
    private void argsPointCut(Object model){}

    @Around("beanPointCut() && savePointCut() && argsPointCut(model)")
    public Object serviceSaveLog(ProceedingJoinPoint proceedingJoinPoint, Object model) throws Throwable {
        log.info("Executing SAVE with model {} ", model.toString());
        Object retValue = proceedingJoinPoint.proceed();
        log.info("Entity SAVED with return {}", retValue.toString());
        return retValue;
    }

    @Around("beanPointCut() && updatePointCut() && argsPointCut(model)")
    public Object serviceUpdateLog(ProceedingJoinPoint proceedingJoinPoint, Object model) throws Throwable {
        log.info("Executing UPDATE with model {} ", model.toString());
        Object retValue = proceedingJoinPoint.proceed();
        log.info("Executed UPDATE with return {}", retValue.toString());
        return retValue;
    }

    @Around("beanPointCut() && deletePointCut() && argsPointCut(model)")
    public Object serviceDeleteLog(ProceedingJoinPoint proceedingJoinPoint, Object model) throws Throwable {
        Object retValue = proceedingJoinPoint.proceed();
        log.info("Executed DELETE");
        return retValue;
    }

    @Around("cancelPointCut() && argsPointCut(model)")
    public Object cancelDeleteLog(ProceedingJoinPoint proceedingJoinPoint, Object model) throws Throwable {
        Object retValue = proceedingJoinPoint.proceed();
        log.info("Reservation CANCELED Successfuly!! ");
        return retValue;
    }


}

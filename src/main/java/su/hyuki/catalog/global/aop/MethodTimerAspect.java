package su.hyuki.catalog.global.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import su.hyuki.catalog.global.annotation.MethodTimer;

@Slf4j
@Aspect
@Component
public class MethodTimerAspect {

  @Around("@annotation(su.hyuki.catalog.global.annotation.MethodTimer)")
  public Object logTimer(ProceedingJoinPoint joinPoint) throws Throwable {
    log.info("{} : Method Start", MethodTimer.class);

    long start = System.currentTimeMillis();

    Object proceed = joinPoint.proceed();

    long end = System.currentTimeMillis();

    log.info(joinPoint.getSignature() + " end time : " + (end-start) + "ms");

    return proceed;
  }
}

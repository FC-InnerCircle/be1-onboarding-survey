package lshh.be1onboardingsurvey.common.lib.cache.lock;

import lshh.be1onboardingsurvey.common.config.AopTransaction;
import lshh.be1onboardingsurvey.common.lib.helper.ExpressionLanguageParser;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;

import java.lang.reflect.Method;
import java.util.concurrent.locks.Lock;

@Aspect
public class AdvisoryLockManager {
    private static final String LOCK_PREFIX = "LOCK:";

    private final AdvisoryLockBuffer advisoryLockBuffer;
    private final AopTransaction aopTransaction;


    public AdvisoryLockManager(AdvisoryLockBuffer advisoryLockBuffer, AopTransaction aopTransaction) {
        this.advisoryLockBuffer = advisoryLockBuffer;
        this.aopTransaction = aopTransaction;
    }

    @Around("@annotation(lshh.be1onboardingsurvey.common.lib.cache.lock.AdvisoryLock)")
    public Object tryLock(final ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        AdvisoryLock advisoryLock = method.getAnnotation(AdvisoryLock.class);
        String prekey = advisoryLock.prekey();
        String key = advisoryLock.key();
        String resultKey = LOCK_PREFIX + prekey + ExpressionLanguageParser.getDynamicValue(signature.getParameterNames(), joinPoint.getArgs(), key);
        long waitTime = advisoryLock.waitTime();

        Lock lock = advisoryLockBuffer.getLock(key);
        System.out.println("----------------------------------------key: " + resultKey);
        if(!lock.tryLock(waitTime, advisoryLock.timeUnit())) {
            throw new AdvisoryLockException("Failed to acquire lock - over wait time: " + resultKey);
        }
        try {
            return aopTransaction.proceed(joinPoint);
        } finally {
            lock.unlock();
        }
    }
}
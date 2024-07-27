package lshh.be1onboardingsurvey.common.lib.cache.local;

import lshh.be1onboardingsurvey.common.lib.cache.lock.AdvisoryLockBuffer;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public abstract class LocalAdvisoryLockBuffer extends LocalBuffer<Lock> implements AdvisoryLockBuffer {

    public Lock getLock(String key) {
        cacheMap.putIfAbsent(key, new ReentrantLock());
        return cacheMap.get(key);
    }

    @Override
    public void clear(String key) {
        Lock lock = cacheMap.get(key);
        if(lock == null) {
            return;
        }
        super.clear(key);
    }
}
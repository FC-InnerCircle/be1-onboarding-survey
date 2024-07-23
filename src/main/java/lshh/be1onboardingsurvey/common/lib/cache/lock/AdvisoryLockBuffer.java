package lshh.be1onboardingsurvey.common.lib.cache.lock;

import java.util.concurrent.locks.Lock;

public interface AdvisoryLockBuffer {
    Lock getLock(String key);
    void clear(String key);
}

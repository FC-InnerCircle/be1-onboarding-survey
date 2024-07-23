package lshh.be1onboardingsurvey.common.lib.cache.local;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

// redis 대용
public abstract class LocalBuffer<T> {
    protected Map<String, T> cacheMap = new ConcurrentHashMap<>();

    public void set(String key, T value) {
        cacheMap.put(key, value);
    }

    public T get(String key){
        return cacheMap.get(key);
    }

    public void clear(String key) {
        cacheMap.remove(key);
    }
}

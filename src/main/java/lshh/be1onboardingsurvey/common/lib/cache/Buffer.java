package lshh.be1onboardingsurvey.common.lib.cache;

public interface Buffer <T>{
    void set(String key, T value);
    T get(String key);
    void clear(String key);
}

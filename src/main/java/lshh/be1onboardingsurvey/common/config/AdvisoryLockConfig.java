package lshh.be1onboardingsurvey.common.config;

import lombok.RequiredArgsConstructor;
import lshh.be1onboardingsurvey.common.lib.cache.lock.AdvisoryLockManager;
import lshh.be1onboardingsurvey.common.lib.cache.lock.AdvisoryLockBuffer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@RequiredArgsConstructor
@Configuration
public class AdvisoryLockConfig {
    private final AdvisoryLockBuffer advisoryLockBuffer;
    private final AopTransaction aopTransaction;

    @Bean
    public AdvisoryLockManager advisoryLockManager() {
        return new AdvisoryLockManager(advisoryLockBuffer, aopTransaction) {
        };
    }
}
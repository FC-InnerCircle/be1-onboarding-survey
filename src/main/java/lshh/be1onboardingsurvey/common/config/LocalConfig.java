package lshh.be1onboardingsurvey.common.config;

import lshh.be1onboardingsurvey.common.lib.cache.local.LocalAdvisoryLockBuffer;
import lshh.be1onboardingsurvey.common.lib.cache.lock.AdvisoryLockBuffer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LocalConfig {

    @Bean
    public AdvisoryLockBuffer advisoryLockBuffer() {
        return new LocalAdvisoryLockBuffer() {
        };
    }
}

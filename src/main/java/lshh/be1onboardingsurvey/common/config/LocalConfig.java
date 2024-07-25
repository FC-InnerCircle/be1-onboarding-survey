package lshh.be1onboardingsurvey.common.config;

import lshh.be1onboardingsurvey.common.lib.cache.Buffer;
import lshh.be1onboardingsurvey.common.lib.cache.local.LocalAdvisoryLockBuffer;
import lshh.be1onboardingsurvey.common.lib.cache.local.LocalBuffer;
import lshh.be1onboardingsurvey.common.lib.cache.lock.AdvisoryLockBuffer;
import lshh.be1onboardingsurvey.common.lib.sequnce.SequenceClient;
import lshh.be1onboardingsurvey.common.lib.sequnce.local.LocalSequenceClient;
import lshh.be1onboardingsurvey.survey.domain.entity.SurveyResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LocalConfig {

    @Bean
    public AdvisoryLockBuffer advisoryLockBuffer() {
        return new LocalAdvisoryLockBuffer() {
        };
    }


    static class SurveyResponseBuffer extends LocalBuffer<SurveyResponse> {
    }

    @Bean
    public Buffer<SurveyResponse> surveyResponseBuffer() {
        return new SurveyResponseBuffer();
    }

    @Bean
    public SequenceClient sequenceClient() {
        return new LocalSequenceClient();
    }
}

package lshh.be1onboardingsurvey.survey.infrastructure;

import lombok.RequiredArgsConstructor;
import lshh.be1onboardingsurvey.common.lib.cache.Buffer;
import lshh.be1onboardingsurvey.common.lib.sequnce.SequenceClient;
import lshh.be1onboardingsurvey.survey.domain.entity.SurveyResponse;
import lshh.be1onboardingsurvey.survey.domain.component.SurveyResponseBuffer;
import lshh.be1onboardingsurvey.survey.domain.dto.Result;
import org.springframework.stereotype.Component;

import java.util.Optional;

@RequiredArgsConstructor
@Component
public class SurveyResponseBufferImplement implements SurveyResponseBuffer {
    private final Buffer<SurveyResponse> buffer;
    private final SequenceClient sequenceClient;

    @Override
    public Optional<SurveyResponse> findResponseById(Long responseId) {
        return Optional.of(buffer.get(responseId+""));
    }

    @Override
    public Result<?> save(SurveyResponse response) {
        String key = "SurveyResponse";
        Long sequence = sequenceClient.nextLongValue(key);
        response.setId(sequence);
        buffer.set(sequence+"", response);
        return Result.success(response);
    }
}

package fastcampus.innercircle.onboarding.survey.dto;

import fastcampus.innercircle.onboarding.survey.domain.SurveyForm;
import fastcampus.innercircle.onboarding.survey.domain.SurveyQuestion;
import fastcampus.innercircle.onboarding.survey.domain.SurveyQuestionOption;
import fastcampus.innercircle.onboarding.survey.domain.SurveyResponseType;

import java.time.LocalDateTime;
import java.util.List;

public record CreateFormRequest(
        String title,
        String desc,
        List<CreateFormRequest.CreateSurveyQuestion> questions
) {
    public SurveyForm toEntity() {
        return SurveyForm.builder()
                .version(1L)
                .title(title())
                .desc(desc())
                .createAt(LocalDateTime.now())
                .questions(questions().stream()
                        .map(CreateSurveyQuestion::toEntity)
                        .toList())
                .build();
    }

    public record CreateSurveyQuestion(
            Integer position,
            String title,
            String desc,
            boolean isRequired,
            SurveyResponseType type,
            List<CreateSurveyQuestionOption> options
    ) {
        public SurveyQuestion toEntity() {
            return SurveyQuestion.builder()
                    .version(1L)
                    .title(title())
                    .desc(desc())
                    .isRequired(isRequired())
                    .responseType(type())
                    .position(position())
                    .options(options().stream()
                            .map(CreateSurveyQuestionOption::toEntity)
                            .toList())
                    .build();
        }

        public record CreateSurveyQuestionOption(
                Integer position,
                String name
        ) {
            public SurveyQuestionOption toEntity() {
                return SurveyQuestionOption.builder()
                        .name(name())
                        .build();
            }
        }
    }
}

package com.innercircle.survey.interfaces.survey;


import com.innercircle.survey.domain.survey.Survey;
import com.innercircle.survey.domain.survey.question.SurveyLongAnswer;
import com.innercircle.survey.domain.survey.question.SurveyMultipleSelect;
import com.innercircle.survey.domain.survey.question.SurveyShortAnswer;
import com.innercircle.survey.domain.survey.question.SurveySingleSelect;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

public class SurveyDto {

    @Data
    @Schema
    public static class RegisterRequest {
        private String surveyName;
        private String surveyDesc;
        private List<ShortAnswerTypeQuestion> shortAnswerTypeQuestions;
        private List<LongAnswerTypeQuestion> longAnswerTypeQuestions;
        private List<SingleSelectListQuestion> singleSelectListQuestions;
        private List<MultipleSelectListQuestion> multipleSelectListQuestions;

        @Data
        public static class ShortAnswerTypeQuestion extends QuestionBase {

            private String question;

//            public SurveyShortAnswer toEntity() {
//                return SurveyShortAnswer.builder()
//                        .question(question)
//                        .build();
//            }
        }

        @Data
        public static class LongAnswerTypeQuestion extends QuestionBase {
            private String question;

//            public SurveyLongAnswer toEntity() {
//                return SurveyLongAnswer.builder()
//                        .question(question)
//                        .build();
//            }
        }

        @Data
        public static class SingleSelectListQuestion extends QuestionBase {
            private String question;
            private List<QuestionOption> questionOptions;

//            public SurveySingleSelect toEntity() {
//                return SurveySingleSelect.builder()
//                        .question(question)
//                        .build();
//            }
        }

        @Data
        public static class MultipleSelectListQuestion extends QuestionBase {
            private String question;
            private List<QuestionOption> questionOptions;

//            public SurveyMultipleSelect toEntity() {
//                return SurveyMultipleSelect.builder()
//                        .question(question)
//                        .build();
//            }
        }

        @Data
        public static class QuestionOption {
            private Long sequence;
            private String content;
        }

        @Data
        public static class QuestionBase {
            private String question;
            private Long sequence;
            private boolean mustYN;
            private QuestType questType;
        }

        @Getter
        @RequiredArgsConstructor
        public enum QuestType {
            SHORT("단답형"),
            LONG("장문형"),
            SINGLE_SELECT_LIST("단일 선택 리스트"),
            MULTIPLE_SELECT_LIST("다중 선택 리스트");
            private final String desc;
        }

        public Survey toEntity() {
            Survey survey = new Survey();
//            if (shortAnswerTypeQuestions != null) {
//                shortAnswerTypeQuestions.stream()
//                        .map(ShortAnswerTypeQuestion::toEntity)
//                        .forEach(survey::addShortAnswer);
//            }
//            if (longAnswerTypeQuestions != null) {
//                longAnswerTypeQuestions.stream()
//                        .map(LongAnswerTypeQuestion::toEntity)
//                        .forEach(survey::addLongAnswer);
//            }
//            if (singleSelectListQuestions != null) {
//                singleSelectListQuestions.stream()
//                        .map(SingleSelectListQuestion::toEntity)
//                        .forEach(survey::addSingleSelect);
//            }
//            if (multipleSelectListQuestions != null) {
//                multipleSelectListQuestions.stream()
//                        .map(MultipleSelectListQuestion::toEntity)
//                        .forEach(survey::addMultipleSelect);
//            }
            return survey;
        }
    }

    @Data
    public static class ResponseResultRequest {

    }

    @Data
    public static class SurveyRequest {
    }

    public class SurveySubmitRequest {
    }

    /**
     * Response DTO
     * */

    @Getter
    @Builder
    public static class RegisterResponse {
        private final String surveyToken;

        public static RegisterResponse of(String surveyToken) {
            return RegisterResponse.builder()
                    .surveyToken(surveyToken)
                    .build();
        }
    }

    public class UpdateRequest {

        public Survey toEntity() {
            return null;
        }
    }
}

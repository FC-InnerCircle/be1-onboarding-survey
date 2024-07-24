package com.innercircle.survey.interfaces.servey;


import io.swagger.v3.oas.annotations.media.Schema;
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
        private List<multipleSelectListQuestion> multipleSelectListQuestions;

        @Data
        public static class ShortAnswerTypeQuestion extends QuestionBase {

        }

        @Data
        public static class LongAnswerTypeQuestion extends QuestionBase {

        }

        @Data
        public static class SingleSelectListQuestion extends QuestionBase {
            private List<QuestionOption> questionOptions;
        }

        @Data
        public static class multipleSelectListQuestion extends QuestionBase {
            private List<QuestionOption> questionOptions;
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
    }

    @Data
    public static class ResponseResultRequest {

    }

    @Data
    public static class SurveyRequest {
    }

    public class SurveySubmitRequest {
    }
}

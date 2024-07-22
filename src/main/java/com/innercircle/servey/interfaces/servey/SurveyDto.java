package com.innercircle.servey.interfaces.servey;


import lombok.Data;
import lombok.Getter;

import java.util.List;

public class SurveyDto {

    @Data
    public static class RegisterRequest {
        private String surveyName;
        private String surveyDesc;
        private List<shortAnswerTypeQuestion> shortAnswerTypeQuestions;
        private List<longAnswerTypeQuestion> longAnswerTypeQuestions;0

        @Data
        public static class shortAnswerTypeQuestion {

        }

        @Data
        public static class longAnswerTypeQuestion {

        }

    }


}

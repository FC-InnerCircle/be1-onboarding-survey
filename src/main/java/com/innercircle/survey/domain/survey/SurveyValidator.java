package com.innercircle.survey.domain.survey;

import com.innercircle.survey.domain.common.exception.CustomException;
import org.springframework.stereotype.Component;

import java.util.Optional;

import static com.innercircle.survey.domain.common.exception.ErrorCode.SURVEY_ITEM_REGISTER_FAILED;
import static com.innercircle.survey.domain.common.exception.ErrorCode.SURVEY_REGISTER_FAILED;

@Component
public class SurveyValidator {

    public Survey checkSaveSurvey(Optional<Survey> saveSurvey) {

        if (saveSurvey.isEmpty()) {
            throw new CustomException(SURVEY_REGISTER_FAILED,
                    "설문 조사 등록에 실패하였습니다.");
        }
        return saveSurvey.get();
    }

    public SurveyItem checkSaveSurveyItem(Optional<SurveyItem> saveSurveyItem) {
        if (saveSurveyItem.isEmpty()) {
            throw new CustomException(SURVEY_ITEM_REGISTER_FAILED,
                    "설문 조사 항목 등록에 실패하였습니다.");
        }
        return saveSurveyItem.get();
    }
}

package com.innercircle.survey.service;

import com.innercircle.survey.common.exception.CustomException;
import com.innercircle.survey.common.exception.ErrorCode;
import com.innercircle.survey.domain.Option;
import com.innercircle.survey.domain.Question;
import com.innercircle.survey.domain.QuestionType;
import com.innercircle.survey.domain.Survey;
import com.innercircle.survey.dto.survey.OptionReq;
import com.innercircle.survey.dto.survey.QuestionReq;
import com.innercircle.survey.dto.survey.SurveyReq;
import com.innercircle.survey.repository.OptionRepository;
import com.innercircle.survey.repository.QuestionRepository;
import com.innercircle.survey.repository.SurveyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SurveyService {

    private final SurveyRepository surveyRepository;
    private final QuestionRepository questionRepository;
    private final OptionRepository optionRepository;

    public void createSurvey(SurveyReq surveyReq) {
        if (surveyReq.getQuestions().isEmpty()) {
            throw new CustomException(ErrorCode.QUESTION_NOT_FOUND);
        }

        Survey survey = Survey.builder().title(surveyReq.getTitle())
            .description(surveyReq.getDescription()).endAt(surveyReq.getEndAt()).build();
        survey = surveyRepository.save(survey);

        List<QuestionReq> questionDtos = surveyReq.getQuestions();
        for (QuestionReq questionDto : questionDtos) {
            Question question = Question.builder().survey(survey)
                .type(QuestionType.valueOf(questionDto.getType())).text(questionDto.getText())
                .isRequired(questionDto.getIsRequired()).build();
            question = questionRepository.save(question);

            List<OptionReq> optionDtos = questionDto.getOptions();
            if (optionDtos != null) {
                for (OptionReq optionDto : optionDtos) {
                    Option option = Option.builder().question(question).text(optionDto.getText())
                        .build();
                    optionRepository.save(option);
                }
            }
        }
    }
}

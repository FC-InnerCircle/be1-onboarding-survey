package com.innercircle.survey.service;

import com.innercircle.survey.dto.request.SubmitResponseRequestDto;
import com.innercircle.survey.dto.request.SurveyCreateRequestDto;
import com.innercircle.survey.dto.request.SurveyUpdateRequestDto;
import com.innercircle.survey.dto.response.SurveyResponseDto;
import com.innercircle.survey.dto.response.SurveyResponseResponseDtos;
import com.innercircle.survey.exception.NotFoundException;
import com.innercircle.survey.model.Question;
import com.innercircle.survey.model.QuestionOption;
import com.innercircle.survey.model.Response;
import com.innercircle.survey.model.Survey;
import com.innercircle.survey.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;

@RequiredArgsConstructor
@Service
public class SurveyService {
    private final SurveyRepository surveyRepository;
    private final QuestionRepository questionRepository;
    private final QuestionOptionRepository questionOptionRepository;
    private final ResponseRepository responseRepository;

    private Survey findById(Long surveyId) {
        return surveyRepository.findById(surveyId)
                .orElseThrow(() -> new NotFoundException("Survey not found"));
    }

    @Transactional
    public SurveyResponseDto createSurvey(SurveyCreateRequestDto request) {
        Survey survey = request.toSurveyEntity();
        List<Question> questions = request.toQuestionEntities(survey);
        surveyRepository.save(survey);
        questionRepository.saveAll(questions);
        survey.setQuestions(questions);
        questions.forEach(question -> {
            List<QuestionOption> questionOptions = question.getQuestionOptions();
            if (!CollectionUtils.isEmpty(questionOptions)) {
                questionOptionRepository.saveAll(questionOptions);
            }
        });
        return SurveyResponseDto.fromEntity(survey);
    }
}

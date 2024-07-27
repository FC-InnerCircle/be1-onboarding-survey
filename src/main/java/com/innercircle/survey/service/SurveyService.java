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
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SurveyService {

    private final SurveyRepository surveyRepository;
    private final QuestionRepository questionRepository;
    private final OptionRepository optionRepository;

    @Transactional
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

    @Transactional
    public void updateSurvey(Long id, SurveyReq surveyReq) {
        Survey survey = surveyRepository.findById(id)
                .orElseThrow(() -> new CustomException(ErrorCode.SURVEY_NOT_FOUND));
        if (surveyReq.getQuestions().isEmpty()) {
            throw new CustomException(ErrorCode.QUESTION_NOT_FOUND);
        }
        survey.update(surveyReq.getTitle(), surveyReq.getDescription(), surveyReq.getEndAt());
        surveyRepository.save(survey);

        saveQuestionsAndOptions(survey, surveyReq.getQuestions());
    }

    private void saveQuestionsAndOptions(Survey survey, List<QuestionReq> questionDtos) {
        for (QuestionReq questionDto : questionDtos) {
            if (questionDto.getId() != null) {
                Question question = questionRepository.findById(questionDto.getId())
                        .orElseThrow(() -> new CustomException(ErrorCode.QUESTION_NOT_FOUND));
                if (Boolean.TRUE.equals(questionDto.getIsDeleted())) {
                    question.delete();
                    questionRepository.save(question);
                    continue;
                }
                question.delete();
                questionRepository.save(question);

                Question newQuestion = question.createNewVersion(questionDto.getText(),
                        QuestionType.valueOf(questionDto.getType()),
                        question.getVersion() + 1,
                        questionDto.getIsRequired());
                newQuestion = questionRepository.save(newQuestion);
                saveOptions(newQuestion, questionDto.getOptions());
            } else {
                Question question = Question.builder().survey(survey)
                        .type(QuestionType.valueOf(questionDto.getType())).text(questionDto.getText())
                        .isRequired(questionDto.getIsRequired()).build();
                questionRepository.save(question);
                saveOptions(question, questionDto.getOptions());
            }
        }
    }

    private void saveOptions(Question question, List<OptionReq> optionDtos) {
        List<Option> existingOptions = new ArrayList<>(question.getOptions());

        for (OptionReq optionDto : optionDtos) {
            if (optionDto.getId() != null) {
                Option option = optionRepository.findById(optionDto.getId())
                        .orElseThrow(() -> new CustomException(ErrorCode.OPTION_NOT_FOUND));
                option.update(optionDto.getText());
                existingOptions.remove(option);
            } else {
                Option option = Option.builder().question(question).text(optionDto.getText())
                        .build();
                optionRepository.save(option);
            }
        }

        for (Option option : existingOptions) {
            question.getOptions().remove(option);
            optionRepository.delete(option);
        }
    }
}

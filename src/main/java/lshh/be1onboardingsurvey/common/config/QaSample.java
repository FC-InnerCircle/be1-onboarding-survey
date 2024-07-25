package lshh.be1onboardingsurvey.common.config;

import lshh.be1onboardingsurvey.survey.domain.entity.SurveyItemFormType;
import lshh.be1onboardingsurvey.survey.domain.SurveyService;
import lshh.be1onboardingsurvey.survey.domain.command.AddSurveyItemCommand;
import lshh.be1onboardingsurvey.survey.domain.command.AddSurveyItemOptionCommand;
import lshh.be1onboardingsurvey.survey.domain.command.CreateSurveyCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class QaSample {

    public QaSample(@Autowired SurveyService surveyService) {

        // 테스트 샘플 survey 생성
        CreateSurveyCommand createSurveyCommand = new CreateSurveyCommand("테스트 설문지", "테스트 설문지입니다.");
        surveyService.create(createSurveyCommand);
        // 테스트 샘플 survey item 생성
        AddSurveyItemCommand addSurveyItemCommand_TEXT = new AddSurveyItemCommand(1L, "테스트 설문지 텍스트 항목", "테스트 설문지 항목입니다.", SurveyItemFormType.TEXT, true, 1L);
        surveyService.addItem(addSurveyItemCommand_TEXT);

        AddSurveyItemCommand addSurveyItemCommand_RADIO = new AddSurveyItemCommand(1L, "테스트 설문지 단일선택 항목", "테스트 설문지 항목입니다.", SurveyItemFormType.RADIO, true, 2L);
        surveyService.addItem(addSurveyItemCommand_RADIO);
        // 테스트 샘플 survey item option 생성
        AddSurveyItemOptionCommand addSurveyItemOptionCommand_RADIO1 = new AddSurveyItemOptionCommand(1L, 2L, "테스트 설문지 단일선택 항목 옵션1", "", 1L);
        surveyService.addItemOption(addSurveyItemOptionCommand_RADIO1);
        AddSurveyItemOptionCommand addSurveyItemOptionCommand_RADIO2 = new AddSurveyItemOptionCommand(1L, 2L, "테스트 설문지 단일선택 항목 옵션2", "", 2L);
        surveyService.addItemOption(addSurveyItemOptionCommand_RADIO2);

        AddSurveyItemCommand addSurveyItemCommand_CHECKBOX = new AddSurveyItemCommand(1L, "테스트 설문지 다중선택 항목", "테스트 설문지 항목입니다.", SurveyItemFormType.CHECKBOX, true, 3L);
        surveyService.addItem(addSurveyItemCommand_CHECKBOX);
        // 테스트 샘플 survey item option 생성
        AddSurveyItemOptionCommand addSurveyItemOptionCommand_CHECKBOX1 = new AddSurveyItemOptionCommand(1L, 3L, "테스트 설문지 다중선택 항목 옵션1", "", 1L);
        surveyService.addItemOption(addSurveyItemOptionCommand_CHECKBOX1);
        AddSurveyItemOptionCommand addSurveyItemOptionCommand_CHECKBOX2 = new AddSurveyItemOptionCommand(1L, 3L, "테스트 설문지 다중선택 항목 옵션2", "", 2L);
        surveyService.addItemOption(addSurveyItemOptionCommand_CHECKBOX2);


    }
}

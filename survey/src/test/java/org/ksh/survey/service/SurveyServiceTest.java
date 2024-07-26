package org.ksh.survey.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.ksh.common.model.BaseResponse;
import org.ksh.common.model.BaseStatusCode;
import org.ksh.survey.entity.SurveyTemplate;
import org.ksh.survey.entity.SurveyTemplateItem;
import org.ksh.survey.model.SurveyInputType;
import org.ksh.survey.model.SurveySaveRequest;
import org.ksh.survey.repository.SurveyTemplateItemRepository;
import org.ksh.survey.repository.SurveyTemplateRepository;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SurveyServiceTest {
	@Mock
	private SurveyTemplateRepository surveyTemplateRepository;

	@Mock
	private SurveyTemplateItemRepository surveyTemplateItemRepository;

	@InjectMocks
	private SurveyTemplateService surveyTemplateService;

	private SurveySaveRequest surveySaveRequest;
	private SurveyTemplate surveyTemplate;
	private List<SurveyTemplateItem> surveyTemplateItems;

	@BeforeEach
	void setUp() {
		surveyTemplate = SurveyTemplate.builder()
				.id(1L)
				.name("고객 만족 설문조사")
				.description("고객 피드백")
				.build();

		surveyTemplateItems = List.of(
				SurveyTemplateItem.builder()
						.id(1L)
						.name("만족도")
						.description("만족하는 상황")
						.surveyInputType(SurveyInputType.SINGLE_CHOICE)
						.isRequired(true)
						.surveyTemplate(surveyTemplate)
						.build(),
				SurveyTemplateItem.builder()
						.id(2L)
						.name("한마디")
						.description("고객의 한마디")
						.surveyInputType(SurveyInputType.LONG_TEXT)
						.isRequired(false)
						.surveyTemplate(surveyTemplate)
						.build()
		);

		surveySaveRequest = mock(SurveySaveRequest.class);
		when(surveySaveRequest.createSurveyTemplate()).thenReturn(surveyTemplate);
		when(surveySaveRequest.createSurveyTemplateItems(any(SurveyTemplate.class))).thenReturn(surveyTemplateItems);
	}

	@Test
	void 설문조사_결과저장_테스트() {
		when(surveyTemplateRepository.save(any(SurveyTemplate.class))).thenReturn(surveyTemplate);
		when(surveyTemplateItemRepository.saveAll(anyList())).thenReturn(surveyTemplateItems);

		BaseResponse response = surveyTemplateService.saveSurveyTemplate(surveySaveRequest);

		verify(surveyTemplateRepository, times(1)).save(any(SurveyTemplate.class));
		verify(surveyTemplateItemRepository, times(1)).saveAll(anyList());
		assertEquals(BaseStatusCode.SUCCESS, response.getBody());
	}

}

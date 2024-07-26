package org.ksh.survey.model;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import org.ksh.survey.entity.SurveyReply;
import org.ksh.survey.entity.SurveyTemplate;

import java.util.List;

@Getter
public class SurveyReplyRequest {
    @NotBlank(message = "설문 Id가 없습니다.")
    private long surveyId;

    private String subjectName;

    private List<SurveyReplyItemRequest> surveyItemIds;

    public SurveyReply createSurveyReply(SurveyTemplate surveyTemplate) {
        return SurveyReply.builder()
                .subjectName(subjectName)
                .surveyTemplate(surveyTemplate)
                .version(surveyTemplate.getVersion())
                .build();
    }

}

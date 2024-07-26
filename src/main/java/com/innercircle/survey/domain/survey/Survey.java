package com.innercircle.survey.domain.survey;

import com.innercircle.survey.domain.survey.command.SurveyCommand;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Builder(toBuilder = true)
@AllArgsConstructor
public class Survey {

    private Long surveyId;

    private String surveyName;

    private String surveyDescription;

    private List<SurveyItem> surveyItems;

    private LocalDateTime createdAt;

    public void setSurveyItem(List<SurveyItem> surveyItems) {
        this.surveyItems = surveyItems;
    }

    public static Survey toSurveyDomain(SurveyCommand.Create command) {
        return Survey.builder()
                .surveyName(command.surveyName())
                .surveyDescription(command.surveyDescription())
                .build();

    }
}

package lshh.be1onboardingsurvey.survey.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lshh.be1onboardingsurvey.survey.domain.command.AddSurveyItemCommand;
import lshh.be1onboardingsurvey.survey.domain.command.AddSurveyItemOptionCommand;
import lshh.be1onboardingsurvey.survey.domain.command.CreateSurveyCommand;
import lshh.be1onboardingsurvey.survey.domain.command.UpdateSurveyItemCommand;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Entity
public class Survey {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String name;
    String description;

    @OneToMany(mappedBy = "survey", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    List<SurveyItem> items;

    public static Survey of(CreateSurveyCommand command) {
        return Survey.builder()
                .name(command.name())
                .description(command.description())
                .build();
    }

    public void addItem(AddSurveyItemCommand command) {
        SurveyItem surveyItem = command.toEntity();
        surveyItem.setSurvey(this);
        items.add(surveyItem);
    }

    public void updateItem(AddSurveyItemOptionCommand command) {
        // todo
    }

    public void updateItem(UpdateSurveyItemCommand command){
        // todo
    }


}

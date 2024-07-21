package lshh.be1onboardingsurvey.survey.domain;

import jakarta.persistence.*;
import lombok.*;
import lshh.be1onboardingsurvey.survey.domain.command.AddSurveyResponseItemCommand;

import java.util.ArrayList;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class SurveyResponse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Setter
    @ManyToOne
    Survey survey;

    @OneToMany(mappedBy = "surveyResponse", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    List<SurveyResponseItem> items;

    public void addItem(AddSurveyResponseItemCommand command, SurveyItemFormType type) {
        SurveyResponseItem surveyResponseItem = command.toEntity(type);
        surveyResponseItem.setSurveyResponse(this);
        if(this.items == null){
            this.items = new ArrayList<>();
        }
        items.add(surveyResponseItem);
    }
}

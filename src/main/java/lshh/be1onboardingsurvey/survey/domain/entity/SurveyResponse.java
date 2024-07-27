package lshh.be1onboardingsurvey.survey.domain.entity;

import jakarta.persistence.*;
import lombok.*;
import lshh.be1onboardingsurvey.survey.domain.command.AddSurveyResponseItemCommand;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class SurveyResponse {
    @Id
    @Setter
    Long id;

    @Setter
    LocalDateTime submitted;

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

        if(existsResponseItem(surveyResponseItem.getSurveyItemId())){
            updateItem(surveyResponseItem);
        }else{
            this.items.add(surveyResponseItem);
        }
    }

    public boolean existsResponseItem(Long surveyItemId) {
        return this.items.stream()
                .anyMatch(item -> item.getSurveyItemId().equals(surveyItemId));
    }

    public Optional<SurveyResponseItem> findResponseItemBySurveyId(Long surveyItemId) {
        return this.items.stream()
                .filter(item -> item.getSurveyItemId().equals(surveyItemId))
                .findFirst();
    }

    public void updateItem(SurveyResponseItem item){
        findResponseItemBySurveyId(item.getSurveyItemId())
                .ifPresent(responseItem -> responseItem.update(item));
    }
}

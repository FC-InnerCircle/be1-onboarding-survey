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
import java.util.Optional;


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

    public Optional<SurveyItem> findItem(Long id){
        return this.items.stream()
                .filter(i -> i.getId().equals(id))
                .findFirst();
    }

    public Optional<SurveyItem> findItemBySequence(Long sequence){
        return this.items.stream()
                .filter(i -> i.getSequence().equals(sequence) && i.getOverridden() == null)
                .findFirst();
    }

    public void updateItem(AddSurveyItemOptionCommand command) {
        SurveyItem surveyItem = findItem(command.itemId())
                .orElseThrow(() -> new IllegalArgumentException("Survey item not found"));
        surveyItem.addItemOption(command);
    }

    public void updateItem(UpdateSurveyItemCommand command){
        SurveyItem latestItem = findItem(command.itemId())
                .orElseThrow(() -> new IllegalArgumentException("Survey item not found"));

        SurveyItem newItem = command.toEntity();
        switch(command.form()){
            case TEXT:
            case TEXTAREA:
                break;
            case RADIO:
            case CHECKBOX:
                List<SurveyItemOption> options = latestItem.getOptions();
                newItem.addItemOptions(options);
                break;
        }

        latestItem.setOverridden();
        newItem.setSurvey(this);
        this.items.add(newItem);
    }


}

package lshh.be1onboardingsurvey.survey.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lshh.be1onboardingsurvey.common.lib.clock.Clock;
import lshh.be1onboardingsurvey.survey.domain.command.*;

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

    @OneToMany(mappedBy = "survey", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    List<SurveyResponse> responses;

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

    public Optional<SurveyItem> findItemByPreId(Long preId){
        return this.items.stream()
                .filter(item -> item.getPreId() != null && item.getPreId().equals(preId))
                .findFirst();
    }

    public Optional<SurveyItem> findLatestItem(Long id){
        return findItem(id)
                .map(item -> {
                    while(item.getOverridden() != null){
                        item = findItemByPreId(item.getId())
                                .orElseThrow(() -> new IllegalArgumentException("Survey latest item not found"));
                    }
                    return item;
                });
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

    public void updateItem(UpdateSurveyItemCommand command, Clock clock){
        SurveyItem latestItem = findLatestItem(command.itemId())
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

        latestItem.setOverridden(clock);
        newItem.setSurvey(this);
        this.items.add(newItem);
    }


    public void updateItem(UpdateSurveyItemOptionCommand command, Clock clock) {
        SurveyItem surveyItem = findItem(command.itemId())
                .orElseThrow(() -> new IllegalArgumentException("Survey item not found"));
        surveyItem.updateItemOption(command, clock);
    }

    public void addResponse(AddSurveyResponseCommand command) {
        SurveyResponse surveyResponse = command.toEntity();
        surveyResponse.setSurvey(this);
        responses.add(surveyResponse);
    }

    public Optional<SurveyResponse> findResponse(Long id){
        return this.responses.stream()
                .filter(response -> response.getId().equals(id))
                .findFirst();
    }

    public void addResponseItem(AddSurveyResponseItemCommand command) {
        SurveyResponse surveyResponse = findResponse(command.responseId())
                .orElseThrow(() -> new IllegalArgumentException("Survey response not found"));
        SurveyItem surveyItem = findItem(command.itemId())
                .orElseThrow(() -> new IllegalArgumentException("Survey item not found"));
        surveyResponse.addItem(command, surveyItem.getFormType());
    }
}

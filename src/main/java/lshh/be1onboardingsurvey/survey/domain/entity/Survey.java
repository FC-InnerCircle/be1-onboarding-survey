package lshh.be1onboardingsurvey.survey.domain.entity;

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
            case TEXT, TEXTAREA -> {}
            case RADIO, CHECKBOX -> {
                List<SurveyItemOption> options = latestItem.getOptions().stream().map(option -> option.toCopy(newItem)).toList();
                newItem.addItemOptions(options);
            }
        }
        newItem.setPreId(latestItem.getId());
        latestItem.setOverridden(clock);
        newItem.setSurvey(this);
        this.items.add(newItem);
    }


    public void updateItem(UpdateSurveyItemOptionCommand command, Clock clock) {
        SurveyItem surveyItem = findItem(command.itemId())
                .orElseThrow(() -> new IllegalArgumentException("Survey item not found"));
        surveyItem.updateItemOption(command, clock);
    }

    public void addResponse(SurveyResponse response) {
        if(!isReadyToSubmitResponse(response)){
            throw new IllegalArgumentException("Survey response is not ready to submit");
        }
        response.setSurvey(this);
        responses.add(response);
    }

    public boolean isReadyToSubmitResponse(SurveyResponse response){
        if(this.items == null || this.items.isEmpty()){
            return true;
        }
        for(SurveyItem item : this.items){
            if(item.getRequired() != null && item.getRequired() && !response.existsResponseItem(item.getId())){
                return false;
            }
        }
        return true;
    }

    public Optional<SurveyResponse> findResponse(Long id){
        return this.responses.stream()
                .filter(response -> response.getId().equals(id))
                .findFirst();
    }
}

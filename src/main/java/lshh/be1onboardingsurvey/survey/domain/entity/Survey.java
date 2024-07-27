package lshh.be1onboardingsurvey.survey.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lshh.be1onboardingsurvey.common.lib.clock.Clock;
import lshh.be1onboardingsurvey.common.lib.diff.Diff;
import lshh.be1onboardingsurvey.survey.domain.command.*;
import lshh.be1onboardingsurvey.survey.domain.vo.Version;

import java.time.LocalDateTime;
import java.util.ArrayList;
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

    public void addItem(AddSurveyItemCommand command, LocalDateTime now) {
        SurveyItem surveyItem = command.toEntity();
        surveyItem.setVersion(Version.forCreate(now));
        surveyItem.setSurvey(this);
        if(this.items == null){
            this.items = new ArrayList<>();
        }
        items.add(surveyItem);
    }

    public Optional<SurveyItem> findItem(Long id){
        return this.items.stream()
                .filter(i -> i.getId().equals(id))
                .findFirst();
    }

    public Optional<SurveyItem> findItemByPreId(Long preId){
        return this.items.stream()
                .filter(item -> item.getVersion().previous() != null && item.getVersion().previous().getId().equals(preId))
                .findFirst();
    }

    public Optional<SurveyItem> findLatestItem(Long id){
        return findItem(id)
                .map(item -> {
                    while(item.getVersion().overwritten() != null){
                        item = findItemByPreId(item.getId())
                                .orElseThrow(() -> new IllegalArgumentException("Survey latest item not found"));
                    }
                    return item;
                });
    }

    public List<SurveyItem> findItemByName(String name){
        return this.items.stream()
                .filter(item -> item.getName().equals(name))
                .toList();
    }

    public Optional<SurveyItem> findItemBySequence(Long sequence){
        return this.items.stream()
                .filter(item -> item.getSequence().equals(sequence)
                        && item.getVersion().overwritten() == null)
                .findFirst();
    }

    public void updateItem(AddSurveyItemOptionCommand command, LocalDateTime now) {
        SurveyItem surveyItem = findItem(command.itemId())
                .orElseThrow(() -> new IllegalArgumentException("Survey item not found"));
        surveyItem.addItemOption(command, now);
    }

    public void updateItem(UpdateSurveyItemCommand command, Clock clock){
        SurveyItem latestItem = findLatestItem(command.itemId())
                .orElseThrow(() -> new IllegalArgumentException("Survey item not found"));
        SurveyItem newItem = command.toEntity();
        if(Diff.of(latestItem, newItem).isNotChanged()){
            return;
        }
        switch(command.form()){
            case TEXT, TEXTAREA -> {}
            case RADIO, CHECKBOX -> {
                List<SurveyItemOption> options = latestItem.getOptions().stream().map(option -> option.toCopy(newItem)).toList();
                newItem.addItemOptions(options);
            }
        }
        newItem.setVersion(Version.forUpdate(latestItem, clock.now()));
        latestItem.setVersion(Version.forOverwriten(latestItem.getVersion(), clock.now()));
        newItem.setSurvey(this);
        this.items.add(newItem);
    }

    public void updateItem(UpdateSurveyItemOptionCommand command, LocalDateTime now) {
        SurveyItem surveyItem = findItem(command.itemId())
                .orElseThrow(() -> new IllegalArgumentException("Survey item not found"));
        surveyItem.updateItemOption(command, now);
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

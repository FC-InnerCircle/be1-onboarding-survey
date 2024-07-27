package lshh.be1onboardingsurvey.survey.domain.entity;

import jakarta.persistence.*;
import lombok.*;
import lshh.be1onboardingsurvey.common.lib.diff.Diff;
import lshh.be1onboardingsurvey.common.lib.jpa.BooleanConverter;
import lshh.be1onboardingsurvey.survey.domain.command.AddSurveyItemOptionCommand;
import lshh.be1onboardingsurvey.survey.domain.command.UpdateSurveyItemOptionCommand;
import lshh.be1onboardingsurvey.survey.domain.vo.Version;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class SurveyItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String name;
    String description;
    @Enumerated(EnumType.STRING)
    SurveyItemFormType formType;
    @Convert(converter = BooleanConverter.class)
    Boolean required;
    Long sequence;

    @Setter
    @Embedded
    Version<SurveyItem> version;
  
    @Setter
    @ManyToOne
    Survey survey;

    @OneToMany(mappedBy = "surveyItem", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    List<SurveyItemOption> options;

    public boolean isOverridden(){
        return this.version.overwritten() != null;
    }

    public void addItemOption(AddSurveyItemOptionCommand command, LocalDateTime now) {
        if(
            this.formType != SurveyItemFormType.RADIO
            && this.formType != SurveyItemFormType.CHECKBOX
        ){
            throw new IllegalArgumentException("Survey item is not a select type");
        }
        SurveyItemOption surveyItemOption = command.toEntity();
        surveyItemOption.setSurveyItem(this);
        if(this.options == null){
            this.options = new ArrayList<>();
        }
        surveyItemOption.setVersion(Version.forCreate(now));
        options.add(surveyItemOption);
    }

    public void addItemOptions(List<SurveyItemOption> options) {
        if(this.options == null){
            this.options = new ArrayList<>();
        }
        this.options.addAll(options);
    }

    public void updateItemOption(UpdateSurveyItemOptionCommand command, LocalDateTime now) {
        SurveyItemOption latest = this.findLatestOption(command.optionId())
                .orElseThrow(() -> new IllegalArgumentException("Survey item option not found"));
        SurveyItemOption newOption = command.toEntity();
        if(Diff.of(latest, newOption).isNotChanged()){
            return;
        }
        latest.setVersion(Version.forOverwriten(latest.getVersion(), now));
        newOption.setSurveyItem(this);
        newOption.setVersion(Version.forUpdate(latest, now));
        this.options.add(newOption);
    }

    public Optional<SurveyItemOption> findOption(Long id){
        return this.options.stream()
                .filter(o -> o.getId().equals(id))
                .findFirst();
    }

    public Optional<SurveyItemOption> findItemByPreId(Long preId){
        return this.options.stream()
                .filter(item -> item.getVersion().previous() != null && item.getVersion().previous().getId().equals(preId))
                .findFirst();
    }

    public Optional<SurveyItemOption> findLatestOption(Long id){
        return findOption(id)
                .map(item -> {
                    while(item.getVersion().overwritten() != null){
                        item = findItemByPreId(item.getId())
                                .orElseThrow(() -> new IllegalArgumentException("Survey latest item not found"));
                    }
                    return item;
                });
    }

    public List<SurveyItemOption> findOptionByName(String name) {
        return this.options.stream()
                .filter(option -> option.getName().equals(name))
                .toList();
    }
}

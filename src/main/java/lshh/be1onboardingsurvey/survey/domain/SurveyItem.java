package lshh.be1onboardingsurvey.survey.domain;

import jakarta.persistence.*;
import lombok.*;
import lshh.be1onboardingsurvey.common.lib.clock.Clock;
import lshh.be1onboardingsurvey.common.lib.jpa.BooleanConverter;
import lshh.be1onboardingsurvey.survey.domain.command.AddSurveyItemOptionCommand;
import lshh.be1onboardingsurvey.survey.domain.command.UpdateSurveyItemOptionCommand;

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
    SurveyItemForm form;
    @Convert(converter = BooleanConverter.class)
    Boolean required;
    Long sequence;

    LocalDateTime overridden;
  
    @Setter
    @ManyToOne
    Survey survey;

    @OneToMany(mappedBy = "surveyItem", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    List<SurveyItemOption> options;

    public void addItemOption(AddSurveyItemOptionCommand command) {
        if(
            this.form != SurveyItemForm.RADIO
            && this.form != SurveyItemForm.CHECKBOX
        ){
            throw new IllegalArgumentException("Survey item is not a select type");
        }
        SurveyItemOption surveyItemOption = command.toEntity();
        surveyItemOption.setSurveyItem(this);
        if(this.options == null){
            this.options = new ArrayList<>();
        }
        options.add(surveyItemOption);
    }

    public void setOverridden(Clock clock) {
        this.overridden = clock.now();
    }

    public void addItemOptions(List<SurveyItemOption> options) {
        if(this.options == null){
            this.options = new ArrayList<>();
        }
        this.options.addAll(options);
    }

    public void updateItemOption(UpdateSurveyItemOptionCommand command, Clock clock) {
        SurveyItemOption latest = this.findOption(command.optionId())
                .orElseThrow(() -> new IllegalArgumentException("Survey item option not found"));

        SurveyItemOption newOption = command.toEntity();
        latest.setOverridden(clock);
        newOption.setSurveyItem(this);
    }

    public Optional<SurveyItemOption> findOption(Long id){
        return this.options.stream()
                .filter(o -> o.getId().equals(id))
                .findFirst();
    }
}

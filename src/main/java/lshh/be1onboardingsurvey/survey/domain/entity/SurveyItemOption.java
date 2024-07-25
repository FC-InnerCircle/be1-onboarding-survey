package lshh.be1onboardingsurvey.survey.domain.entity;

import jakarta.persistence.*;
import lombok.*;
import lshh.be1onboardingsurvey.common.lib.clock.Clock;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder

@Entity
public class SurveyItemOption {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String name;
    String description;
    Long sequence;
    LocalDateTime overridden;
    @Setter
    Long preId;

    @Setter
    @ManyToOne
    SurveyItem surveyItem;

    public void setOverridden(Clock clock) {
        this.overridden = clock.now();
    }

    public SurveyItemOption toCopy(SurveyItem surveyItem) {
        return SurveyItemOption.builder()
                .name(this.name)
                .description(this.description)
                .sequence(this.sequence)
                .surveyItem(surveyItem)
                .build();
    }

}

package lshh.be1onboardingsurvey.survey.domain;

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
    @ManyToOne
    SurveyItem surveyItem;

    public void setOverridden(Clock clock) {
        this.overridden = clock.now();
    }
}

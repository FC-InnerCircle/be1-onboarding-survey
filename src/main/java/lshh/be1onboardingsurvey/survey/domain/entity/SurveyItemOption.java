package lshh.be1onboardingsurvey.survey.domain.entity;

import jakarta.persistence.*;
import lombok.*;
import lshh.be1onboardingsurvey.survey.domain.vo.Version;

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

    @Setter
    @Embedded
    Version<SurveyItemOption> version;

    @Setter
    @ManyToOne
    SurveyItem surveyItem;

    public SurveyItemOption toCopy(SurveyItem surveyItem) {
        return SurveyItemOption.builder()
                .name(this.name)
                .description(this.description)
                .sequence(this.sequence)
                .surveyItem(surveyItem)
                .version(this.version)
                .build();
    }

}

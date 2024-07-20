package lshh.be1onboardingsurvey.survey.domain;

import jakarta.persistence.*;
import lombok.*;
import lshh.be1onboardingsurvey.common.lib.jpa.BooleanConverter;

import java.time.LocalDateTime;
import java.util.List;

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

    LocalDateTime overrided;

    @Setter
    @ManyToOne
    Survey survey;

    @OneToMany(mappedBy = "surveyItem", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    List<SurveyItemOption> options;
}

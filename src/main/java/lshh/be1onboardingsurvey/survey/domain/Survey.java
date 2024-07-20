package lshh.be1onboardingsurvey.survey.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lshh.be1onboardingsurvey.survey.domain.command.CreateSurveyCommand;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Entity
public class Survey {
    @Id
    private Long id;
    private String name;
    private String description;

    public static Survey of(CreateSurveyCommand command) {
        return Survey.builder()
                .name(command.name())
                .description(command.description())
                .build();
    }
}

package fastcampus.innercircle.onboarding.survey.domain;

import jakarta.persistence.*;
import lombok.*;

@ToString(exclude = {"question"})
@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "SURVEY_QUESTION_OPTION")
public class SurveyQuestionOption {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "OPTION_ID")
    private Long id;

    @Column(name = "OPTION_NAME")
    private String name;

    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "QUESTION_ID")
    private SurveyQuestion question;

    @Builder
    public SurveyQuestionOption(final SurveyQuestion question, final String name) {
        this.question = question;
        this.name = name;
    }
}

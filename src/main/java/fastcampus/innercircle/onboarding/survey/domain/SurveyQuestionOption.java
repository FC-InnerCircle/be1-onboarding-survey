package fastcampus.innercircle.onboarding.survey.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "SURVEY_QUESTION_OPTION")
public class SurveyQuestionOption {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "OPTION_ID")
    private Long id;

    @Column(name = "OPTION_NAME")
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "QUESTION_ID")
    private SurveyQuestion question;
}

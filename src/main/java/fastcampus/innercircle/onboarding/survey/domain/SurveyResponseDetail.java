package fastcampus.innercircle.onboarding.survey.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "SURVEY_RESPONSE_DETAIL")
public class SurveyResponseDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "RESPONSE_DETAIL_ID")
    private Long id;

    @Column(name = "TEXT")
    private String text;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "RESPONSE_ID")
    private SurveyResponse response;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "QUESTION_ID")
    private SurveyQuestion question;
}

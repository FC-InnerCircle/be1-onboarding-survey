package fastcampus.innercircle.onboarding.survey.domain;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "SURVEY_RESPONSE")
public class SurveyResponse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "RESPONSE_ID")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "FORM_ID")
    private SurveyForm form;

    @OneToMany(mappedBy = "response")
    private List<SurveyResponseDetail> details;
}

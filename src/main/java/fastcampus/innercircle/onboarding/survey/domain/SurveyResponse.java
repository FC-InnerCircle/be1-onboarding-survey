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
    @JoinColumns({
            @JoinColumn(name = "FORM_ID", referencedColumnName = "FORM_ID"),
            @JoinColumn(name = "VERSION", referencedColumnName = "VERSION")
    })
    private SurveyForm form;

    @OneToMany(mappedBy = "response")
    private List<SurveyResponseDetail> details;
}

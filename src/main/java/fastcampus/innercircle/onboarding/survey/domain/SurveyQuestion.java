package fastcampus.innercircle.onboarding.survey.domain;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "SURVEY_QUESTION")
public class SurveyQuestion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "QUESTION_ID")
    private Long id;

    @Column(name = "FORM_VERSION")
    private Long version;

    @Enumerated(EnumType.STRING)
    @Column(name = "RESPONSE_TYPE")
    private SurveyResponseType responseType;

    @Column(name = "QUESTION_TITLE")
    private String title;

    @Column(name = "QUESTION_DESC")
    private String desc;

    @Column(name = "CREATE_AT")
    private LocalDateTime createAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "FORM_ID")
    private SurveyForm form;

    @OneToMany(mappedBy = "question")
    private List<SurveyQuestionOption> options = new ArrayList<>();
}

package fastcampus.innercircle.onboarding.survey.domain;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "SURVEY_FORM")
public class SurveyForm {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "FORM_ID")
    private Long id;

    @Column(name = "FORM_VERSION")
    private Long version;

    @Column(name = "FORM_TITLE")
    private String title;

    @Column(name = "FORM_DESC")
    private String desc;

    @Column(name = "CREATE_AT")
    private LocalDateTime createAt;

    @OneToMany(mappedBy = "form")
    private List<SurveyQuestion> questions = new ArrayList<>();

    @OneToMany(mappedBy = "form")
    private List<SurveyResponse> responses = new ArrayList<>();
}

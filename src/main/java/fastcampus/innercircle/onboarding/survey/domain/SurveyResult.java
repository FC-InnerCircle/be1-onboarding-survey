package fastcampus.innercircle.onboarding.survey.domain;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "SURVEY_RESULT")
public class SurveyResult {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "RESULT_ID")
    private Long id;

    @Column(name = "CREATE_AT")
    private LocalDateTime createAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns({
            @JoinColumn(name = "FORM_ID", referencedColumnName = "FORM_ID"),
            @JoinColumn(name = "VERSION", referencedColumnName = "VERSION")
    })
    private SurveyForm form;

    @OneToMany(mappedBy = "result", cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<SurveyResultDetail> details = new ArrayList<>();
}

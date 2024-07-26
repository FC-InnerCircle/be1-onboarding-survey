package com.example.innercircle_survey.entity;

import com.example.innercircle_survey.dto.ResponseDTO;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Entity
@Getter @Setter
@Builder
@RequiredArgsConstructor
public class Response {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "response_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "survey_version_id")
    private SurveyVersion surveyVersion;

    @OneToMany(mappedBy = "response")
    private List<Answer> answers;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    public static Response create(ResponseDTO dto) {
        Response response = new Response();

        List<Answer> answers = dto.getAnswers().stream()
                .map(answerDTO -> {
                    Answer answer = Answer.create(answerDTO);
                    answer.setResponse(response);
                    return answer;
                }).toList();

        response.setAnswers(answers);

        return response;
    }
}

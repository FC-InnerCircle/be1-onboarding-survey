package com.example.innercircle_survey.entity;

import com.example.innercircle_survey.dto.AnswerDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Getter @Setter
@RequiredArgsConstructor
public class Answer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "answer_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "question_version_id")
    private QuestionVersion questionVersion;

    @ManyToOne
    @JoinColumn(name = "response_id")
    private Response response;

    // 단답형, 장문형, 단일선택리스트, 다중선택리스트 모두 AnswerOption 사용
    @OneToMany(mappedBy = "answer")
    private List<AnswerOption> answerOptions = new ArrayList<>();

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    public static Answer create(AnswerDTO dto) {
        Answer answer = new Answer();

        List<AnswerOption> options = dto.getOptions().stream()
                .map(text -> {
                    AnswerOption option = AnswerOption.create(text);
                    option.setAnswer(answer);
                    return option;
                })
                .toList();

        answer.setAnswerOptions(options);

        return answer;
    }
}

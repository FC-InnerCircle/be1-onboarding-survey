package com.example.innercircle_survey.dto;

import com.example.innercircle_survey.entity.Answer;
import com.example.innercircle_survey.entity.Response;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class ResponseDTO {

    private List<AnswerDTO> answers;

    public static ResponseDTO create(Response entity) {
        ResponseDTO dto = new ResponseDTO();
        List<AnswerDTO> answers = new ArrayList<>();
        for(Answer answer : entity.getAnswers()) {
            AnswerDTO answerDTO = AnswerDTO.create(answer);
            answers.add(answerDTO);
        }
        return dto;
    }
}

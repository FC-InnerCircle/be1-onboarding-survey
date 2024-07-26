package com.innercircle.survey.domain.survey.response;


import com.innercircle.survey.domain.survey.question.MultipleSelectOption;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@Table(name = "multiple_select_responses")
public class MultipleSelectResponse extends QuestionResponse{

    @OneToMany(mappedBy = "response")
    private List<MultipleSelectResponseOption> selectOptions = new ArrayList<>();
}

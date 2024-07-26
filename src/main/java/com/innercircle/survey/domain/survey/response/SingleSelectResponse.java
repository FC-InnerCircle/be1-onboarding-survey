package com.innercircle.survey.domain.survey.response;

import com.innercircle.survey.domain.survey.question.SingleSelectOption;
import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
@Table(name = "single_select_response")
public class SingleSelectResponse extends QuestionResponse{

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "selected_option_id")
    private SingleSelectOption selectOption;
}

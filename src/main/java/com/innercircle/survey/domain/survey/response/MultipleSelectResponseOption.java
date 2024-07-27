package com.innercircle.survey.domain.survey.response;

import com.innercircle.survey.domain.AbstractEntity;
import com.innercircle.survey.domain.survey.question.MultipleSelectOption;
import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
@Table(name = "multiple_select_response_options")
public class MultipleSelectResponseOption extends AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "response_id")
    private MultipleSelectResponse response;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "option_id")
    private MultipleSelectOption option;
}

package com.innercircle.survey.dto.survey;

import lombok.*;

import java.util.List;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class QuestionReq {

    private String type;
    private String text;
    private boolean isRequired;
    private Integer order;
    private List<OptionReq> options;

}

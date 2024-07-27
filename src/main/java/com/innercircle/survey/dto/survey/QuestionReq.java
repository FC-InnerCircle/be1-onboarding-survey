package com.innercircle.survey.dto.survey;

import lombok.*;

import java.util.List;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class QuestionReq {

    private Long id;
    private String type;
    private String text;
    private Boolean isRequired;
    private Integer version;
    private Boolean isDeleted;
    private List<OptionReq> options;

}

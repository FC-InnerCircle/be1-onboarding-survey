package com.innercircle.survey.dto.survey;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SurveyReq {

    private String title;
    private String description;
    private LocalDateTime endAt;

    private List<QuestionReq> questions;

}


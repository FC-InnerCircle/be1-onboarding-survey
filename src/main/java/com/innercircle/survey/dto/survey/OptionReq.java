package com.innercircle.survey.dto.survey;

import lombok.*;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OptionReq {

    private Long id;
    private String text;

}

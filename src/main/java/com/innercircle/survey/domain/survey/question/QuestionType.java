package com.innercircle.survey.domain.survey.question;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum QuestionType {
    SHORT_ANSWER("단답형 질문"),
    LONG_ANSWER("장문형 질문"),
    SINGLE_SELECT("단일 선택형 질문"),
    MULTIPLE_SELECT("다중 선택형 질문");

    private final String desc;
}

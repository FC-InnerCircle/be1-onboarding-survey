package com.innercircle.onboardingservey.domain.model;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum QuestionType {
    SHORT_TEXT(
        "단답형",
        false
    ),
    LONG_TEXT(
        "장문형",
        false
    ),
    SINGLE_CHOICE(
        "단일 선택 리스트",
        true
    ),
    MULTIPLE_CHOICE(
        "다중 선택 리스트",
        true
    ),
    ;

    private final String description;
    private final boolean hasOption;

    public boolean isText() {
        return hasOption == Boolean.FALSE;
    }

    public boolean isChoice() {
        return hasOption == Boolean.TRUE;
    }
}

package com.innercircle.project_one.survey.common;

public enum SurveyObjectDataType {
    TEXT(Values.TEXT),
    RICH_TEXT(Values.RICH_TEXT),
    RADIO(Values.RADIO),
    CHECK_BOX(Values.CHECK_BOX);

    SurveyObjectDataType(String val) {
        if (!this.name().equals(val))
            throw new IllegalArgumentException("Incorrect SurveyObjectDataType: " + val);
    }


    public static SurveyObjectDataType of(String val) {
        if (val == null) {
            throw new IllegalArgumentException("잘못된 요청입니다.");
        }
        try {
            return SurveyObjectDataType.valueOf(val.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("적절한 타입이 아닙니다: " + val);
        }
    }

    public boolean isElementDataType() {
        return this == RADIO || this == CHECK_BOX;
    }

    public static class Values {
        public static final String TEXT = "TEXT";
        public static final String RICH_TEXT = "RICH_TEXT";
        public static final String CHECK_BOX = "CHECK_BOX";
        public static final String RADIO = "RADIO";
    }

}

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

    public static class Values {
        public static final String TEXT = "TEXT";
        public static final String RICH_TEXT = "RICH_TEXT";
        public static final String CHECK_BOX = "CHECK_BOX";
        public static final String RADIO = "RADIO";
    }

}

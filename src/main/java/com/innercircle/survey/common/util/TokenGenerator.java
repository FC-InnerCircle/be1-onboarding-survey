package com.innercircle.survey.common.util;

import org.apache.commons.lang3.RandomStringUtils;

public class TokenGenerator {
    private static final int TOKEN_LENGTH = 20;

    public static String randomCharacter(int length) {
        return RandomStringUtils.randomAlphanumeric(length);
    }

    public static String randomCharacterWithPrefix(String prefix) {
        return prefix + RandomStringUtils.randomAlphanumeric(TOKEN_LENGTH - prefix.length());
    }
}

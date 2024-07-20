package com.innercircle.onboardingservey.common;

import java.util.List;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

public class ValidationUtils {
    public static String assertHasText(String text, String message) {
        if (!StringUtils.hasText(text)) {
            throw new IllegalArgumentException(message);
        }
        return text;
    }
    public static <T> T assertNotNull(T target, String message) {
        if (target == null) {
            throw new IllegalArgumentException(message);
        }
        return target;
    }

    public static <T> List<T> assertHasCollection(List<T> target, String message) {
        if (CollectionUtils.isEmpty(target)) {
            throw new IllegalArgumentException(message);
        }
        return target;
    }
}

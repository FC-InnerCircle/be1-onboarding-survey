package lshh.be1onboardingsurvey.survey.controller;

import lshh.be1onboardingsurvey.survey.domain.dto.Result;

public record Response<T>(
        Result.Status status,
        T data
) {
    public static <T> Response<T> of(Result<T> result) {
        return new Response<>(result.status(), result.data());
    }
    public static <T> Response<T> of(Result.Status status, T data) {
        return new Response<>(status, data);
    }
}

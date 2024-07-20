package lshh.be1onboardingsurvey.survey.controller;

import lshh.be1onboardingsurvey.survey.domain.dto.Result;

public record Response<T>(
        Result.Status status,
        T data
) {
    public static Response of(Result result) {
        return new Response(result.status(), result.data());
    }
    public static Response of(Result.Status status, Object data) {
        return new Response(status, data);
    }
}

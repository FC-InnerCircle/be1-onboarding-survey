package lshh.be1onboardingsurvey.survey.controller;

import lshh.be1onboardingsurvey.survey.domain.dto.Result;

public record Response<T>(Result<T> result) {
    public static Response of(Result result) {
        return new Response(result);
    }
}

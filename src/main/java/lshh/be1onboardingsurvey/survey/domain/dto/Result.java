package lshh.be1onboardingsurvey.survey.domain.dto;

public record  Result <T>(
    Status status,
    T data
){

    public static Result success() {
        return new Result(Status.SUCCESS, null);
    }

    enum Status {
        OK,
        SUCCESS,
        ERROR
    }
}


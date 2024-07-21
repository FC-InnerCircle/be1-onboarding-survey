package lshh.be1onboardingsurvey.survey.domain.dto;

public record  Result <T>(
    Status status,
    T data
){

    public static Result<String> success() {
        return new Result<>(Status.SUCCESS, "Success");
    }
    public static <T> Result<T> success(T data) {
        return new Result<>(Status.SUCCESS, data);
    }
    public static Result<String> ok() {
        return new Result<>(Status.OK, "OK");
    }
    public static <T> Result<T> ok(T data) {
        return new Result<>(Status.OK, data);
    }
    public static Result<String> fail() {
        return new Result<>(Status.FAIL, "Fail");
    }
    public static <T> Result<T> fail(T data) {
        return new Result<>(Status.FAIL, data);
    }
    public enum Status {
        OK,
        SUCCESS,
        FAIL
    }
}


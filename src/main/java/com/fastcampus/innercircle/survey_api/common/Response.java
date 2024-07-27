package com.fastcampus.innercircle.survey_api.common;

public class Response<T> {

    private final T data;

    public T getData() {
        return data;
    }

    public Response(T data) {
        this.data = data;
    }

    public static <T> Response<T> of(T data) {
        return new Response<>(data);
    }
}

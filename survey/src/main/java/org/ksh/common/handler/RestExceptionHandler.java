package org.ksh.common.handler;

import lombok.extern.slf4j.Slf4j;
import org.ksh.common.exception.DataNotFoundException;
import org.ksh.common.model.BaseResponse;
import org.ksh.common.model.BaseStatusCode;
import org.springframework.http.HttpStatus;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.security.InvalidParameterException;
import java.util.List;
import java.util.stream.Collectors;
@Slf4j
@RestControllerAdvice
public class RestExceptionHandler {
    /**
     * 400 Bad Request (파라미터 에러 처리)
     *
     * @param exception
     * @return
     */
    @ExceptionHandler({InvalidParameterException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public BaseResponse<?> handleInvalidParameterException(Exception exception) {
        log.warn("InvalidParameterException", exception);
        return getBaseResponseByHttpStatus(HttpStatus.BAD_REQUEST, "유효하지 않는 파라미터입니다.");
    }

    /**
     * @param exception
     * @return
     * @RequestParam(required = true)일때, 빈값이 매핑된 경우
     */
    @ExceptionHandler({MissingServletRequestParameterException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public BaseResponse<?> handleMissingServletRequestParameterException(MissingServletRequestParameterException exception) {
        log.warn("MissingServletRequestParameterException", exception);
        return getBaseResponseByBaseStatusCode(BaseStatusCode.BAD_REQUEST, "요청 파라미터 값이 비어있습니다.");
    }

    /**
     * @param exception
     * @return
     * @Valid관련 유효성 검사에서 예외 발생시 핸들링
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public BaseResponse<?> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        log.warn("MethodArgumentNotValidException", exception);
        List<RequestException> exceptionList = exception.getBindingResult()
                .getFieldErrors().stream()
                .map(ex -> RequestException.builder()
                        .name(ex.getObjectName())
                        .code(ex.getCode())
                        .message(String.format("%s [%s:%s]",
                                ex.getDefaultMessage(),
                                ex.getField(),
                                ex.getRejectedValue() == null ? null : ex.getRejectedValue().toString()))
                        .build())
                .collect(Collectors.toList());
        return new BaseResponse<>(BaseStatusCode.BAD_REQUEST, exceptionList);
    }

    /**
     * 타입 일치하지 않는 경우 발생하는 예외 핸들링
     *
     * @param exception
     * @return
     */
    @ExceptionHandler({MethodArgumentTypeMismatchException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public BaseResponse<?> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException exception) {
        log.warn("MethodArgumentTypeMismatchException", exception);
        return getBaseResponseByBaseStatusCode(BaseStatusCode.BAD_REQUEST, "타입이 일치하지 않습니다.");
    }

    /**
     * 타입 일치하지 않는 경우 발생하는 커스텀 예외 핸들링
     *
     * @param exception
     * @return
     */
    @ExceptionHandler({IllegalArgumentException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public BaseResponse<?> handleIllegalArgumentException(MethodArgumentTypeMismatchException exception) {
        log.warn("MethodArgumentTypeMismatchException", exception);
        return getBaseResponseByBaseStatusCode(BaseStatusCode.BAD_REQUEST, exception.getMessage());
    }

    /**
     * 404 Not Found
     *
     * @param exception
     * @return
     */
    @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public BaseResponse<?> handleNoHandlerFounderException(Exception exception) {
        log.warn("NoHandlerFoundException", exception);
        return getBaseResponseByHttpStatus(HttpStatus.NOT_FOUND, "페이지를 찾을 수 없습니다.");
    }

    /**
     * 405 Method not allowed
     *
     * @param exception
     * @return
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    public BaseResponse<?> handleHttpRequestMethodNotSupportedException(Exception exception) {
        log.warn("HttpRequestMethodNotSupportedException", exception);
        return getBaseResponseByHttpStatus(HttpStatus.METHOD_NOT_ALLOWED, "허용되지 않는 메소드입니다.");
    }

    /**
     * 그밖의 예외 처리
     *
     * @param exception
     * @return
     */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public BaseResponse<?> handleException(Exception exception) {
        log.warn("Exception", exception);
        return getBaseResponseByHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR, "시스템에 문제가 있습니다.");
    }

    /**
     * 조회 데이터 없음
     *
     * @param exception
     * @return
     */
    @ExceptionHandler(DataNotFoundException.class)
    @ResponseStatus(HttpStatus.OK)
    public BaseResponse<?> handleDataNotFoundException(Exception exception) {
        log.warn("DataNotFoundException", exception);
        return getBaseResponseByBaseStatusCode(BaseStatusCode.DATA_NOT_FOUND, exception.getMessage());
    }

    /**
     * HTTP Status Base Response
     *
     * @param httpStatus
     * @param message
     * @return
     */
    private BaseResponse<?> getBaseResponseByHttpStatus(HttpStatus httpStatus, String message) {
        BaseStatusCode statusCode = BaseStatusCode.getBaseStatusCodeByStatus(httpStatus.value());
        return new BaseResponse<>(statusCode, message);
    }

    private BaseResponse<?> getBaseResponseByBaseStatusCode(BaseStatusCode baseStatusCode, String message) {
        BaseStatusCode statusCode = BaseStatusCode.getBaseStatusCodeByStatus(baseStatusCode.getStatus());
        log.warn("Exception message ", message);
        return new BaseResponse<>(statusCode, message);
    }

}

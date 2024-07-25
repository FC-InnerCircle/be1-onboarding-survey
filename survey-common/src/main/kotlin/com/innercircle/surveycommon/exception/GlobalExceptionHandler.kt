package com.innercircle.surveycommon.exception

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.ErrorResponse
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class GlobalExceptionHandler {
    @ExceptionHandler(CustomException::class)
    fun handleCustomException(ex: CustomException): ResponseEntity<com.innercircle.surveycommon.exception.ErrorResponse> {
        val status =
            when (ex) {
                is ResourceNotFoundException -> HttpStatus.NOT_FOUND
                is InvalidInputException -> HttpStatus.BAD_REQUEST
                else -> HttpStatus.INTERNAL_SERVER_ERROR
            }

        val errorResponse = ErrorResponse(ex.message ?: "오류가 발생했습니다", ex.errorCode)
        return ResponseEntity(errorResponse, status)
    }

    @ExceptionHandler(Exception::class)
    fun handleGenericException(ex: Exception): ResponseEntity<com.innercircle.surveycommon.exception.ErrorResponse> {
        val errorResponse = ErrorResponse("예기치 않은 오류가 발생했습니다", "INTERNAL_SERVER_ERROR")
        return ResponseEntity(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR)
    }
}

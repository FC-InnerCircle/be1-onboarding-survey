package org.example.survey.config

import org.example.survey.config.exception.CommonExceptionResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class GlobalExceptionHandler {
    @ExceptionHandler(RuntimeException::class)
    fun runtimeExceptionHandler(e: RuntimeException): ResponseEntity<CommonExceptionResponse> {
        e.printStackTrace()
        return ResponseEntity(
            CommonExceptionResponse(
                code = HttpStatus.INTERNAL_SERVER_ERROR.name,
                message = e.message ?: "error",
            ),
            HttpStatus.INTERNAL_SERVER_ERROR,
        )
    }

    @ExceptionHandler(IllegalArgumentException::class)
    fun illegalArgumentExceptionHandler(e: IllegalArgumentException): ResponseEntity<CommonExceptionResponse> {
        e.printStackTrace()
        return ResponseEntity(
            CommonExceptionResponse(
                code = HttpStatus.BAD_REQUEST.name,
                message = e.message ?: "error",
            ),
            HttpStatus.BAD_REQUEST,
        )
    }
}

package org.inner.circle.survey.common.exception

import com.fasterxml.jackson.databind.exc.InvalidFormatException
import io.github.oshai.kotlinlogging.KotlinLogging
import jakarta.validation.ConstraintViolationException
import org.apache.coyote.BadRequestException
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.FieldError
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.context.request.WebRequest
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException

@RestControllerAdvice
class GlobalExceptionHandler {
    private val log = KotlinLogging.logger {}

    @ExceptionHandler(InvalidFormatException::class)
    fun handleInvalidFormatException(
        ex: InvalidFormatException, request: WebRequest
    ): ResponseEntity<String> {
        val enumClass = (ex.targetType as? Class<Enum<*>>)?.enumConstants?.joinToString(", ") ?: "unknown"
        val errorMessage = "Invalid value for enum. Allowed values are: $enumClass"
        log.error { errorMessage }
        return ResponseEntity(errorMessage, HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException::class)
    fun handleMethodArgumentTypeMismatchException(
        ex: MethodArgumentTypeMismatchException, request: WebRequest
    ): ResponseEntity<String> {
        if (ex.requiredType?.isEnum == true) {
            val enumClass = (ex.requiredType as? Class<Enum<*>>)?.enumConstants?.joinToString(", ") ?: "unknown"
            val errorMessage = "Invalid value for enum. Allowed values are: $enumClass"
            log.error { errorMessage }
            return ResponseEntity(errorMessage, HttpStatus.BAD_REQUEST)
        }
        log.error { "Invalid value" }
        return ResponseEntity("Invalid value", HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(Exception::class)
    fun handleException(
        ex: Exception, request: WebRequest
    ): ResponseEntity<String> {
        log.error(ex) { "Internal server error" }
        return ResponseEntity("Internal server error", HttpStatus.INTERNAL_SERVER_ERROR)
    }

    @ExceptionHandler(IllegalArgumentException::class)
    fun handleIllegalArgumentException(
        ex: IllegalArgumentException, request: WebRequest
    ): ResponseEntity<String> {
        val errorMessage = ex.message ?: "Bad request"
        log.error(ex) { errorMessage }
        return ResponseEntity(errorMessage, HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleMethodArgumentNotValidException(
        ex: MethodArgumentNotValidException, request: WebRequest
    ): ResponseEntity<Map<String, String?>> {
        val errors: MutableMap<String, String?> = HashMap()
        ex.bindingResult.allErrors.forEach { error ->
            val fieldName = (error as? FieldError)?.field ?: "unknown"
            val errorMessage = error.defaultMessage
            errors[fieldName] = errorMessage
        }
        log.error { errors }
        return ResponseEntity(errors, HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(ConstraintViolationException::class)
    fun handleConstraintViolationException(
        ex: ConstraintViolationException, request: WebRequest
    ): ResponseEntity<Map<String, String?>> {
        val errors: MutableMap<String, String?> = HashMap()
        ex.constraintViolations.forEach { violation ->
            val fieldName = violation.propertyPath.toString()
            val errorMessage = violation.message
            errors[fieldName] = errorMessage
        }
        log.error { errors }
        return ResponseEntity(errors, HttpStatus.BAD_REQUEST)
    }

}
package com.innercircle.surveycommon.exception

sealed class CustomException(
    message: String,
    val errorCode: String,
) : RuntimeException(message)

class ResourceNotFoundException(
    message: String = "리소스를 찾을 수 없습니다",
    errorCode: String = "RESOURCE_NOT_FOUND",
) : CustomException(message, errorCode)

class InvalidInputException(
    message: String = "잘못된 입력입니다",
    errorCode: String = "INVALID_INPUT",
) : CustomException(message, errorCode)

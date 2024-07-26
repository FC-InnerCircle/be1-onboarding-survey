package com.innercircle.surveyapi.common

import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import org.springframework.web.context.request.RequestContextHolder
import org.springframework.web.context.request.ServletRequestAttributes

@Aspect
@Component
class RequestLoggingAspect {
    private val logger = LoggerFactory.getLogger(this::class.java)

    @Around("@within(org.springframework.web.bind.annotation.RestController)")
    fun logRequest(joinPoint: ProceedingJoinPoint): Any {
        val request = (RequestContextHolder.currentRequestAttributes() as ServletRequestAttributes).request
        val method = request.method
        val uri = request.requestURI

        logger.info("Request received: $method $uri")

        val start = System.currentTimeMillis()
        val result = joinPoint.proceed()
        val executionTime = System.currentTimeMillis() - start

        logger.info("Request completed: $method $uri - Execution Time: ${executionTime}ms")

        return result
    }
}

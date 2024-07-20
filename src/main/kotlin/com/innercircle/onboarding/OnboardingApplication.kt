package com.innercircle.onboarding

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class OnboardingApplication

fun main(args: Array<String>) {
    runApplication<OnboardingApplication>(*args)
}

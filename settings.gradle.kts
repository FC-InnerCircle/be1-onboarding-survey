pluginManagement {
    includeBuild("build-logic")
}

plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.8.0"
}

rootProject.name = "onboarding"
include("survey-api", "survey-domain", "survey-common", "survey-application")

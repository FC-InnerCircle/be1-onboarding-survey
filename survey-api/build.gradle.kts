plugins {
    id("org.springframework.boot") version "3.3.2"
    id("io.spring.dependency-management") version "1.1.6"
    id("org.springdoc.openapi-gradle-plugin") version "1.9.0"
    kotlin("jvm") version "1.9.24"
    kotlin("plugin.spring") version "1.9.24"
}

dependencies {
    implementation(project(":survey-domain"))
    implementation(project(":survey-common"))
    implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.6.0")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.springframework.boot:spring-boot-starter-aop")
    implementation("jakarta.servlet:jakarta.servlet-api:5.0.0")
}

// main가 없으므로 jar task를 비활성화
tasks.getByName<Jar>("bootJar") {
    enabled = false
}

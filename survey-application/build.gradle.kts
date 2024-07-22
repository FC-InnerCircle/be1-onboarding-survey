import java.time.LocalDate

// plugin 설정
plugins {
    id("org.springframework.boot") version "3.3.2"
    id("io.spring.dependency-management") version "1.1.6"
    kotlin("jvm") version "1.9.24"
    kotlin("plugin.spring") version "1.9.24"
}

dependencies {
    implementation(project(":survey-api"))
    implementation(project(":survey-domain"))
    implementation(project(":survey-common"))

    implementation("org.springframework.boot:spring-boot-starter")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-validation")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
}

// 멀티모듈 1개의 jar로 패키징
tasks.register<Jar>("fatJar") {

    val currentDate = LocalDate.now().toString()

    archiveClassifier.set("all")
    archiveFileName.set("survey-application-all-$currentDate.jar")
    destinationDirectory.set(file("${rootProject.projectDir}/build-output/result"))
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
    from(sourceSets.main.get().output)

    dependsOn(configurations.runtimeClasspath)
    from({
        configurations.runtimeClasspath
            .get()
            .filter { it.name.endsWith("jar") }
            .map { zipTree(it) }
    })

    manifest {
        attributes["Main-Class"] = "com.innercircle.surveyapplication.SurveyApplication"
    }

    doLast {
        println("Fat JAR 파일이 생성될 경로: ${destinationDirectory.get().asFile.absolutePath}")
        println("Fat JAR 파일 이름: ${archiveFileName.get()}")
    }
}

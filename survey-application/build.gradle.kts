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

    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib")

    implementation("org.springframework.boot:spring-boot-starter-aop")
    implementation("com.h2database:h2")
}

val currentDate = LocalDate.now().toString()

tasks.register<Jar>("fatJar") {

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
        from("src/main/resources/META-INF/MANIFEST.MF")
    }

    doFirst {
        println("Setting archive properties...")
    }

    doFirst {
        println("Starting fatJar task...")
    }

    doLast {
        println("Fat JAR 파일이 생성될 경로: ${destinationDirectory.get().asFile.absolutePath}")
        println("Fat JAR 파일 이름: ${archiveFileName.get()}")

        val jarFile = file("${destinationDirectory.get().asFile.absolutePath}/${archiveFileName.get()}")
        if (jarFile.exists()) {
            println("JAR 파일이 생성되었습니다: ${jarFile.absolutePath}")
            val manifestFile = zipTree(jarFile).matching { include("META-INF/MANIFEST.MF") }.singleFile
            if (manifestFile.exists()) {
                val manifestContent = manifestFile.readText()
                if (manifestContent.isNotBlank() &&
                    manifestContent.contains("Main-Class: com.innercircle.surveyapplication.SurveyApplication")
                ) {
                    println("Main-Class 속성이 올바르게 설정되었습니다.")
                } else {
                    println("Main-Class 속성이 설정되지 않았습니다.")
                    throw GradleException("Main-Class 속성이 설정되지 않았습니다.")
                }
            } else {
                println("매니페스트 파일을 찾을 수 없습니다.")
                throw GradleException("매니페스트 파일을 찾을 수 없습니다.")
            }
        } else {
            println("JAR 파일이 생성되지 않았습니다.")
        }

        val finalManifestFile = zipTree(jarFile).matching { include("META-INF/MANIFEST.MF") }.singleFile
        if (finalManifestFile.exists()) {
            val finalManifestContent = finalManifestFile.readText()
            if (finalManifestContent.isNotBlank()) {
                println("최종 매니페스트 파일이 존재합니다.")
            } else {
                println("최종 매니페스트 파일 내용이 비어 있습니다.")
                throw GradleException("최종 매니페스트 파일 내용이 비어 있습니다.")
            }
        } else {
            println("최종 매니페스트 파일을 찾을 수 없습니다.")
            throw GradleException("최종 매니페스트 파일을 찾을 수 없습니다.")
        }

        val finalDestination = file("${rootProject.projectDir}/final-destination")
        if (!finalDestination.exists()) {
            finalDestination.mkdirs()
        }
        jarFile.copyTo(file("${finalDestination.absolutePath}/${archiveFileName.get()}"), overwrite = true)
        println("JAR 파일이 최종 목적지로 이동되었습니다: ${finalDestination.absolutePath}/${archiveFileName.get()}")

        val finalJarFile = file("${finalDestination.absolutePath}/${archiveFileName.get()}")
        if (finalJarFile.exists()) {
            val finalManifestFileInDestination = zipTree(finalJarFile).matching { include("META-INF/MANIFEST.MF") }.singleFile
            if (finalManifestFileInDestination.exists()) {
                val finalManifestContentInDestination = finalManifestFileInDestination.readText()
                if (finalManifestContentInDestination.isNotBlank()) {
                    println("최종 목적지에 있는 JAR 파일의 매니페스트 파일이 존재합니다.")
                } else {
                    println("최종 목적지에 있는 매니페스트 파일 내용이 비어 있습니다.")
                    throw GradleException("최종 목적지에 있는 매니페스트 파일 내용이 비어 있습니다.")
                }
            } else {
                println("최종 목적지에 있는 매니페스트 파일을 찾을 수 없습니다.")
                throw GradleException("최종 목적지에 있는 매니페스트 파일을 찾을 수 없습니다.")
            }
        } else {
            println("최종 목적지에 JAR 파일이 존재하지 않습니다.")
            throw GradleException("최종 목적지에 JAR 파일이 존재하지 않습니다.")
        }
    }

    doFirst {
        println("Checking runtime classpath dependencies...")
        configurations.runtimeClasspath.get().forEach {
            println("Dependency: ${it.name}")
        }
    }

    doFirst {
        println("Setting up manifest attributes...")
    }

    doFirst {
        println("Adding source sets output...")
    }

    doFirst {
        println("Adding runtime classpath JARs...")
    }

    // spring.factories 파일 추가
    from("src/main/resources/META-INF/spring.factories") {
        into("META-INF")
    }
}

// 빌드 작업이 오류 없이 실행되는지 확인
tasks.register("checkBuild") {
    dependsOn("build")
    doLast {
        println("Build completed successfully.")
    }
}

tasks.bootJar {
    archiveFileName.set("survey-application.jar")
    mainClass.set("com.innercircle.surveyapplication.SurveyApplicationKt")
}

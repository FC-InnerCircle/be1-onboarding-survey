// plugin 설정
plugins {
    id("org.springframework.boot") version "3.3.2"
    id("io.spring.dependency-management") version "1.1.6"
    kotlin("jvm") version "1.9.24"
    kotlin("plugin.spring") version "1.9.24"
}

// 공통 설정
subprojects {
    apply(plugin = "org.springframework.boot")
    apply(plugin = "io.spring.dependency-management")
    apply(plugin = "org.jetbrains.kotlin.jvm")

    dependencies {
        "implementation"("com.fasterxml.jackson.module:jackson-module-kotlin")
        "implementation"("org.jetbrains.kotlin:kotlin-reflect")
        "testImplementation"("org.jetbrains.kotlin:kotlin-test-junit5")
        "testRuntimeOnly"("org.junit.platform:junit-platform-launcher")
    }
}

// 공통 의존성 설정
allprojects {
    group = "com.inner-circle"
    version = "0.0.1-SNAPSHOT"

    repositories {
        mavenCentral()
    }

    tasks {
        java {
            toolchain {
                // Java 버전을 최신 안정 버전으로 수정
                languageVersion = JavaLanguageVersion.of(21)
            }
        }

        kotlin {
            compilerOptions {
                // 필요에 따라 Kotlin 컴파일러 옵션 조정
                freeCompilerArgs = listOf("-Xjsr305=strict")
            }
        }

        test {
            useJUnitPlatform()
        }
    }
}
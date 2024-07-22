dependencies {
    implementation(project(":survey-common"))
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("com.h2database:h2") // For in-memory database
}

// main가 없으므로 jar task를 비활성화
tasks.getByName<Jar>("bootJar") {
    enabled = false
}

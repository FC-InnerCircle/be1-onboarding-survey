dependencies {
    implementation(project(":survey-common"))
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("com.h2database:h2") // For in-memory database
}

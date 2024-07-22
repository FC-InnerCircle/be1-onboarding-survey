// main가 없으므로 jar task를 비활성화
tasks.getByName<Jar>("bootJar") {
    enabled = false
}

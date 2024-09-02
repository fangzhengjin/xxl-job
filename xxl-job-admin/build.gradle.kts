tasks {
    bootJar {
        enabled = true
    }
    jar {
        enabled = false
    }
}
dependencies {
    implementation(project(":xxl-job-core"))
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-freemarker")
    implementation("org.springframework.boot:spring-boot-starter-mail")
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    implementation(libs.mybatisSpringBootStarter)
    testImplementation("org.springframework.boot:spring-boot-starter-test")
}

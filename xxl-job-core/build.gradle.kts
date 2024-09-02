plugins {
    `java-library`
}

dependencies {
    api(libs.nettyCodecHttp)
    api(libs.gson)
    api(libs.groovy)
    api(libs.slf4j)
    implementation(libs.jakartaAnnotationApi)
    implementation("org.springframework:spring-context")
}

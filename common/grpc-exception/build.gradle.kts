plugins {
    id("java")
}

group = "ru.ners"
version = "0.0.1-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("io.grpc:grpc-core:1.68.1")
}

tasks.test {
    useJUnitPlatform()
}
plugins {
    id("java")
}

group = "ru.ners"
version = "0.0.1-SNAPSHOT"

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("io.grpc:grpc-core:1.68.1")
    implementation("net.devh:grpc-server-spring-boot-starter:3.1.0.RELEASE")
}

tasks.test {
    useJUnitPlatform()
}
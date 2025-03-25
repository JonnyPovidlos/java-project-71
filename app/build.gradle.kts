plugins {
    id("java")
    id("com.github.ben-manes.versions") version "0.52.0"
    id("org.sonarqube") version "6.0.1.5171"
    application
    checkstyle
    jacoco
}

group = "hexlet.code"
version = "1.0-SNAPSHOT"

java {
    sourceCompatibility = JavaVersion.VERSION_17
}

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
    implementation("info.picocli:picocli:4.7.6")
    implementation("com.fasterxml.jackson.core:jackson-databind:2.18.3")
}

tasks.test {
    useJUnitPlatform()
    finalizedBy(tasks.jacocoTestReport) // report is always generated after tests run
}

tasks.jacocoTestReport {
    dependsOn(tasks.test) // tests are required to run before generating the report
    reports {
        xml.required = true
    }
}

application {
    mainClass = "hexlet.code.App"
}

sonar {
    properties {
        property("sonar.projectKey", "JonnyPovidlos_java-project-71")
        property("sonar.organization", "jonnypovidlos")
        property("sonar.host.url", "https://sonarcloud.io")
    }
}
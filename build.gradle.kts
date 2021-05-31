import java.net.URI

group = "org.github.silverbulleters"
version = gitVersionCalculator.calculateVersion("v")
var javaVersion = JavaVersion.VERSION_11
var encoding = "UTF-8"
val junitVersion = "5.6.1"

plugins {
    java
    id("io.franzbecker.gradle-lombok") version "4.0.0"
    id("com.github.gradle-git-version-calculator") version "1.1.0"
    id("net.kyori.indra.license-header") version "1.2.1"
}

repositories {
    mavenCentral()
    maven { url = URI("https://jitpack.io") }
}

java {
    sourceCompatibility = javaVersion
    targetCompatibility = javaVersion
}

dependencies {
    compileOnly("org.projectlombok", "lombok", lombok.version)
    // https://mvnrepository.com/artifact/com.fasterxml.jackson.core/jackson-core
    implementation("com.fasterxml.jackson.core", "jackson-core", "2.12.1")
    implementation("com.fasterxml.jackson.core", "jackson-databind", "2.12.1")
    // https://mvnrepository.com/artifact/org.apache.logging.log4j/log4j-api
    implementation("org.apache.logging.log4j", "log4j-api","2.14.0")
    implementation("org.apache.logging.log4j", "log4j-core","2.14.0")
    implementation("org.apache.logging.log4j", "log4j-slf4j-impl","2.14.0")
    implementation("org.jetbrains", "annotations", "16.0.2")

    // junit
    testImplementation("org.junit.jupiter", "junit-jupiter-api", junitVersion)
    testImplementation("org.junit.jupiter", "junit-jupiter-params", junitVersion)
    testRuntimeOnly("org.junit.jupiter", "junit-jupiter-engine", junitVersion)

    testImplementation("org.assertj", "assertj-core", "3.18.1")
}

tasks.withType<JavaCompile> {
    options.encoding = encoding
}

tasks.test {
    useJUnitPlatform()

    testLogging {
        events("passed", "skipped", "failed", "standard_error")
    }

    reports {
        html.isEnabled = true
    }
}

lombok {
    version = "1.18.16"
}

license {
    header = rootProject.file("HEADER.txt")
    ext["year"] = "2020"
    ext["name"] = "Team SilverBulleters <team@silverbulleters.org> and contributors"
    ext["project"] = "BSL platform context"
    exclude("**/*.properties")
    exclude("**/*.xml")
    exclude("**/*.json")
    exclude("**/*.txt")
    exclude("**/*.java.orig")
    exclude("**/*.impl")
}

apply(from = "gradle/generate.gradle.kts")

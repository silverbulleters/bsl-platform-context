import java.net.URI

group = "org.github.silverbulleters"
version = gitVersionCalculator.calculateVersion("v")
var javaVersion = JavaVersion.VERSION_11
var encoding = "UTF-8"
val junitVersion = "5.6.1"
var log4jVersion = "2.14.0"

plugins {
    java
    `maven-publish`
    jacoco
    id("io.franzbecker.gradle-lombok") version "4.0.0"
    id("com.github.gradle-git-version-calculator") version "1.1.0"
    id("net.kyori.indra.license-header") version "1.2.1"
    id("org.sonarqube") version "3.3"
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
    implementation("org.apache.logging.log4j", "log4j-api", log4jVersion)
    implementation("org.apache.logging.log4j", "log4j-core", log4jVersion)
    implementation("org.apache.logging.log4j", "log4j-slf4j-impl", log4jVersion)
    implementation("org.jetbrains", "annotations", "16.0.2")

    // junit
    testImplementation("org.junit.jupiter", "junit-jupiter-api", junitVersion)
    testImplementation("org.junit.jupiter", "junit-jupiter-params", junitVersion)
    testRuntimeOnly("org.junit.jupiter", "junit-jupiter-engine", junitVersion)

    testImplementation("org.assertj", "assertj-core", "3.18.1")
}

tasks.withType<JavaCompile> {
    options.encoding = encoding
    options.compilerArgs.add("-Xlint:unchecked")
    options.compilerArgs.add("-parameters")
}

tasks.test {
    useJUnitPlatform()

    testLogging {
        events("passed", "skipped", "failed")
    }

    reports {
        html.isEnabled = true
    }
}

tasks.check {
    dependsOn(tasks.jacocoTestReport)
}

tasks.jacocoTestReport {
    reports {
        xml.isEnabled = true
        xml.destination = File("$buildDir/reports/jacoco/test/jacoco.xml")
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

publishing {
    publications {
        create<MavenPublication>("maven") {
            from(components["java"])
            pom {
                description.set("Platform context 1C:Enterprise 8")
                url.set("https://github.com/silverbulleters/bsl-platform-context")
                licenses {
                    license {
                        name.set("GNU LGPL 3")
                        url.set("https://www.gnu.org/licenses/lgpl-3.0.txt")
                        distribution.set("repo")
                    }
                }
                developers {
                    developer {
                        id.set("team")
                        name.set("Team Silverbulleters")
                        email.set("team@silverbulleters.org")
                        url.set("https://github.com/silverbulleters")
                        organization.set("silverbulleters")
                        organizationUrl.set("https://github.com/silverbulleters")
                    }
                }
                scm {
                    connection.set("scm:git:git://github.com/silverbulleters/bsl-platform-context.git")
                    developerConnection.set("scm:git:git@github.com:silverbulleters/bsl-platform-context.git")
                    url.set("https://github.com/silverbulleters/bsl-platform-context")
                }
            }
        }
    }
}

sonarqube {
    properties {
        property("sonar.sourceEncoding", "UTF-8")
        property("sonar.host.url", "https://sonarcloud.io")
        property("sonar.organization", "silverbulleters")
        property("sonar.projectKey", "silverbulleters_bsl-platform-context")
        property("sonar.projectName", "BSL: Platform context")
        property("sonar.exclusions", "**/gen/**/*.*")
        property("sonar.coverage.jacoco.xmlReportPaths", "$buildDir/reports/jacoco/test/jacoco.xml")
    }
}
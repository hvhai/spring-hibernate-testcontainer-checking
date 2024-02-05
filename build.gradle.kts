plugins {
    java
    id("org.springframework.boot") version "3.2.1"
    id("io.spring.dependency-management") version "1.1.4"
    id("net.rdrei.android.buildtimetracker") version "0.11.0"
    id("io.freefair.lombok") version "8.4"
}

group = "com.codehunter"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_17

sourceSets {
    val main by getting
    val test by getting

    val integrationTest by creating {
        compileClasspath += main.output + test.output
        runtimeClasspath += main.output + test.output
    }
}

configurations {
    val testImplementation by getting
    val testRuntimeOnly by getting
    "integrationTestImplementation" { extendsFrom(testImplementation) }
    "integrationTestRuntimeOnly" { extendsFrom(testRuntimeOnly) }
    all {
        exclude(group = "org.springframework.boot", module = "spring-boot-starter-logging")
    }
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
}

repositories {
    mavenCentral()
}

//extra["testcontainersVersion"] = "1.19.1"

buildtimetracker {
    reporters {
        register("csv") {
            options.run {
                put("output", "$buildDir/times.csv")
                put("append", "true")
                put("header", "false")
            }
        }

        register("summary") {
            options.run {
                put("ordered", "false")
                put("threshold", "50")
                put("header", "false")
            }
        }

        register("csvSummary") {
            options.run {
                put("csv", "$buildDir/times.csv")
            }
        }
    }
}

dependencies {
    // Spring
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-web")
    annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")

    // Database
    implementation("io.hypersistence:hypersistence-utils-hibernate-60:3.6.0")
    // H2
    runtimeOnly("com.h2database:h2")
    // MySql
    implementation("com.mysql:mysql-connector-j")
    testImplementation("org.testcontainers:mysql")

    // Mapstruct
    implementation("org.mapstruct:mapstruct:1.5.5.Final")
    annotationProcessor("org.mapstruct:mapstruct-processor:1.5.5.Final")

    // Lombok
    compileOnly("org.projectlombok:lombok")
    annotationProcessor("org.projectlombok:lombok")
    "integrationTestAnnotationProcessor"("org.projectlombok:lombok")
    testImplementation("org.projectlombok:lombok")

    // Logging
    implementation("org.apache.logging.log4j:log4j-api:2.21.0")
    implementation("org.apache.logging.log4j:log4j-core:2.21.0")

    // Test
    implementation("org.assertj:assertj-core:3.25.3")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.springframework.boot:spring-boot-testcontainers")
    testImplementation("org.testcontainers:junit-jupiter")

    // monitoring
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    runtimeOnly("io.micrometer:micrometer-registry-prometheus")
}

//dependencyManagement {
//    imports {
//        mavenBom("org.testcontainers:testcontainers-bom:${property("testcontainersVersion")}")
//    }
//}

tasks {
    test {
        useJUnitPlatform()
    }

    val integrationTest by creating(Test::class) {
        description = "Runs integration tests."
        group = "verification"

        testClassesDirs = sourceSets["integrationTest"].output.classesDirs
        classpath = sourceSets["integrationTest"].runtimeClasspath
        testLogging.showStandardStreams = true
        useJUnitPlatform()
        shouldRunAfter("test")
    }

    check {
        dependsOn(integrationTest)
//		finalizedBy(jacocoTestReport)
    }
}
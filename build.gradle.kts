plugins {
    java
    id("org.springframework.boot") version "3.1.2"
    id("io.spring.dependency-management") version "1.1.2"
    id("net.rdrei.android.buildtimetracker") version "0.11.0"
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

extra["testcontainersVersion"] = "1.18.3"

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
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("io.hypersistence:hypersistence-utils-hibernate-60:3.5.1")
    implementation("org.mapstruct:mapstruct:1.5.5.Final")
    implementation("org.assertj:assertj-core:3.24.2")
    compileOnly("org.projectlombok:lombok")
    runtimeOnly("com.h2database:h2")
    implementation("com.mysql:mysql-connector-j")
    annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")
    annotationProcessor("org.projectlombok:lombok")
    annotationProcessor("org.mapstruct:mapstruct-processor:1.5.5.Final")
    "integrationTestAnnotationProcessor"("org.projectlombok:lombok")
    testImplementation("org.projectlombok:lombok")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.testcontainers:junit-jupiter")
    testImplementation("org.testcontainers:mysql")
}

dependencyManagement {
    imports {
        mavenBom("org.testcontainers:testcontainers-bom:${property("testcontainersVersion")}")
    }
}

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
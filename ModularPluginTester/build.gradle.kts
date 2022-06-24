buildscript {
    repositories {
        mavenCentral()
        mavenLocal()
    }

    dependencies {
        classpath("com.ywq.mp:ModularPlugin:1.0-SNAPSHOT")
    }
}

allprojects {
    tasks.create("downloadDependencies") {
        description = "Download all dependencies to the Gradle cache"
        doLast {
            for (configuration in configurations) {
                if (configuration.isCanBeResolved) {
                    configuration.files
                }
            }
        }
    }
}

plugins {
    kotlin("jvm") version "1.6.10"
    java
}

apply(plugin = "mp-project")

group = "com.ywq"
version = "1.0-SNAPSHOT"

repositories {
    google()
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib"))
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.6.0")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine")

    implementation("io.reactivex.rxjava3:rxjava:3.+")
    implementation("com.squareup.okhttp3:okhttp:4.9.3")
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}
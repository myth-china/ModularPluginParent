plugins {
    kotlin("jvm") version "1.6.10"
    java
    `java-gradle-plugin`
    `maven-publish`
}

group = "com.ywq.mp"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib"))
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.8.2")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine")

    implementation("org.yaml:snakeyaml:1.29")
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}

gradlePlugin {
    plugins {
        create("mp-project") {
            id = "mp-project"
            implementationClass = "com.ywq.mp.ProjectPlugin"
        }

        create("mp-settings") {
            id = "mp-settings"
            implementationClass = "com.ywq.mp.SettingsPlugin"
        }
    }
}
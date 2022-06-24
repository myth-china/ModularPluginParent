rootProject.name = "ModularPluginTester"

buildscript {
    repositories {
        mavenCentral()
        mavenLocal()
    }

    dependencies {
        classpath("com.ywq.mp:ModularPlugin:1.0-SNAPSHOT")
    }
}

apply(plugin = "mp-settings")

//includeBuild("../ModularPlugin")

//includeBuild("../okhttp")
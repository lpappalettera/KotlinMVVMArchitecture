// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    val kotlinVersion: String by extra("1.6.10")
    val composeVersion: String by extra("1.1.1")
    val hiltVersion: String by extra("2.40.5")

    repositories {
        google()
        mavenCentral()
    }

    dependencies {
        classpath("com.android.tools.build:gradle:7.1.3")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVersion")
        classpath("org.jetbrains.kotlin:kotlin-serialization:$kotlinVersion")
        classpath("com.google.dagger:hilt-android-gradle-plugin:$hiltVersion")
    }
}

allprojects {
    repositories {
        maven {
            name = "Apploket"
            url = uri("https://lab.dtnr.nl/api/v4/groups/368/-/packages/maven")
            if (project.hasProperty("gitlab.token")) {
                credentials(HttpHeaderCredentials::class) {
                    name = "Private-Token"
                    value = "${project.property("gitlab.token")}"
                }
            } else if (project.hasProperty("job.token")) {
                credentials(HttpHeaderCredentials::class) {
                    name = "Job-Token"
                    value = "${project.property("job.token")}"
                }
            } else {
                throw GradleException("You need to have a private token to gitlab defined in ~/.gradle/gradle.properties in order to use this repository")
            }
            authentication {
                create<HttpHeaderAuthentication>("header")
            }
        }
        google()
        mavenCentral()
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
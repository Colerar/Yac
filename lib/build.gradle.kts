import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
  kotlin("jvm")
  `java-library`
}

repositories {
  mavenCentral()
}

dependencies {
  testImplementation(Kotlin.Test.common)
  testImplementation(Kotlin.Test.junit5)
}

tasks.test {
  useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
  kotlinOptions.apply {
    jvmTarget = "17"
    freeCompilerArgs = freeCompilerArgs + "-opt-in=kotlin.RequiresOptIn"
  }
}

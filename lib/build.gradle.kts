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

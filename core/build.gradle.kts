import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import java.util.*

plugins {
  kotlin("jvm")
  `maven-publish`
  signing
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

// Publishing
ext["signing.keyId"] = null
ext["signing.password"] = null
ext["signing.secretKeyRingFile"] = null
ext["ossrhUsername"] = null
ext["ossrhPassword"] = null

val secretPropsFile: File = project.rootProject.file("local.properties")
if (secretPropsFile.exists()) {
  secretPropsFile.reader().use {
    Properties().apply {
      load(it)
    }
  }.onEach { (name, value) ->
    ext[name.toString()] = value
  }
} else {
  ext["signing.keyId"] = System.getenv("SIGNING_KEY_ID")
  ext["signing.password"] = System.getenv("SIGNING_PASSWORD")
  ext["signing.secretKeyRingFile"] = System.getenv("SIGNING_SECRET_KEY_RING_FILE")
  ext["ossrhUsername"] = System.getenv("OSSRH_USERNAME")
  ext["ossrhPassword"] = System.getenv("OSSRH_PASSWORD")
}


val javadocJar by tasks.registering(Jar::class) {
  archiveClassifier.set("javadoc")
}

fun getExtraString(name: String) = ext[name]?.toString()

publishing {
  repositories {
    maven {
      name = "sonatype"
      val releasesRepoUrl = "https://s01.oss.sonatype.org/service/local/staging/deploy/maven2/"
      val snapshotsRepoUrl = "https://s01.oss.sonatype.org/content/repositories/snapshots/"
      val url = if (version.toString().contains("SNAPSHOT")) snapshotsRepoUrl else releasesRepoUrl
      setUrl(url)
      credentials {
        username = getExtraString("ossrhUsername")
        password = getExtraString("ossrhPassword")
      }
    }
    publications {
      create<MavenPublication>("binaryAndSources") {
        from(components["java"])
      }
    }
  }

  publications.withType<MavenPublication> {

    artifact(javadocJar.get())

    pom {
      name.set("Yac")
      description.set("Yet Another Console based on Clikt, not only for CLI")
      url.set("https://github.com/Colerar/Yac")
      licenses {
        license {
          name.set("The Apache Software License, Version 2.0")
          url.set("http://www.apache.org/licenses/LICENSE-2.0.txt")
          distribution.set("repo")
        }
      }

      developers {
        developer {
          id.set("ajalt")
          name.set("AJ Alt")
          url.set("https://github.com/ajalt")
        }
        developer {
          id.set("Colerar")
          email.set("233hbj@gmail.com")
          url.set("https://github.com/Colerar")
        }
      }

      scm {
        url.set("https://github.com/Colerar/Yac")
        connection.set("scm:git:git://github.com/Colerar/Yac.git")
        developerConnection.set("scm:git:ssh://git@github.com/Colerar/Yac.git")
      }
    }
  }
}

signing {
  sign(publishing.publications)
}

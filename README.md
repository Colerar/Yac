# Yac

[![License: Apache 2.0](https://img.shields.io/badge/License-Apache_2.0-yellow)](https://www.apache.org/licenses/LICENSE-2.0) [![Maven Snapshots](https://img.shields.io/nexus/s/moe.sdl.yac/core?label=Maven%20Snapshots&server=https%3A%2F%2Fs01.oss.sonatype.org)](https://s01.oss.sonatype.org/content/repositories/snapshots/moe/sdl/yac/core/)

Yet another command system for not only CLI environment.

Most parts of this project are migrated from [Clikt](https://github.com/ajalt/clikt).

## Setup

Now only snapshots available:

```kotlin
repositories {
  maven("https://s01.oss.sonatype.org/content/repositories/snapshots/")
}

dependencies {
  implementation("moe.sdl.yac:core:$snapshotVersion")
}
```

[![GitHub Workflow Status](https://img.shields.io/github/actions/workflow/status/gotson/NightMonkeys/ci.yml?branch=main&style=flat-square)](https://github.com/gotson/NightMonkeys/actions/workflows/ci.yml)
[![Maven Central](https://img.shields.io/maven-central/v/com.github.gotson.nightmonkeys/imageio-jxl?color=blue&style=flat-square)](https://search.maven.org/search?q=g:com.github.gotson.nightmonkeys)
[![Sonatype Nexus (Snapshots)](https://img.shields.io/nexus/s/com.github.gotson.nightmonkeys/imageio-jxl?color=blue&label=maven-snapshot&server=https%3A%2F%2Foss.sonatype.org&style=flat-square)](https://oss.sonatype.org/content/repositories/snapshots/com/github/gotson/nightmonkeys/)

# NightMonkeys

A collection of ImageIO plugins, adding support for newer image formats. NightMonkeys uses the newer Foreign Linker API
available in JDK 21 to access native libraries.

## How it works

NightMonkeys plugins are released as multi-release JARs:

- with Java < 21, a no-op version of the plugin will unregister itself on load, basically doing nothing
- with Java 21, the plugin will be available
- the plugins are not compatible with other Java versions, as the Foreign Linker APIs are still changing

This lets you add the dependencies in your project whatever the JDK used, and still enable the plugin at runtime if the necessary JDK is used. 

## Supported formats

| Plugin               | Format                                                                                                               | Read | Write | Metadata | TwelveMonkeys Tests | Notes                              |
|----------------------|----------------------------------------------------------------------------------------------------------------------|------|-------|----------|---------------------|------------------------------------|
| [jxl](imageio-jxl)   | [Jpeg XL](https://jpeg.org/jpegxl/)                                                                                  | ✔    | -     | -        | ✔                   | See limitations in the plugin page |
| [webp](imageio-webp) | [WebP](https://developers.google.com/speed/webp)                                                                     | ✔    | -     | -        | ✔                   | See limitations in the plugin page |
| [heif](imageio-heif) | [HEIF](https://en.wikipedia.org/wiki/High_Efficiency_Image_File_Format) & [AVIF](https://en.wikipedia.org/wiki/AVIF) | ✔    | -     | -        | ✔                   | See limitations in the plugin page |

When possible, the plugins will use the extensive test suite
from [TwelveMonkeys](https://github.com/haraldk/TwelveMonkeys), which covers much more test cases than simple
decoding/encoding.

## Requirements

In order for the plugins to run properly, you will need to:

- Run Java 21 with the following options:

```
--enable-preview --enable-native-access=ALL-UNNAMED
```

- Make sure the path to the directory containing the native libraries is contained in the Java system
  property `java.library.path` (check
  also [this](https://stackoverflow.com/questions/20038789/default-java-library-path)).
  - For Linux, normally it works fine when installed from a package manager. You can add the libraries' path to
    the `LD_LIBRARY_PATH` environment variable.
  - For Mac, if using HomeBrew, you will need to set `JAVA_LIBRARY_PATH` to `/usr/local/lib/` or `/opt/homebrew/lib/`.

## Installation

### Gradle

```groovy
runtimeOnly "com.github.gotson.nightmonkeys:imageio-{plugin}:{version}"
```

### Gradle (Kotlin DSL)

```kotlin
runtimeOnly("com.github.gotson.nightmonkeys:imageio-{plugin}:{version}")
```

### Maven

```xml
<dependency>
  <groupId>com.github.gotson.nightmonkeys</groupId>
  <artifactId>imageio-{plugin}</artifactId>
  <version>{version}</version>
  <scope>runtime</scope>
</dependency>
```

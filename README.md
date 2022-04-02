[![GitHub Workflow Status](https://img.shields.io/github/workflow/status/gotson/NightMonkeys/CI?style=flat-square)](https://github.com/gotson/NightMonkeys/actions/workflows/ci.yml)

# NightMonkeys

A collection of ImageIO plugins, adding support for newer image formats. NightMonkeys uses the newer Foreign Linker API available in JDK 18 to access native libraries.

## How it works

NightMonkeys plugins are released as multi-release JARs:
- with Java < 18, a no-op version of the plugin will unregister itself on load, basically doing nothing
- with Java 18+, the plugin will be available

This lets you add the dependencies in your project whatever the JDK used, and still enable the plugin at runtime if the necessary JDK is used. 

## Supported formats

| Plugin             | Format                              | Read | Write | Metadata | Notes |
|--------------------|-------------------------------------|------|-------|----------|-------|
| [jxl](imageio-jxl) | [Jpeg XL](https://jpeg.org/jpegxl/) | ✔    | -     | -        |       |


## Requirements

In order for the plugins to run properly, you will need to:
- Run Java with the following options: `--add-modules jdk.incubator.foreign --enable-native-access=ALL-UNNAMED`
- Make sure the path to the directory containing the native libraries is contained in the Java system property `java.library.path` (check also [this](https://stackoverflow.com/questions/20038789/default-java-library-path)).
  - For Linux, normally it works fine when installed from a package manager. You can add the libraries' path to the `LD_LIBRARY_PATH` environment variable.
  - For Mac, if using HomeBrew, you will need to set `JAVA_LIBRARY_PATH` to `/usr/local/lib/`.

## Installation

<table>
<tr>
    <td>Gradle</td>
    <td>
        <pre>runtimeOnly "com.github.gotson:nightmonkeys-imageio-{plugin}:{version}"</pre>
    </td>
</tr>
<tr>
    <td>Gradle (Kotlin DSL)</td>
    <td>
        <pre>runtimeOnly("com.github.gotson:nightmonkeys-imageio-{plugin}:{version}")</pre>
        </td>
</tr>
<tr>
    <td>Maven</td>
    <td>
        <pre>&lt;dependency&gt;
    &lt;groupId&gt;com.github.gotson&lt;/groupId&gt;
    &lt;artifactId&gt;nightmonkeys-imageio-{plugin}&lt;/artifactId&gt;
    &lt;version&gt;{version}&lt;/version&gt;
    &lt;scope&gt;runtime&lt;/scope&gt;
&lt;/dependency&gt;</pre>
    </td>
</tr>
</table>


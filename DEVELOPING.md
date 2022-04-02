# Development guidelines

## Requirements

NightMonkeys relies on native libraries being installed on your system, and for the path of those libraries to be available for Java. Check the README of each plugin for details on how to install the required library for different OSes.

You will also need a version of Java to run Gradle. Gradle is configured to use the Java toolchains feature, and will download the necessary JDK if needed.

## Tests

There are 3 test tasks per project:
- `test`: will test the Java 8 classes
- `java18Test`: will test the Java 18 classes
- `noLibTest`: will test the Java 18 classes, but without the path to the libraries.

Run `./gradlew check` to run them all.
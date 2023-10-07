This project reproduces what I think is a bug in IntelliJ IDEA and thus Android
Studio.

I have reproduced it in IntelliJ 2023.2.2 and Android Studio Giraffe 2022.3.1
Patch 2.

I reported this as a [bug in IntelliJ IDEA][jbug] and as a [bug in Android
Studio][asbug].

[jbug]: https://youtrack.jetbrains.com/issue/IDEA-334328/
[asbug]: https://issuetracker.google.com/issues/303807958

The following all work in the IDE:

```
gradle :subbuild:check
gradle :subproject:check
gradle :check
```

The following individual tests also work if you click the play button next to
them in the IDE:

* `RootTest` runs

      gradle :cleanTestDebugUnitTest :testDebugUnitTest --tests com.foobar.root.RootTest
* `SubProjectTest` runs

      gradle :subproject:cleanTestDebugUnitTest :subproject:testDebugUnitTest --tests com.foobar.subproject.SubProjectTest

## The bug

`SubBuildTest` **fails** when run via the play button in the IDE. Clicking it runs

    gradle :cleanTestDebugUnitTest :testDebugUnitTest --tests com.foobar.subbuild.SubBuildTest

Note that the path to the subbuild is missing from the task names!

The error in the build log of Android Studio Giraffe is:

```
WARNING: Unsupported Kotlin plugin version.
The `embedded-kotlin` and `kotlin-dsl` plugins rely on features of Kotlin `1.6.21` that might work differently than in the requested version `1.9.0`.

FAILURE: Build failed with an exception.

* Where:
Build file 'subbuild/build.gradle.kts' line: 4Build file 'subbuild/build.gradle.kts' line: 4

* What went wrong:
An exception occurred applying plugin request [id: 'com.foobar.fizzbuzz']
> Failed to apply plugin 'com.android.internal.version-check'.
      > Minimum supported Gradle version is 8.0. Current version is 7.5.1. If using the gradle wrapper, try editing the distributionUrl in subbuild/gradle/wrapper/gradle-wrapper.properties to gradle-8.0-all.zip
```

In IntelliJ I get just the warning because the version is newer:

```
WARNING: Unsupported Kotlin plugin version.
The `embedded-kotlin` and `kotlin-dsl` plugins rely on features of Kotlin `1.8.20` that might work differently than in the requested version `1.9.0`.
```

## Analysis

The test passes if you run it like this

    gradle :subbuild:cleanTestDebugUnitTest :subbuild:testDebugUnitTest --tests com.foobar.subbuild.SubBuildTest

This is the correct way to run tasks in a composite build according to the
[Gradle docs](https://docs.gradle.org/current/userguide/composite_builds.html).

From the error message, it seems that the IDE is changing the working directory
to the subbuild and then running the task. This is not correct since it means
the IDE no longer sees the gradle wrapper configuration from the parent build.
It ends up building with whatever the default Gradle version is in the IDE
(Gradle 8.2 in IntelliJ 2023.2.2 and Gradle 7.5 in Android Studio Giraffe).

This causes a failure in Android Studio since the subbuild applies a custom
plugin using AGP, which requires Gradle 8. The warning is still concerning in
the newer version of IntelliJ since it means the custom plugins are being
compiled with the wrong version of Kotlin for the Gradle version, potentially
causing subtle bugs.

You can work around this by copying the Gradle wrapper/configuration into the
subbuild directory, but this should not be necessary. The IDE should understand
that if you run a test in a composite build, it should run it from the parent
build directory, applying the full path to the subbuild's task.

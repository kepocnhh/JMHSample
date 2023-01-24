repositories.mavenCentral()

plugins {
    id("org.jetbrains.kotlin.jvm")
    id("me.champeau.jmh") version "0.6.8"
}

val jvmTarget = "11"

tasks.getByName<JavaCompile>("compileJava") {
    targetCompatibility = jvmTarget
}

tasks.getByName<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>("compileKotlin") {
    kotlinOptions.jvmTarget = jvmTarget
}

jmh {
    excludes.set(setOf("FooBenchmark"))
    batchSize.set(1)
    fork.set(1)
    iterations.set(1)
    jmhTimeout.set("64s")
    jmhVersion.set("1.36")
    resultFormat.set("JSON")
    warmup.set("1s")
    warmupBatchSize.set(1)
    warmupForks.set(0)
    warmupIterations.set(0)
    threads.set(Thread.activeCount())
    verbosity.set("NORMAL")
    operationsPerInvocation.set(1)
}

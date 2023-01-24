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
    iterations.set(1)
    fork.set(1)
    jmhVersion.set("1.36")
    warmupIterations.set(1)
    warmup.set("1s")
    resultFormat.set("JSON")
}

repositories.mavenCentral()

plugins {
//    `java-library`
    id("org.jetbrains.kotlin.jvm")
}

val jmhVersion = "1.36"

dependencies {
    implementation(project(":lib"))
    implementation("org.openjdk.jmh:jmh-core:$jmhVersion")
    implementation("org.openjdk.jmh:jmh-generator-bytecode:$jmhVersion")
}

task<JavaExec>("runBenchmark") {
//    dependsOn("classes")
//    dependsOn("jmhRunBytecodeGenerator")
    dependsOn("jmhRuntimeClasspath")
    mainClass.set("org.openjdk.jmh.Main")
    classpath = sourceSets.main.get().runtimeClasspath
    classpath = files(
        sourceSets.main.get().runtimeClasspath,
        File(buildDir, "generated/resources"),
//        File(buildDir, "generated/sources")
    )
//    println("classesDirs: " + sourceSets.main.get().output.classesDirs.map { it.absolutePath })
//    println("runtimeClasspath: " + sourceSets.main.get().runtimeClasspath.joinToString(separator = "\n") { it.absolutePath })
//    classpath = kotlin.sourceSets.main.get().kotlin
//    classpath = sourceSets.main.get().runtimeClasspath + kotlin.sourceSets.main.get().kotlin
}

task<JavaExec>("jmhRunBytecodeGenerator") {
    dependsOn("classes")
    mainClass.set("org.openjdk.jmh.generators.bytecode.JmhBytecodeGenerator")
    classpath = sourceSets.main.get().runtimeClasspath
//    classpath = configurations.implementation.get()
    args(
        File(buildDir, "classes/kotlin/main"),
        File(buildDir, "generated/sources"),
        File(buildDir, "generated/resources"),
        "default"
    )
}

task<JavaCompile>("jmhRuntimeClasspath") {
    dependsOn("jmhRunBytecodeGenerator")
    classpath = sourceSets.main.get().runtimeClasspath
    source(
        File(buildDir, "generated/sources"),
//        File(buildDir, "generated/resources")
    )
    destinationDirectory.set(File(buildDir, "classes/kotlin/main"))
}

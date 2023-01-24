buildscript {
    repositories.mavenCentral()

    dependencies {
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.7.21")
    }
}

task<Delete>("clean") {
    delete = setOf(rootProject.buildDir)
}

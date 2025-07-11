plugins {
    kotlin("jvm") version "2.1.20"
    kotlin("plugin.serialization") version "1.9.0"
    id("com.github.johnrengelman.shadow") version "8.1.1"
}
tasks.shadowJar {
    manifest {
        attributes["Main-Class"] = "lazy.BotKt"
    }
}

group = "lazy"
version = "0.2.1"

repositories {
    mavenCentral()
    maven("https://oss.sonatype.org/content/repositories/snapshots")
}

dependencies {
    implementation("net.dv8tion:JDA:5.0.0") { // replace $version with the latest version
    }
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.10.2")
    implementation("club.minnced:jda-ktx:0.12.0")
    implementation("org.json:json:20250517")
    implementation("ch.qos.logback:logback-classic:1.5.6")
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(23)
}

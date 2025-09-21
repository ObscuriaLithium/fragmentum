plugins {
    id("multiloader-common")
    alias(libs.plugins.moddev)
}

val modId: String by project

neoForge {
    neoFormVersion = libs.versions.neoForm
    val at = file("src/main/resources/META-INF/accesstransformer.cfg")
    if (at.exists()) {
        accessTransformers.from(at.absolutePath)
    }
    parchment {
        minecraftVersion = libs.versions.parchmentMC
        mappingsVersion = libs.versions.parchment
    }
}

dependencies {
    compileOnly(libs.mixin)
    compileOnly("io.github.llamalad7:mixinextras-common:0.3.5")
    annotationProcessor("io.github.llamalad7:mixinextras-common:0.3.5")
}

configurations {
    create("commonJava") {
        isCanBeResolved = false
        isCanBeConsumed = true
    }
    create("commonKotlin") {
        isCanBeResolved = false
        isCanBeConsumed = true
    }
    create("commonResources") {
        isCanBeResolved = false
        isCanBeConsumed = true
    }
}

artifacts {
    add("commonJava", sourceSets.main.get().java.sourceDirectories.singleFile)
    add("commonKotlin", sourceSets.main.get().kotlin.sourceDirectories.filter { !it.name.endsWith("java") }.singleFile)
    add("commonResources", sourceSets.main.get().resources.sourceDirectories.singleFile)
}

tasks.named<Jar>("jar") {
    destinationDirectory.set(file("build/libs"))
}

tasks.register("releaseApi") {
    dependsOn(tasks.named("build"))
    doLast {
        copy {
            from(file("build/libs/$modId-api-${libs.versions.minecraft.get()}-$version.jar"))
            into(rootProject.file("output/$version"))
        }
    }
}

publishing {
    publications {
        create<MavenPublication>("apiPublication") {
            groupId = "dev.obscuria"
            artifactId = "fragmentum-api"
            version = "${libs.versions.minecraft.get()}-$version"
            artifact(tasks.named<Jar>("jar").get())
        }
    }

    repositories {
        maven {
            name = "local"
            url = rootProject.file("output/repo").toURI()
        }
    }
}
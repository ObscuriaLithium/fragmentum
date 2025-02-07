plugins {
    id("multiloader-common")
    alias(libs.plugins.moddev)
}

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

tasks.register<Jar>("apiJar") {
    group = "build"
    description = "Build JAR for the API package only"

    val outputDir = rootProject.file("output/$version/api")
    val artifactName = "fragmentum-api"
    val artifactVersion = "${libs.versions.minecraft.get()}-$version"

    from(sourceSets.main.get().output) {
        include("dev/obscuria/fragmentum/api/**")
    }

    archiveBaseName.set(artifactName)
    archiveVersion.set(artifactVersion)
    destinationDirectory.set(outputDir)
}

tasks.register<Jar>("apiSourcesJar") {
    group = "build"
    description = "Build sources JAR for the API package only"

    val outputDir = rootProject.file("output/${version}/api")
    val artifactName = "fragmentum-api"
    val artifactVersion = "${libs.versions.minecraft.get()}-${version}"

    from(sourceSets.main.get().allSource) {
        include("dev/obscuria/fragmentum/api/**")
    }

    archiveBaseName.set(artifactName)
    archiveVersion.set(artifactVersion)
    archiveClassifier.set("sources")
    destinationDirectory.set(outputDir)
}

tasks.register<Javadoc>("apiJavadoc") {
    group = "documentation"
    description = "Generate Javadoc for the API package only"

    source = sourceSets.main.get().allJava.matching {
        include("dev/obscuria/fragmentum/api/**")
    }

    classpath = sourceSets.main.get().compileClasspath
    setDestinationDir(file("${layout.buildDirectory}/api-javadoc"))
}

tasks.register<Jar>("apiJavadocJar") {
    group = "documentation"
    description = "Build Javadoc JAR for the API package only"

    val outputDir = rootProject.file("output/${version}/api")
    val artifactName = "fragmentum-api"
    val artifactVersion = "${libs.versions.minecraft.get()}-$version"

    dependsOn(tasks.named("apiJavadoc"))

    val javadocTask = tasks.named<Javadoc>("apiJavadoc").get()
    from(javadocTask.destinationDir)

    archiveBaseName.set(artifactName)
    archiveVersion.set(artifactVersion)
    archiveClassifier.set("javadoc")
    destinationDirectory.set(outputDir)
}

tasks.register("buildRelease") {
    group = "build"
    dependsOn("apiJar", "apiSourcesJar", "apiJavadocJar")
}

publishing {
    publications {
        create<MavenPublication>("apiPublication") {
            groupId = "dev.obscuria"
            artifactId = "fragmentum-api"
            version = "${libs.versions.minecraft.get()}-$version"
            artifact(tasks.named<Jar>("apiJar").get())
            artifact(tasks.named<Jar>("apiSourcesJar").get())
            artifact(tasks.named<Jar>("apiJavadocJar").get())
        }
    }

    repositories {
        maven {
            name = "local"
            url = rootProject.file("output/repo").toURI()
        }
    }
}
plugins {
    id("multiloader-loader")
    alias(libs.plugins.loom)
}

val modId: String by project

repositories {
    maven { url = uri("https://maven.terraformersmc.com/releases/") }
}

dependencies {
    minecraft(libs.minecraft)
    mappings(loom.layered {
        officialMojangMappings()
        parchment("org.parchmentmc.data:parchment-${libs.versions.parchmentMC.get()}:${libs.versions.parchment.get()}@zip")
    })
    modImplementation(libs.fabricLoader)
    modImplementation(libs.fabricApi)
    modImplementation(libs.flk)

    modImplementation("com.terraformersmc:modmenu:7.2.2")
}

loom {
    val aw = project(":common").file("src/main/resources/${modId}.accesswidener")
    if (aw.exists()) {
        accessWidenerPath.set(aw)
    }
    mixin {
        defaultRefmapName.set("${modId}.refmap.json")
    }
    runs {
        named("client") {
            client()
            configName = "Fabric Client"
            ideConfigGenerated(true)
            runDir("runs/client")
        }
        named("server") {
            server()
            configName = "Fabric Server"
            ideConfigGenerated(true)
            runDir("runs/server")
        }
    }
}

tasks.register("releaseFabric") {
    dependsOn(tasks.named("build"))
    doLast {
        copy {
            from(file("build/libs/$modId-fabric-${libs.versions.minecraft.get()}-$version.jar"))
            into(rootProject.file("output/$version"))
        }
    }
}

publishing {
    publications {
        create<MavenPublication>("apiPublication") {
            groupId = "dev.obscuria"
            artifactId = "fragmentum-fabric"
            version = "${libs.versions.minecraft.get()}-$version"

            artifact(tasks.named<Jar>("jar").get()) {
                classifier = null
            }
        }
    }

    repositories {
        maven {
            name = "local"
            url = rootProject.file("output/repo").toURI()
        }
    }
}
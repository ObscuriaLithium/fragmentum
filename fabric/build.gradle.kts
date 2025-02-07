plugins {
    id("multiloader-loader")
    alias(libs.plugins.loom)
}

val modId: String by project

dependencies {
    minecraft(libs.minecraft)
    mappings(loom.layered {
        officialMojangMappings()
        parchment("org.parchmentmc.data:parchment-${libs.versions.parchmentMC.get()}:${libs.versions.parchment.get()}@zip")
    })
    modImplementation(libs.fabricLoader)
    modImplementation(libs.fabricApi)

    modImplementation(libs.flk)
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
            setConfigName("Fabric Client")
            ideConfigGenerated(true)
            runDir("runs/client")
        }
        named("server") {
            server()
            setConfigName("Fabric Server")
            ideConfigGenerated(true)
            runDir("runs/server")
        }
    }
}

tasks.register("buildRelease") {
    dependsOn("build")
    group = "build"

    doLast {
        val output = rootProject.file("output/$version")
        copy {
            from(file("build/libs/$modId-fabric-${libs.versions.minecraft.get()}-$version.jar"))
            into(output)
        }
    }
}

publishing {
    publications {
        create<MavenPublication>("apiPublication") {
            groupId = "dev.obscuria"
            artifactId = "fragmentum-fabric"
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
plugins {
    alias(libs.plugins.kotlin)
    alias(libs.plugins.loom)
    alias(libs.plugins.mod.publish)
}

val id = project.property("mod_id") as String
group = project.property("maven_group") as String
version = project.property("mod_version") as String

base {
    archivesName.set(project.property("archives_base_name") as String)
}

repositories {
    mavenLocal()
    mavenCentral()
}

dependencies {
    minecraft(libs.minecraft)
    mappings(loom.officialMojangMappings())
    modImplementation(libs.fabric.loader)
    modImplementation(libs.fabric.language.kotlin)
}

java {
    toolchain.languageVersion = JavaLanguageVersion.of(17)
}

tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
    options.release.set(17)
}

tasks.processResources {
    val properties = mapOf(
        "id" to id,
        "version" to project.version,
        "name" to project.property("mod_name") as String,
    )
    filteringCharset = "UTF-8"
    inputs.properties(properties)
    filesMatching("fabric.mod.json") { expand(properties) }
}

publishMods {
    displayName = "${project.property("mod_name")} ${project.version}"
    file = tasks.remapJar.get().archiveFile
    type = STABLE
    modLoaders.add("fabric")

    modrinth {
        projectId = project.property("modrinth_id") as String
        accessToken = providers.environmentVariable("MODRINTH_TOKEN")
        minecraftVersions.add("1.20.1")
    }

    curseforge {
        projectId = project.property("curseforge_id") as String
        accessToken = providers.environmentVariable("CURSEFORGE_TOKEN")
        minecraftVersions.add("1.20.1")
    }

    github {
        repository = project.property("repository") as String
        accessToken = providers.environmentVariable("GITHUB_TOKEN")
        commitish = "main"
        tagName = "v${project.version}"
    }
}

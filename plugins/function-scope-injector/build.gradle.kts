import org.jetbrains.kotlin.ideaExt.idea

description = "Kotlin Function Scope Injector Compiler Plugin"

plugins {
    kotlin("jvm")
    id("jps-compatible")
}

dependencies {
    compileOnly(project(":compiler:fir:entrypoint"))
    compileOnly(project(":compiler:plugin-api"))
    compileOnly(intellijCoreDep()) { includeJars("intellij-core") }

    testApiJUnit5()
    testApi(projectTests(":compiler:tests-common-new"))

    testRuntimeOnly(project(":compiler:fir:fir-serialization"))
}

val generationRoot = projectDir.resolve("tests-gen")

sourceSets {
    "main" {
        projectDefault()
    }
    "test" {
        projectDefault()
        this.java.srcDir(generationRoot.name)
    }
}

if (kotlinBuildProperties.isInJpsBuildIdeaSync) {
    apply(plugin = "idea")
    idea {
        this.module.generatedSourceDirs.add(generationRoot)
    }
}

projectTest(parallel = true, jUnitMode = JUnitMode.JUnit5) {
    workingDir = rootDir
    useJUnitPlatform()
    jvmArgs!!.removeIf { it.contains("-Xmx") }
    maxHeapSize = "3g"
    dependsOn(":plugins:function-scope-injector:plugin-annotations:jar")
}

testsJar()

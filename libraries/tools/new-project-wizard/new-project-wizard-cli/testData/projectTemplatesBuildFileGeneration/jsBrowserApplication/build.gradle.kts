plugins {
    kotlin("js") version "1.3.70"
}
group = "me.user"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    jcenter()
    maven {
        url = uri("https://dl.bintray.com/kotlin/kotlin-js-wrappers")
    }
}
dependencies {
    testImplementation(kotlin("test-js"))
    implementation(kotlin("stdlib-js"))
    implementation("org.jetbrains.kotlinx:kotlinx-html-js:0.6.12")
    implementation("org.jetbrains:kotlin-react:16.9.0-pre.89-kotlin-1.3.60")
    implementation("org.jetbrains:kotlin-react-dom:16.9.0-pre.89-kotlin-1.3.60")
    implementation(npm("react","16.12.0"))
    implementation(npm("react-dom","16.12.0"))
    implementation(npm("react-is","16.12.0"))
    implementation("org.jetbrains:kotlin-styled:1.0.0-pre.89-kotlin-1.3.60")
    implementation(npm("styled-components","5.0.0"))
    implementation(npm("inline-style-prefixer","5.1.0"))
}
kotlin.target.browser { }
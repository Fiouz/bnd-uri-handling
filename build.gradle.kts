import aQute.bnd.gradle.Index

apply {
    plugin("java-library")
    plugin("biz.aQute.bnd.builder")
}
repositories {
    mavenCentral()
}
dependencies {
    JavaPlugin.IMPLEMENTATION_CONFIGURATION_NAME("org.slf4j:slf4j-api:1.7.25")
}
tasks.register<Index>("bndIndex") {
    description = "Creates an OSGi bundle repository file from the main source set runtime classpath."
    group = "OSGi"
    val classpath: FileCollection = project.the<SourceSetContainer>()[SourceSet.MAIN_SOURCE_SET_NAME].runtimeClasspath
    bundles(classpath)
    doFirst("Display classpath before indexing") {
        logger.lifecycle("Displaying classpath elements before actual bnd indexing:")
        classpath.forEach { file: File ->
            logger.lifecycle(" - {}", file)
        }
    }
}

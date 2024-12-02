plugins {
    kotlin("jvm") version "2.0.20"
}

version = "1.0.0"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}

val years = listOf(2018, 2024)

years.forEach { year ->
    sourceSets.main.get().kotlin.matching {
        include("**/y${year}/Day*.kt")
        exclude("**/y${year}/DayTemplate.kt")
    }.forEach { dayFile ->
        val className = dayFile.nameWithoutExtension

        tasks.register<JavaExec>("y${year}run${className}") {
            group = "advent"
            description = "Run $className"
            classpath = sourceSets.main.get().runtimeClasspath
            mainClass.set("net.dill.y${year}.${className}")
        }
    }

    tasks.register("y${year}") {
        group = "advent"
        description = "Run all Day classes for year $year"

        dependsOn(tasks.withType<JavaExec>().matching {
            it.name.startsWith("y${year}runDay")
        })
    }
}

tasks.register("createDay") {
    val year = project.findProperty("year") as? String ?: throw GradleException("Year is not specified")
    val day = project.findProperty("day") as? String ?: throw GradleException("Day is not specified")

    val formattedDay = day.padStart(2, '0')
    val sourceDir = file("src/main/kotlin/net/dill/y$year")
    val testSourceDir = file("src/test/kotlin/net/dill/y$year")
    val mainResourceDir = file("src/main/resources/$year")
    val testResourceDir = file("src/test/resources/$year")

    doLast {
        sourceDir.mkdirs()
        testSourceDir.mkdirs()
        mainResourceDir.mkdirs()
        testResourceDir.mkdirs()

        val templateFile = File(sourceDir, "DayTemplate.kt")
        val newFile = File(sourceDir, "Day$formattedDay.kt")
        if (!templateFile.exists()) throw GradleException("Template file not found: ${templateFile.path}")
        val updatedContent = templateFile.readText()
            .replace("DayTemplate", "Day$formattedDay")
            .replace("resourceLines($year, 0)", "resourceLines($year, $day)")
        newFile.writeText(updatedContent)

        val testTemplateFile = File(testSourceDir, "DayTemplateTest.kt")
        val newTestFile = File(testSourceDir, "Day${formattedDay}Test.kt")
        if (!testTemplateFile.exists()) throw GradleException("Test template file not found: ${testTemplateFile.path}")
        newTestFile.writeText(testTemplateFile.readText().replace("DayTemplate", "Day$formattedDay"))

        val newTestResourceFile = File(testResourceDir, "day$formattedDay.txt")
        if (!newTestResourceFile.exists()) {
            newTestResourceFile.createNewFile()
        }

        val newMainResourceFile = File(mainResourceDir, "day$formattedDay.txt")
        if (!newMainResourceFile.exists()) {
            newMainResourceFile.createNewFile()
        }

        println("Created files for Day$formattedDay in $year")
    }
}

kotlin {
    jvmToolchain(21)
}

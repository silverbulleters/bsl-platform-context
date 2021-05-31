import groovy.json.JsonSlurper
import java.nio.charset.StandardCharsets
import javax.inject.Inject
import kotlin.streams.toList

open class GeneratePlatformTypeIdentifier @Inject constructor(objects: ObjectFactory) : DefaultTask() {
    private val pathToTemplate = "gradle/template/TemplatePlatformTypeIdentifier.java"
    private val pathToResultFile = "src/main/java/org/silverbulleters/bsl/platform/context/types/PlatformTypeIdentifier.java"
    private val pathToIdentifiers = "src/main/resources/identifiers.json"
    private val valueFormat = "  %s(\"%s\",\"%s\"),"
    private val contentReplace = "  UNKNOWN(\"\", \"\");"

    @TaskAction
    fun generate() {
        val stringValues = getEnumValuesString() + "\n" + contentReplace
        val content = getTextFromFile(pathToTemplate)
                .replace("TemplatePlatformTypeIdentifier", "PlatformTypeIdentifier")
                .replace(contentReplace, stringValues);

        val file = File(pathToResultFile)
        file.writeText(content, charset("UTF-8"))
    }

    private fun getTextFromFile(path: String): String {
        return File(path).readText(StandardCharsets.UTF_8);
    }

    private fun getEnumValuesString(): String {
        val data = getMapFromJson()
        val identifiers = data.toMap()["Identifiers"] as ArrayList<*>
        return identifiers.stream()
                .map { it as Map<*, *> }
                .map { String.format(valueFormat, it["Id"], it["Name"], it["NameRu"]) }
                .toList()
                .joinToString("\n")
    }

    private fun getMapFromJson(): Map<*, *> {
        val content = getTextFromFile(pathToIdentifiers)
        val jsonSlurper = JsonSlurper()
        return jsonSlurper.parseText(content) as Map<*, *>
    }

}

tasks.register<GeneratePlatformTypeIdentifier>("generatePlatformTypes") {
    description = "Generate platform identifier types";
    group = "Generation tools";
}
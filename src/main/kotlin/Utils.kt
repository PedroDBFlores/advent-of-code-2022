fun String.splitMultiline() = split("\\r?\\n|\\r".toRegex())

fun getResourceFile(path: String) = {}::class.java.getResource(path)!!.readText()

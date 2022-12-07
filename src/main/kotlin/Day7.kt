/*
    Day 7 - No Space Left On Device
 */

fun smallDirectoriesSum(input: String): Int {
    val rootFiles = listOf<File>()
    input.splitMultiline()
        .map { it.toLineType() }
        .forEach {
            when (it) {
                is Command -> {
                    if (it.name == "cd") {

                    }
                }
                is Directory -> TODO()
                is File -> TODO()
            }
        }
    return 0
}

private sealed class LineType(val input: String) {
    val arguments: List<String> get() = input.split(" ")
}

private class Command(input: String) : LineType(input) {
    val name get() = arguments[1]
    val argument get() = arguments[2]
}

private class Directory(input: String) : LineType(input) {
    val name = arguments[1]
}

private class File(input: String) : LineType(input) {
    val fileSize get() = arguments[0].toInt()
    val fileName get() = arguments[1]
}

private fun String.toLineType() = if (this.startsWith("$")) {
    Command(this)
} else if (this.startsWith("dir")) {
    Directory(this)
} else {
    File(this)
}
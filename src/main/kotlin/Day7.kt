/*
    Day 7 - No Space Left On Device
 */

const val MAX_SPACE = 70000000
const val UPDATE_SIZE = 30000000

fun smallDirectoriesSum(input: String): Long {
    val pwd = Directory("root")
    pwd.directories["/"] = Directory("/", pwd)
    val lineTypes = input.splitMultiline()
        .map { it.toLineType() }

    buildDirectoryTree(lineTypes, pwd)

    return getSmallestDirectorySum(pwd)
}

private tailrec fun buildDirectoryTree(lineTypes: List<LineType>, pwd: Directory?) {
    if (lineTypes.isEmpty()) return

    val line = lineTypes.first()
    val rest = lineTypes.drop(1)
    when (line) {
        is Command -> {
            if (line.name == "cd") {
                if (line.argument == "..") {
                    buildDirectoryTree(rest, pwd?.parent)
                } else {
                    val directory = pwd?.directories?.get(line.argument)
                    buildDirectoryTree(rest, directory)
                }
            } else if (line.name == "ls") {
                buildDirectoryTree(rest, pwd)
            }
        }

        is DirectoryIndicator -> {
            pwd?.directories?.set(line.name, Directory(line.name, pwd))
            buildDirectoryTree(rest, pwd)
        }

        is File -> {
            pwd?.files?.add(line)
            buildDirectoryTree(rest, pwd)
        }
    }
}

private sealed class LineType(val input: String) {
    val arguments: List<String> get() = input.split(" ")
}

private class Command(input: String) : LineType(input) {
    val name get() = arguments[1]
    val argument get() = arguments.getOrElse(2) { "" }

    override fun toString() = "$name $argument"
}

private class DirectoryIndicator(input: String) : LineType(input) {
    val name = arguments[1]

    override fun toString() = "dir $name"
}

private class File(input: String) : LineType(input) {
    val fileSize get() = arguments[0].toInt()
    val fileName get() = arguments[1]

    override fun toString() = "$fileSize $fileName"
}

private fun String.toLineType() = if (this.startsWith("$")) {
    Command(this)
} else if (this.startsWith("dir")) {
    DirectoryIndicator(this)
} else {
    File(this)
}

private data class Directory(private val name: String, val parent: Directory? = null) {
    val directories = mutableMapOf<String, Directory>()
    val files = mutableListOf<File>()

    fun getDirectorySize(): Long {
        var size = 0L
        size += files.sumOf { it.fileSize }
        size += directories
            .map { it.value }
            .sumOf { it.getDirectorySize() }
        return size
    }
}

private fun getSmallestDirectorySum(directory: Directory): Long {
    var sum = 0L
    directory.directories.forEach { (_, v) ->
        val directorySize = v.getDirectorySize()
        if (directorySize <= 100000) {
            sum += directorySize
        }
        sum += getSmallestDirectorySum(v)
    }
    return sum
}

//private fun smallestDeletableDir(directory: Directory, spaceNeeded: Int, winner)  {
//    if (directory.getDirectorySize() in (spaceNeeded + 1) until winner) {
//        winner = getNodeSize(node)
//
//    }
//    node.nodes.values.forEach {
//        smallestDeletableDir(it)
//    }
//
//}

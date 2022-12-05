fun rearrangeCrates(input: String): String {
    val lines = input.splitMultiline()
    val splitIndex = lines.indexOfFirst { it.isEmpty() || it.isBlank() }
    val crateRepresentation = lines.subList(0, splitIndex)
    val commands = lines.subList(splitIndex + 1, lines.size).map {
        it.toCrateOperation()
    }
    coise(crateRepresentation)

    return ""
}

private fun coise(crateRepresentation: List<String>) {
    crateRepresentation.dropLast(1).reversed().forEach {

    }
}


data class Crate(val letter: String)

data class CrateOperation(
    val numberOfCrates: Int,
    val fromStack: Int,
    val toStack: Int
)

private fun String.toCrateOperation() = this.filter {
    it.isDigit()
}.toCharArray()
    .let {
        CrateOperation(
            numberOfCrates = it[0].digitToInt(),
            fromStack = it[1].digitToInt(),
            toStack = it[2].digitToInt()
        )
    }
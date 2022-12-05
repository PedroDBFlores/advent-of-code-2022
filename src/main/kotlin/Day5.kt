import java.util.Stack
import java.util.regex.Pattern

/*
    Day 5 - Supply Stacks
 */

typealias CrateStacks = Map<Int, Stack<Crate>>

fun rearrangeCrates(input: String): String {
    val lines = input.splitMultiline()
    val splitIndex = lines.indexOfFirst { it.isEmpty() || it.isBlank() }
    val crateRepresentation = lines.subList(0, splitIndex)
    val commands = lines.subList(splitIndex + 1, lines.size).map {
        it.toCrateOperation()
    }
    val stacks = initialStacks(crateRepresentation)
    val stacksAfterCommands = executeCommands(stacks, commands)

    return stacksAfterCommands.map { it.value }.fold("") { acc, crates ->
        acc.plus(crates.peek().letter)
    }
}

private fun initialStacks(crateRepresentation: List<String>): CrateStacks {
    val stackPositions = crateRepresentation.last().mapIndexedNotNull { i, c ->
        if (c.isDigit())
            return@mapIndexedNotNull i
        return@mapIndexedNotNull null
    }
    val stacks = List(stackPositions.size) { i -> i + 1 to Stack<Crate>() }.toMap()

    crateRepresentation.dropLast(1).reversed().forEach { line ->
        val stuff = stackPositions.map { line.getOrElse(it) { ' ' } }
        val crates = stuff.map { c ->
            if (!c.isWhitespace()) {
                return@map c.toCrate()
            }
            return@map null
        }
        (1..stackPositions.size).forEach { stackNumber ->
            val x = crates[stackNumber - 1]
            x?.run { stacks[stackNumber]!!.push(this) }
        }
    }
    return stacks
}

private fun executeCommands(crateStacks: CrateStacks, operations: List<CrateOperation>): CrateStacks {
    operations.forEach { (cratesToBeMoved, fromStack, toStack) ->
        for (n in cratesToBeMoved downTo 0) {
            if (n > 0) {
                val originCrate = crateStacks[fromStack]!!.pop()
                crateStacks[toStack]!!.push(originCrate)
            }
        }
    }
    return crateStacks
}

data class Crate(val letter: Char)

data class CrateOperation(
    val numberOfCrates: Int,
    val fromStack: Int,
    val toStack: Int
)

private fun Char.toCrate() = Crate(this)

private fun String.toCrateOperation(): CrateOperation {
    return Regex("\\d+").findAll(this).map { it.value }.toList().let {
        CrateOperation(
            numberOfCrates = it[0].toInt(),
            fromStack = it[1].toInt(),
            toStack = it[2].toInt()
        )
    }
}
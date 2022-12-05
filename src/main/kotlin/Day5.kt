import java.util.*

/*
    Day 5 - Supply Stacks
 */
private typealias CrateStacks = Map<Int, Stack<Crate>>

fun rearrangeCratesPart1(input: String): String =
    rearrangeCrates(input, ::executeCommands)

fun rearrangeCratesPart2(input: String): String =
    rearrangeCrates(input, ::executeCommandsMovingAsAStack)

private fun rearrangeCrates(input: String, action: (CrateStacks, List<CrateOperation>) -> CrateStacks): String {
    val (crateRepresentation, commands) = extractCrateRepresentationAndCommands(input)

    val stacks = initialStacks(crateRepresentation)
    val stacksAfterCommands = action(stacks, commands)

    return stacksAfterCommands.map { it.value }.fold("") { acc, crates ->
        acc.plus(crates.peek().letter)
    }
}

private fun extractCrateRepresentationAndCommands(input: String): Pair<List<String>, List<CrateOperation>> =
    input.splitMultiline().let { it ->
        Pair(it, it.indexOfFirst { it.isEmpty() || it.isBlank() })
    }.let { (lines, splitIndex) ->
        val crateRepresentation = lines.subList(0, splitIndex)
        val commands = lines.subList(splitIndex + 1, lines.size).map { it.toCrateOperation() }
        Pair(crateRepresentation, commands)
    }

private fun initialStacks(crateRepresentation: List<String>): CrateStacks {
    val stackPositions = crateRepresentation.last().mapIndexedNotNull { i, c ->
        if (c.isDigit())
            return@mapIndexedNotNull i
        return@mapIndexedNotNull null
    }
    val stacks = List(stackPositions.size) { i -> i + 1 to Stack<Crate>() }.toMap()

    crateRepresentation
        .dropLast(1)
        .reversed()
        .forEach { line ->
            val crates = stackPositions.map {
                line.getOrElse(it) { ' ' }
            }.map { c ->
                if (!c.isWhitespace()) {
                    return@map c.toCrate()
                }
                return@map null
            }
            (1..stackPositions.size).forEach { stackNumber ->
                crates[stackNumber - 1]?.run { stacks[stackNumber]!!.push(this) }
            }
        }
    return stacks
}

private fun executeCommands(crateStacks: CrateStacks, operations: List<CrateOperation>): CrateStacks {
    operations.forEach { (cratesToBeMoved, fromStack, toStack) ->
        (1..cratesToBeMoved).forEach { _ ->
            crateStacks[toStack]!!.push(crateStacks[fromStack]!!.pop())
        }
    }
    return crateStacks
}

private fun executeCommandsMovingAsAStack(crateStacks: CrateStacks, operations: List<CrateOperation>): CrateStacks {
    operations.forEach { (cratesToBeMoved, fromStack, toStack) ->
        (1..cratesToBeMoved)
            .map { crateStacks[fromStack]!!.pop() }
            .reversed()
            .forEach { crateStacks[toStack]!!.push(it) }
    }
    return crateStacks
}

private data class Crate(val letter: Char)

private data class CrateOperation(
    val numberOfCrates: Int,
    val fromStack: Int,
    val toStack: Int
)

private fun Char.toCrate() = Crate(this)

private fun String.toCrateOperation(): CrateOperation =
    Regex("\\d+")
        .findAll(this)
        .map { it.value }
        .toList()
        .let {
            CrateOperation(
                numberOfCrates = it[0].toInt(),
                fromStack = it[1].toInt(),
                toStack = it[2].toInt()
            )
        }
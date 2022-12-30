import java.lang.IllegalArgumentException

fun sumSignalStrengths(input: String, cyclesToObserve: List<Int>): Int = input
    .splitMultiline()
    .map { it.split(" ") }
    .map { it.toOp() }
    .let(::computeCommands)
    .filter { p -> cyclesToObserve.contains(p.first) }
    .sumOf { p -> p.first * p.second }

fun crtOutput(input: String): String = input
    .splitMultiline()
    .map { it.split(" ") }
    .map { it.toOp() }
    .let(::computeCommands)
    .chunked(40)
    .map { row ->
        row.mapIndexed { index, (_, register) ->
            val spriteRange = register - 1..register + 1
            if (spriteRange.contains(index + 1)) return@mapIndexed "#"
            return@mapIndexed "."
        }.joinToString("")
    }.joinToString("\r\n")


//    .joinToString("") { (cycle, register) ->
//        val spriteRange = register - 1..register + 1
//        if (spriteRange.contains(cycle)) return@joinToString "#"
//        return@joinToString "."
//    }
//    .chunked(40)
//    .joinToString("\r\n")

private fun computeCommands(commands: List<Op>): List<Pair<Int, Int>> {
    var cycle = 0
    var register = 1
    var nextCommandCycle: Int
    return commands.flatMap { command ->
        val states = mutableListOf<Pair<Int, Int>>()
        nextCommandCycle = cycle + command.cycles
        while (nextCommandCycle != cycle) {
            cycle++
            states.add(cycle to register)
        }
        if (command is Addx) {
            register += command.value
        }
        states
    }
}

private sealed class Op(val cycles: Int)
private class Noop : Op(1)
private class Addx(val value: Int) : Op(2)

private fun List<String>.toOp(): Op = when (this[0]) {
    "noop" -> Noop()
    "addx" -> Addx(this[1].toInt())
    else -> throw IllegalArgumentException("Unsupported CPU command")
}
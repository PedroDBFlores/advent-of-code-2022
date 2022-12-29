import java.lang.IllegalArgumentException
import java.util.LinkedList

fun sumSignalStrengths(input: String, cyclesToObserve: List<Int>): Int {
    val commands = input
        .splitMultiline()
        .map { it.split(" ") }
        .map { it.toOp() }
    val commandQueue = LinkedList(commands)

    var sum = 0
    var cycle = 0
    var nextCommandCycle: Int
    var registerX = 1
    commandQueue.iterator().forEach { command ->
        nextCommandCycle = cycle + command.cycles
        while (nextCommandCycle != cycle) {
            cycle++
            if (cyclesToObserve.contains(cycle)) {
                sum += cycle * registerX
            }
        }
        if (command is Addx) {
            registerX += command.value
        }
    }
    return sum
}

private sealed class Op(val cycles: Int)
private class Noop : Op(1)
private class Addx(val value: Int) : Op(2)

private fun List<String>.toOp(): Op = when (this[0]) {
    "noop" -> Noop()
    "addx" -> Addx(this[1].toInt())
    else -> throw IllegalArgumentException("Unsupported CPU command")
}
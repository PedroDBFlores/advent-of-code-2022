import kotlin.math.abs
import kotlin.math.sign

fun tailVisit(input: String, ropeSize: Int = 2): Int {
    val rope = Rope(ropeSize)
    input.splitMultiline()
        .map { it.split(" ") }
        .map { it.toRopeCommand() }
        .forEach { rope.move(it) }

    return rope.uniqueTailVisits
}

private data class RopeCommand(
    val direction: Direction,
    val steps: Int,
)

private fun String.toDirection() = when (this) {
    "U" -> Direction.Up
    "D" -> Direction.Down
    "L" -> Direction.Left
    "R" -> Direction.Right
    else -> throw IllegalArgumentException("Invalid command")
}

private fun List<String>.toRopeCommand() = RopeCommand(
    direction = this[0].toDirection(),
    steps = this[1].toInt()
)

private data class Coordinates(val x: Int = 0, val y: Int = 0)

private class Rope(private val size: Int) {
    private val nodes = (1..size).map { Coordinates() }.toMutableList()
    private val tailVisits: MutableSet<Coordinates> = mutableSetOf(Coordinates(0, 0))

    fun move(command: RopeCommand): Unit = with(command) {
        (steps downTo 1).forEach { _ ->
            nodes[0] = when (direction) {
                Direction.Up -> nodes[0].copy(y = nodes[0].y + 1)
                Direction.Down -> nodes[0].copy(y = nodes[0].y - 1)
                Direction.Left -> nodes[0].copy(x = nodes[0].x - 1)
                Direction.Right -> nodes[0].copy(x = nodes[0].x + 1)
            }
            (0 until size - 1).forEach { pos ->
                val offsetX = nodes[pos].x - nodes[pos + 1].x
                val offsetY = nodes[pos].y - nodes[pos + 1].y
                if (abs(offsetX) > 1 || abs(offsetY) > 1) {
                    nodes[pos + 1] = nodes[pos + 1].copy(
                        x = nodes[pos + 1].x + offsetX.sign,
                        y = nodes[pos + 1].y + offsetY.sign
                    )
                    if (nodes.lastIndex == pos + 1) {
                        tailVisits.add(nodes[nodes.lastIndex])
                    }
                }
            }
        }
    }

    val uniqueTailVisits get() = tailVisits.size
}
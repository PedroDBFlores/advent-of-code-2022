import kotlin.math.abs
import kotlin.math.sign

fun tailVisit(input: String): Int {
    val rope = Rope()
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

private class Rope {
    private var headCoordinates = Coordinates()
    private var tailCoordinates = Coordinates()
        set(value) {
            field = value
            tailVisits.add(value)
        }
    private val tailVisits: MutableSet<Coordinates> = mutableSetOf(tailCoordinates)

    fun move(command: RopeCommand): Unit = with(command) {
        (steps downTo 1).forEach { _ ->
            headCoordinates = when (direction) {
                Direction.Up -> headCoordinates.copy(y = headCoordinates.y + 1)
                Direction.Down -> headCoordinates.copy(y = headCoordinates.y - 1)
                Direction.Left -> headCoordinates.copy(x = headCoordinates.x - 1)
                Direction.Right -> headCoordinates.copy(x = headCoordinates.x + 1)
            }
            val offsetX = headCoordinates.x - tailCoordinates.x
            val offsetY = headCoordinates.y - tailCoordinates.y
            if (abs(offsetX) > 1 || abs(offsetY) > 1) {
                tailCoordinates = tailCoordinates.copy(
                    x = tailCoordinates.x + offsetX.sign,
                    y = tailCoordinates.y + offsetY.sign
                )
            }
        }
    }

    val uniqueTailVisits get() = tailVisits.size
}
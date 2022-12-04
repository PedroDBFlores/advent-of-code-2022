/*
    Dec 3 - Camp Cleanup
 */

fun countElvesThatFullyOverlap(input: String): Int = mapSections(input)
    .sumOf {
        countIf { it.first.existsIn(it.second) || it.second.existsIn(it.first) }
    }

fun countElvesThatPartiallyOverlap(input: String): Int = mapSections(input)
    .sumOf { countIf { it.first.intersect(it.second).isNotEmpty() } }

private fun countIf(action: () -> Boolean) = if (action()) 1 else 0

private fun mapSections(input: String) = input.splitMultiline()
    .map { it.split(",") }
    .map { (firstElfSections, secondElfSections) ->
        Pair(firstElfSections.toRange(), secondElfSections.toRange())
    }

private fun String.toRange() = split('-')
    .run { IntRange(this[0].toInt(), this[1].toInt()) }

private fun IntRange.existsIn(other: IntRange) =
    this.all { other.contains(it) }
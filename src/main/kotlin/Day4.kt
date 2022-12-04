/*
    Dec 3 - Camp Cleanup
 */

fun campCleanup(input: String): Int {
    val lines = input.splitMultiline()
    val elvesSections = lines.map {
        val (firstElfSections, secondElfSections) = it.split(",")
        Pair(firstElfSections.toRange(), secondElfSections.toRange())
    }
    val matchingSections = elvesSections.sumOf {
        if (it.first.existsIn(it.second) || it.second.existsIn(it.first))
            return@sumOf 1L
        return@sumOf 0L
    }

    return matchingSections.toInt()
}

private fun String.toRange() = split('-')
    .run { IntRange(this[0].toInt(), this[1].toInt()) }

private fun IntRange.existsIn(other: IntRange) =
    this.all { other.contains(it) }
/*
    Day 6 - Tuning Trouble
 */

fun numberOfCharactersProcessedBeforeSOPMarkerPart1(input: String): Int {
    (0..input.length).forEach { index ->
        val slice = input.slice(index..index + 3)
        if (slice.allUnique()) {
            return index + 4
        }
    }
    return -1
}

fun numberOfCharactersProcessedBeforeSOPMarkerPart2(input: String): Int {
    (0..input.length).forEach { index ->
        val slice = input.slice(index..index + 13)
        if (slice.allUnique()) {
            return index + 14
        }
    }
    return -1
}

private fun String.allUnique(): Boolean = all(hashSetOf<Char>()::add)

/*
    Day 6 - Tuning Trouble
 */

fun numberOfCharactersProcessedBeforeSOPMarkerPart1(input: String): Int =
    numberOfCharactersProcessedBeforeSOPMarker(input, 4)

fun numberOfCharactersProcessedBeforeSOPMarkerPart2(input: String): Int =
    numberOfCharactersProcessedBeforeSOPMarker(input, 14)

private fun numberOfCharactersProcessedBeforeSOPMarker(input: String, sizeOfSlice: Int): Int {
    (0..input.length)
        .map { index ->
            if (input.slice(index until index + sizeOfSlice).allUnique()) {
                return index + sizeOfSlice
            }
        }
    return -1
}


private fun String.allUnique(): Boolean = all(hashSetOf<Char>()::add)

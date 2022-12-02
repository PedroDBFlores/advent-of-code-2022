/*
    Dec 1 - Calorie Counting
 */

fun calorieCounting(input: String) =
    input.split("\\r?\\n|\\r".toRegex()).fold(mutableListOf(0)) { acc, s ->
        if (s.isNotEmpty()) {
            acc[acc.size - 1] += s.toInt()
        } else {
            acc.add(0)
        }
        return@fold acc
    }.max()
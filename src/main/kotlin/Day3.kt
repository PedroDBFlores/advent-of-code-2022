/*
    Dec 3 - Rucksack Optimization
 */

fun sumPrioritiesSharedItemTypesInRucksacks(input: String): Int =
    input.split("\\r?\\n|\\r".toRegex())
        .map { line ->
            splitString(line)
                .let { (firstCompartment, secondCompartment) ->
                    val rucksack = Rucksack(firstCompartment, secondCompartment)
                    return@map rucksack.getCommonItems()
                        .sumOf { allPriorities.getOrDefault(it, 0) }
                }
        }.sum()

fun sumPrioritiesSharedItemTypesByElfGroups(input: String): Int {
    val u = input.split("\\r?\\n|\\r".toRegex())
        .chunked(3)
        .map { elvesGroup ->
            val first = elvesGroup.first()
            val commonChars = first.fold(mutableSetOf<Char>()) { acc, item ->
                if (elvesGroup.all { it.contains(item) }) {
                    acc.add(item)
                }
                acc
            }
            return@map commonChars.sumOf { allPriorities.getOrDefault(it, 0) }
        }

    return u.sum()
}

data class Rucksack(val firstCompartment: String, val secondCompartment: String) {
    fun getCommonItems() = firstCompartment.fold(mutableSetOf<Char>()) { acc, c ->
        if (secondCompartment.contains(c)) {
            acc.add(c)
        }
        acc
    }.toSet()
}

private fun splitString(input: String) =
    (input.length / 2).let {
        arrayOf(input.substring(0, it), input.substring(it))
    }

private val lowercaseCharPriorities = ('a'..'z')
    .fold(mutableMapOf<Char, Int>()) { acc, c ->
        acc[c] = acc.getOrDefault(c - 1, 0) + 1
        acc
    }.toMap()

private val uppercaseCharPriorities = ('A'..'Z')
    .fold(mutableMapOf<Char, Int>()) { acc, c ->
        acc[c] = acc.getOrDefault(c - 1, 26) + 1
        acc
    }.toMap()

private val allPriorities = lowercaseCharPriorities + uppercaseCharPriorities


/*
    Dec 3 - Rucksack Optimization
 */

fun sumPrioritiesSharedItemTypesInRucksacks(input: String): Int =
    input.splitMultiline()
        .map { line ->
            splitString(line)
                .let { (firstCompartment, secondCompartment) ->
                    return@map Rucksack(firstCompartment, secondCompartment).getCommonItems()
                        .calculatePriorities()
                }
        }.sum()

fun sumPrioritiesSharedItemTypesByElfGroups(input: String): Int =
    input.splitMultiline()
        .chunked(3)
        .map { elvesGroup ->
            return@map elvesGroup.first().fold<MutableSet<Char>>(mutableSetOf()) { acc, item ->
                if (elvesGroup.all { it.contains(item) }) {
                    acc.add(item)
                }
                acc
            }.calculatePriorities()
        }.sum()

data class Rucksack(val firstCompartment: String, val secondCompartment: String) {
    fun getCommonItems() = firstCompartment.fold(mutableSetOf<Char>()) { acc, c ->
        if (secondCompartment.contains(c)) {
            acc.add(c)
        }
        acc
    }.toSet()
}

private fun Set<Char>.calculatePriorities() =
    this.sumOf { allPriorities.getOrDefault(it, 0) }

private fun splitString(input: String) = (input.length / 2).let {
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


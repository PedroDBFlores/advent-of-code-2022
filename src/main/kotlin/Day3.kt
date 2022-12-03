/*
    Dec 3 - Rucksack Optimization
 */

fun sumPrioritiesSharedItemTypesInRucksack(input: String): Int =
    input.split("\\r?\\n|\\r".toRegex())
        .map { rucksack ->
            val mid = rucksack.length / 2
            arrayOf(rucksack.substring(0, mid), rucksack.substring(mid))
                .let { (firstHalf, secondHalf) ->
                    return@map firstHalf.fold(mutableSetOf<Char>()) { acc, c ->
                        if (secondHalf.contains(c)) {
                            acc.add(c)
                        }
                        acc
                    }.sumOf { allPriorities.getOrDefault(it, 0) }
                }
        }.sum()

val lowercaseCharPriorities = ('a'..'z')
    .fold(mutableMapOf<Char, Int>()) { acc, c ->
        acc[c] = acc.getOrDefault(c - 1, 0) + 1
        acc
    }.toMap()

val uppercaseCharPriorities = ('A'..'Z')
    .fold(mutableMapOf<Char, Int>()) { acc, c ->
        acc[c] = acc.getOrDefault(c - 1, 26) + 1
        acc
    }.toMap()

val allPriorities = lowercaseCharPriorities + uppercaseCharPriorities


/*
    Dec 3 - Rucksack Optimization
 */

fun sumPrioritiesSharedItemTypesInRucksack(input: String): Int {
    val rucksacks = input.split("\\r?\\n|\\r".toRegex())
    val sums = rucksacks.map { rucksack ->
        val mid = rucksack.length / 2
        val parts = arrayOf(rucksack.substring(0, mid), rucksack.substring(mid))

        val commonItems = parts[0].fold(mutableSetOf<Char>()) { acc, c ->
            if (parts[1].contains(c)) {
                acc.add(c)
            }
            acc
        }

        return@map commonItems.sumOf {
            allPriorities.getOrDefault(it, 0)
        }
    }
    return sums.sum()
}

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


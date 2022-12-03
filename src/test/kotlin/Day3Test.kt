import io.kotest.core.spec.style.DescribeSpec
import io.kotest.data.row
import io.kotest.matchers.shouldBe

class Day3Test : DescribeSpec({
    val adventCodeInputFile = Day3Test::class
        .java
        .getResource("day3_input.txt")!!
        .readText()

    listOf(
        row("vJrwpWtwJgWrhcsFMMfFFhFp", 16),
        row("jqHRNqRjqzjGDLGLrsFMfFZSrLrFZsSL", 38),
        row("PmmdzqPrVvPwwTWBwg", 42),
        row("wMqvLMZHhHMvwLHjbvcjnnSBnvTQFn", 22),
        row("ttgJtRGJQctTZtZT", 20),
        row("CrZsJsPPZsGzwwsLwLmpwMDw", 19),
    ).forEach { (rucksackContents, expectedPriority) ->
        it("finds the priorities of shared item types in a rucksack for $rucksackContents") {
            val result = sumPrioritiesSharedItemTypesInRucksack(rucksackContents)

            result shouldBe expectedPriority
        }
    }

    it("should return the sum of priorities for multiple rucksacks") {
        val multipleRucksackInputs = """
            vJrwpWtwJgWrhcsFMMfFFhFp
            jqHRNqRjqzjGDLGLrsFMfFZSrLrFZsSL
            PmmdzqPrVvPwwTWBwg
            wMqvLMZHhHMvwLHjbvcjnnSBnvTQFn
            ttgJtRGJQctTZtZT
            CrZsJsPPZsGzwwsLwLmpwMDw
        """.trimIndent()

        val result = sumPrioritiesSharedItemTypesInRucksack(multipleRucksackInputs)

        result shouldBe 157
    }

    it("should return the sum of priorities for my puzzle") {
        val result = sumPrioritiesSharedItemTypesInRucksack(adventCodeInputFile)

        result shouldBe 7826
    }
})
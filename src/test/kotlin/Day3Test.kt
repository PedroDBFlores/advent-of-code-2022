import io.kotest.core.spec.style.DescribeSpec
import io.kotest.data.row
import io.kotest.matchers.shouldBe

class Day3Test : DescribeSpec({
    val adventCodeInputFile = getResourceFile("day3_input.txt")

    describe("Part 1") {
        listOf(
            row("vJrwpWtwJgWrhcsFMMfFFhFp", 16),
            row("jqHRNqRjqzjGDLGLrsFMfFZSrLrFZsSL", 38),
            row("PmmdzqPrVvPwwTWBwg", 42),
            row("wMqvLMZHhHMvwLHjbvcjnnSBnvTQFn", 22),
            row("ttgJtRGJQctTZtZT", 20),
            row("CrZsJsPPZsGzwwsLwLmpwMDw", 19),
        ).forEach { (rucksackContents, expectedPriority) ->
            it("finds the priorities of shared item types in a rucksack for $rucksackContents") {
                val result = sumPrioritiesSharedItemTypesInRucksacks(rucksackContents)

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

            val result = sumPrioritiesSharedItemTypesInRucksacks(multipleRucksackInputs)

            result shouldBe 157
        }

        it("should return the sum of priorities for my puzzle") {
            val result = sumPrioritiesSharedItemTypesInRucksacks(adventCodeInputFile)

            result shouldBe 7826
        }
    }

    describe("Part 2") {
        listOf(
            row(
                """
                    vJrwpWtwJgWrhcsFMMfFFhFp
                    jqHRNqRjqzjGDLGLrsFMfFZSrLrFZsSL
                    PmmdzqPrVvPwwTWBwg
                """.trimIndent(), 18
            ),
            row(
                """
                    wMqvLMZHhHMvwLHjbvcjnnSBnvTQFn
                    ttgJtRGJQctTZtZT
                    CrZsJsPPZsGzwwsLwLmpwMDw
                """.trimIndent(), 52
            ),
            row(adventCodeInputFile, 2577)
        ).forEach { (rucksackContents, expectedPriority) ->
            it("should return the sum of priorities ($expectedPriority) for an elf group") {
                val result = sumPrioritiesSharedItemTypesByElfGroups(rucksackContents)

                result shouldBe expectedPriority
            }
        }
    }
})
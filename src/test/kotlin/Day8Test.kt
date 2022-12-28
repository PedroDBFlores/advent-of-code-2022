import io.kotest.core.spec.style.DescribeSpec
import io.kotest.data.row
import io.kotest.matchers.shouldBe

class Day8Test : DescribeSpec({
    describe("Part 1") {
        listOf(
            row(
                """
            222
            212
            222
        """.trimIndent(), 8
            ),
            row(
                """
            33333
            34543
            33333
        """.trimIndent(), 15
            ),
            row(
                """
                30373
                25512
                65332
                33549
                35390
            """.trimIndent(), 21
            )
        ).forEach { (input, expectedCount) ->
            it("returns the expected number of visible trees($expectedCount)") {
                val result = visibleTrees(input)

                result shouldBe expectedCount
            }
        }
    }

    describe("Part 2") {
        listOf(
            row(
                """
               30373
               25512
               65332
               33549
               35390
            """.trimIndent(), 8
            ),
            row(
                """
            33333
            34543
            33333
        """.trimIndent(), 4
            ),
            row(
                """
            3333321
            3454321
            3333321
        """.trimIndent(), 8
            ),
            row(
                """
            222
            212
            222
        """.trimIndent(), 1
            ),
        ).forEach { (input, expectedScenicValue) ->
            val result = scenicScore(input)

            result shouldBe expectedScenicValue
        }
    }
})
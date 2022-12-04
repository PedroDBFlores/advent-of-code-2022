import io.kotest.core.spec.style.DescribeSpec
import io.kotest.data.row
import io.kotest.matchers.shouldBe

class Day4Test : DescribeSpec({
    val adventCodeInputFile = getResourceFile("day4_input.txt")

    listOf(
        "2-4,6-8",
        "2-3,4-5",
        "5-7,7-9",
    ).forEach { elvesSections ->
        it("should not count when both elves have not got overlapping sections ($elvesSections)") {
            val result = campCleanup(elvesSections)

            result shouldBe 0
        }
    }

    listOf(
        "6-6,4-6",
        "4-6,3-8",
        "2-8,3-7",
    ).forEach { elvesSections ->
        it("should count when both elves have got overlapping sections ($elvesSections)") {
            val result = campCleanup(elvesSections)

            result shouldBe 1
        }
    }

    listOf(
        row(
            """
            2-4,6-8
            2-3,4-5
            5-7,7-9
            2-8,3-7
            6-6,4-6
            2-6,4-8
        """.trimIndent(), 2
        ),
        row(
            """
            2-4,6-8
            2-9,4-5
            5-7,4-9
            2-8,3-7
            6-6,4-6
            2-6,4-8
        """.trimIndent(), 4
        ),
        row(adventCodeInputFile, 3)
    ).forEach { (elvesSections, expectedCount) ->
        it("should count the number of overlapping sections ($expectedCount)") {
            val result = campCleanup(elvesSections)

            result shouldBe expectedCount
        }
    }
})
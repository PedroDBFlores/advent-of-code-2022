import io.kotest.core.spec.style.DescribeSpec
import io.kotest.data.row
import io.kotest.matchers.shouldBe

class Day9Test : DescribeSpec({
    listOf(
        row("U 2", 2),
        row("R 3", 3),
        row(
            """
            R 3
            U 2
            R 1
        """.trimIndent(), 4
        ),
        row(
            """
            R 3
            U 3
            L 1
        """.trimIndent(), 5
        ),
        row(
            """
            R 4
            U 4
            L 3
            D 1
            R 4
            D 1
            L 5
            R 2
        """.trimIndent(), 13
        ),
    ).forEach { (input, expectedTailVisits) ->
        it("expects $expectedTailVisits tail visits") {
            val result = tailVisit(input)

            result shouldBe expectedTailVisits
        }
    }
})
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.data.row
import io.kotest.matchers.shouldBe

/*
The winner of the whole tournament is the player with the highest score.
Your total score is the sum of your scores for each round.
The score for a single round is the score for the shape you selected
(1 for Rock, 2 for Paper, and 3 for Scissors) plus the score for the outcome of
the round (0 if you lost, 3 if the round was a draw, and 6 if you won).
*/

class Day2Test : DescribeSpec({
    val adventCodeInputFile = getResourceFile("day2_input.txt")

    describe("Part 1") {
        listOf(
            row(
                """
            A Y
            B X
            C Z
        """.trimIndent(), 15
            ),
            row(
                """
            B Z
            C X
            A Y
        """.trimIndent(), 24
            ),
            row(
                """
            B X
            C Y
            A Z
        """.trimIndent(), 6
            ),
            row(adventCodeInputFile, 10994) // Tried and tested
        ).forEach { (strategyGuide, expectedScore) ->
            it("should return the total score for a strategy guide") {
                val result = calculateTotalScorePart1(strategyGuide)

                result shouldBe expectedScore
            }
        }
    }

    describe("Part 2") {
        /*
        The Elf finishes helping with the tent and sneaks back over to you.
        "Anyway, the second column says how the round needs to end: X means you need to lose,
        Y means you need to end the round in a draw, and Z means you need to win. Good luck!"
         */
        listOf(
            row(
                """
                A Y
                B X
                C Z
                """.trimIndent(), 12
            ),
            row(
                """
                A X
                B X
                C X
                """.trimIndent(), 6
            ),
            row(
                """
                A Z
                B Z
                C Z
                """.trimIndent(), 24
            ),
            row(adventCodeInputFile, 12526) // Tried and tested
        ).forEach { (newStrategyGuide, expectedScore) ->
            it("should return the total score for the actual strategy guide") {
                val result = calculateTotalScorePart2(newStrategyGuide)

                result shouldBe expectedScore
            }
        }
    }
})
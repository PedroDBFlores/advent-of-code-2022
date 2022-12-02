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

class RockPaperScissorsTest : DescribeSpec({
    val adventCodeInputFile = RockPaperScissorsTest::class
        .java
        .getResource("rockpaperscissors_input.txt")
        .readText()

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
            val result = calculateTotalScore(strategyGuide)

            result shouldBe expectedScore
        }
    }
})
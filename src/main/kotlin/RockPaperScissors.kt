import java.lang.IllegalArgumentException

/*
    Dec 2 - Rock Paper Scissors
 */

fun calculateTotalScore(strategyGuide: String): Int {
    val plays = strategyGuide.split("\\r?\\n|\\r".toRegex())
    val x = plays.map { extractPlay(it) }
    return x.sumOf { it.calculatePoints() }
}

fun calculateTotalScorePart2(strategyGuide: String): Int {
    val plays = strategyGuide.split("\\r?\\n|\\r".toRegex())
    val x = plays.map { extractCommandedPlay(it) }
    return x.sumOf { it.calculatePoints() }
}

private fun extractPlay(play: String): RockPaperScissorsPlay {
    val (opponentPlay, myPlay) = play.split(" ")
    return RockPaperScissorsPlay(opponentPlay.toExpectedShape(), myPlay.toExpectedShape())
}

private fun extractCommandedPlay(play: String): RockPaperScissorsPlay {
    val (opponentPlay, myPlay) = play.split(" ")
    return RockPaperScissorsPlay(
        opponentPlay.toExpectedShape(),
        myPlay.toCommandedShape(opponentPlay.toExpectedShape())
    )
}

data class RockPaperScissorsPlay(
    val opponentGameShape: GameShape,
    val myGameShape: GameShape
) {
    fun calculatePoints() = myGameShape.points + calculatePlayPoints()

    private fun calculatePlayPoints() = with(myGameShape) {
        if (strongAgainst == opponentGameShape) {
            return@with 6
        } else if (weakAgainst == opponentGameShape) {
            return@with 0
        } else {
            return 3
        }
    }
}

sealed class GameShape(val points: Int) {
    abstract val strongAgainst: GameShape
    abstract val weakAgainst: GameShape
}

object Rock : GameShape(1) {
    override val strongAgainst: GameShape = Scissors
    override val weakAgainst: GameShape = Paper
}

object Paper : GameShape(2) {
    override val strongAgainst: GameShape = Rock
    override val weakAgainst: GameShape = Scissors
}

object Scissors : GameShape(3) {
    override val strongAgainst: GameShape = Paper
    override val weakAgainst: GameShape = Rock
}

fun String.toExpectedShape(): GameShape =
    when (this) {
        "A", "X" -> Rock
        "B", "Y" -> Paper
        "C", "Z" -> Scissors
        else -> throw IllegalArgumentException("No shape could be constructed from $this")
    }

fun String.toCommandedShape(other: GameShape): GameShape =
    when (this) {
        "X" -> other.strongAgainst
        "Y" -> other
        "Z" -> other.weakAgainst
        else -> throw IllegalArgumentException("No shape could be constructed from $this")
    }
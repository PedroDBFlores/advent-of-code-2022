import domain.Challenge
import domain.Part

val supportFiles = (1..3).associateWith { day ->
    getResourceFile("day${day}_input.txt")
}

val challenges = arrayOf(
    Challenge(
        day = 1, name = "Calorie Counting", parts = listOf(
            Part(number = 1, input = supportFiles[1]!!, action = ::calorieCounting),
            Part(number = 2, input = supportFiles[1]!!, action = ::topThreeCalories),
        )
    )
)

fun main(args: Array<String>) {
    println("Welcome to Advent of Code 2022:")
    challenges.forEach { (day, name, parts) ->
        println("Challenge for Day $day - $name")
        parts.forEach { part ->
            println("$part result -> ${part.calculateResult()}")
        }
        println("****************************************")
    }
}
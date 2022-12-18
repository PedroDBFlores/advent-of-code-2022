import domain.Challenge
import domain.Part

val supportFiles = (1..8).associateWith { day ->
    getResourceFile("day${day}_input.txt")
}

val challenges = arrayOf(
    Challenge(
        day = 1, name = "Calorie Counting", parts = listOf(
            Part(number = 1, input = supportFiles[1]!!, action = ::calorieCounting),
            Part(number = 2, input = supportFiles[1]!!, action = ::topThreeCalories),
        )
    ),
    Challenge(
        day = 2, name = "Rock Paper Scissors", parts = listOf(
            Part(number = 1, input = supportFiles[2]!!, action = ::calculateTotalScorePart1),
            Part(number = 2, input = supportFiles[2]!!, action = ::calculateTotalScorePart2),
        )
    ),
    Challenge(
        day = 3, name = "Rucksack Optimization", parts = listOf(
            Part(number = 1, input = supportFiles[3]!!, action = ::sumPrioritiesSharedItemTypesInRucksacks),
            Part(number = 2, input = supportFiles[3]!!, action = ::sumPrioritiesSharedItemTypesByElfGroups),
        )
    ),
    Challenge(
        day = 4, name = "Camp Cleanup", parts = listOf(
            Part(number = 1, input = supportFiles[4]!!, action = ::countElvesThatFullyOverlap),
            Part(number = 2, input = supportFiles[4]!!, action = ::countElvesThatPartiallyOverlap),
        )
    ),
    Challenge(
        day = 5, name = "Supply Stacks", parts = listOf(
            Part(number = 1, input = supportFiles[5]!!, action = ::rearrangeCratesPart1),
            Part(number = 2, input = supportFiles[5]!!, action = ::rearrangeCratesPart2),
        )
    ),
    Challenge(
        day = 6, name = "Tuning Trouble", parts = listOf(
            Part(number = 1, input = supportFiles[6]!!, action = ::numberOfCharactersProcessedBeforeSOPMarkerPart1),
            Part(number = 2, input = supportFiles[6]!!, action = ::numberOfCharactersProcessedBeforeSOPMarkerPart2),
        )
    ),
    Challenge(
        day = 7, name = "No Space Left On Device", parts = listOf(
            Part(number = 1, input = supportFiles[7]!!, action = ::smallDirectoriesSum),
            Part(number = 2, input = supportFiles[7]!!, action = ::smallestDeletableDir),
        )
    ),
    Challenge(
        day = 8, name = "Treetop Tree House", parts = listOf(
            Part(number = 1, input = supportFiles[8]!!, action = ::visibleTrees),
        )
    )
)

fun main() {
    println("Welcome to Advent of Code 2022:")
    println("*".repeat(40))
    challenges.forEach { (day, name, parts) ->
        println("Challenge for Day $day - $name")
        parts.forEach { part ->
            println("$part result -> ${part.calculateResult()}")
        }
        println("*".repeat(40))
    }
}
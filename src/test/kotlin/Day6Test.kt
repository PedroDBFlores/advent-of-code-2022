import io.kotest.core.spec.style.DescribeSpec
import io.kotest.data.row
import io.kotest.matchers.shouldBe

class Day6Test : DescribeSpec({
    describe("Part 1") {
        listOf(
            row("mjqjpqmgbljsphdztnvjfqwrcgsmlb", 7),
            row("bvwbjplbgvbhsrlpgdmjqwftvncz", 5),
            row("nppdvjthqldpwncqszvftbrmjlhg", 6),
            row("nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg", 10),
            row("zcfzfwzzqfrljwzlrfnpqdbhtmscgvjw", 11),
        ).forEach { (dataStream, expectedStartOfPacketMarker) ->
            it("finds the number of characters to be processed before finding the start-of-packet marker at position $expectedStartOfPacketMarker, size 4 marker") {
                val result = numberOfCharactersProcessedBeforeSOPMarkerPart1(dataStream)

                result shouldBe expectedStartOfPacketMarker
            }
        }
    }

    describe("Part 2") {
        listOf(
            row("mjqjpqmgbljsphdztnvjfqwrcgsmlb", 19),
            row("bvwbjplbgvbhsrlpgdmjqwftvncz", 23),
            row("nppdvjthqldpwncqszvftbrmjlhg", 23),
            row("nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg", 29),
            row("zcfzfwzzqfrljwzlrfnpqdbhtmscgvjw", 26),
        ).forEach { (dataStream, expectedStartOfPacketMarker) ->
            it("finds the number of characters to be processed before finding the start-of-packet marker at position $expectedStartOfPacketMarker, size 4 marker") {
                val result = numberOfCharactersProcessedBeforeSOPMarkerPart2(dataStream)

                result shouldBe expectedStartOfPacketMarker
            }
        }
    }
})
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.data.row
import io.kotest.matchers.shouldBe

class Day5Test : DescribeSpec({
    describe("Part 1") {
        listOf(
            row(
                """
                    [R]
                [Z] [X] [Y]
                 1   2   3
                
                move 1 from 2 to 1
            """.trimIndent(), "RXY"
            ),
            row(
                """
                        [D]    
                    [N] [C]    
                    [Z] [M] [P]
                     1   2   3 
                    
                    move 1 from 2 to 1
                    move 3 from 1 to 3
                    move 2 from 2 to 1
                    move 1 from 1 to 2
                """.trimIndent(), "CMZ"
            )
        ).forEach { (cratesArrangement, expectedTopmostCrates) ->
            it("returns the topmost crate in the stack ($expectedTopmostCrates)") {
                val result = rearrangeCratesPart1(cratesArrangement)

                result shouldBe expectedTopmostCrates
            }
        }
    }

    describe("Part 2") {
        listOf(
            row(
                """
                        [D]    
                    [N] [C]    
                    [Z] [M] [P]
                     1   2   3 
                    
                    move 1 from 2 to 1
                    move 3 from 1 to 3
                    move 2 from 2 to 1
                    move 1 from 1 to 2
                """.trimIndent(), "MCD"
            )
        ).forEach { (cratesArrangement, expectedTopmostCrates) ->
            it("returns the topmost crate in the stack ($expectedTopmostCrates), while moving a block of crates all at once") {
                val result = rearrangeCratesPart2(cratesArrangement)

                result shouldBe expectedTopmostCrates
            }
        }
    }
})
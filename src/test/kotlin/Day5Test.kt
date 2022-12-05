import io.kotest.core.spec.style.DescribeSpec
import io.kotest.data.row
import io.kotest.matchers.shouldBe

class Day5Test : DescribeSpec({
    listOf(
        row(
            """
                    [R]
                [Z] [X] [Y]
                 1   2   3
                
                move 1 from 2 to 1
            """.trimIndent(), "ZRY"
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
            val result = rearrangeCrates(cratesArrangement)

            result shouldBe expectedTopmostCrates
        }
    }
})
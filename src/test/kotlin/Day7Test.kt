import io.kotest.core.spec.style.DescribeSpec
import io.kotest.data.row
import io.kotest.matchers.shouldBe

class Day7Test : DescribeSpec({
    listOf(
        row(
            """
            ${'$'} cd /
            ${'$'} ls
            dir a
            15000 b.txt
            40000 c.obj
            dir b
            10000 hello.asm
        """.trimIndent(), "65000"
        )
//        row(
//            """
//            ${'$'} cd /
//            ${'$'} ls
//            dir a
//            14848514 b.txt
//            8504156 c.dat
//            dir d
//            ${'$'} cd a
//            ${'$'} ls
//            dir e
//            29116 f
//            2557 g
//            62596 h.lst
//            ${'$'} cd e
//            ${'$'} ls
//            584 i
//            ${'$'} cd ..
//            ${'$'} cd ..
//            ${'$'} cd d
//            ${'$'} ls
//            4060174 j
//            8033020 d.log
//            5626152 d.ext
//            7214296 k
//        """.trimIndent(), "95437"
//        )
    ).forEach { (input, expectedResult) ->
        it("should return the sum of the directories under 100000 $expectedResult") {
            val result = smallDirectoriesSum(input)

            result shouldBe expectedResult
        }
    }
})
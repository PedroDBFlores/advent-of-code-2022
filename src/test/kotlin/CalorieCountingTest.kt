import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.should
import io.kotest.matchers.shouldBe

class CalorieCountingTest : DescribeSpec({
    val adventCodeInputFile = CalorieCountingTest::class
        .java
        .getResource("caloriecounting_input.txt")
        .readText()

    it("returns the highest calories for one elf") {
        val input = """
            1000
            2000
            3000
        """.trimIndent()

        val result = calorieCounting(input)

        result shouldBe 6000
    }

    it("returns the highest calories for two elves") {
        val input = """
            1000
            2000
            3000
            
            5000
        """.trimIndent()

        val result = calorieCounting(input)

        result shouldBe 6000
    }

    it("returns the highest calories for five elves") {
        val input = """
            1000
            2000
            3000
            
            4000
            
            5000
            6000
            
            7000
            8000
            9000
            
            10000
        """.trimIndent()

        val result = calorieCounting(input)

        result shouldBe 24000
    }

    it("returns the highest calories for my input") {
        val result = calorieCounting(adventCodeInputFile)

        result shouldBe 69310 // Tried and tested by the challenge
    }
})
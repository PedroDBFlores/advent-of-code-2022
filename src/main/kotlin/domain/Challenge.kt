package domain

data class Challenge(val day: Int, val name: String, val parts: List<Part>)

data class Part(val number: Int, private val input: String, private val action: (String) -> Any) {
    fun calculateResult(): Any = action(input)

    override fun toString() = "Part $number"
}
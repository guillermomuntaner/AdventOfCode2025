fun main() {
    fun parseInput(input: List<String>) = input
        .map {
            it
        }

    fun part1(input: List<String>): Int {
        return 0
    }

    fun part2(input: List<String>): Int {
        return 0
    }

    val day = "05"

    val testInput1 = readInput("Day${day}_test")
    part1(testInput1).also { it.println() }.checkEqualTo(0)
    part2(testInput1).also { it.println() }.checkEqualTo(0)

    val input = readInput("Day${day}")
    part1(input).also { it.println() }.checkEqualTo(0)
    part2(input).also { it.println() }.checkEqualTo(0)
}

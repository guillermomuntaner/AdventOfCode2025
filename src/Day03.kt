fun main() {
    fun parseInput(input: List<String>) = input
        .map { it.map { it.digitToInt().toLong() } }

    fun outputJoltage(input: List<String>, count: Int): Long {
        return parseInput(input)
            .map { bank ->
                var joltage = 0L
                var largestI = -1
                for (n in 0..<count) {
                    var biggest = 0L
                    for (i in (largestI + 1)..bank.lastIndex - count + n + 1) {
                        val value = bank[i]
                        if (value > biggest) {
                            largestI = i
                            biggest = value
                        }
                        if (biggest == 9L) break
                    }
                    joltage = joltage * 10L + biggest
                }
                joltage
            }
            .sum()
    }

    val day = "03"

    val testInput1 = readInput("Day${day}_test")
    outputJoltage(testInput1, 2).also { it.println() }.checkEqualTo(357L)
    outputJoltage(testInput1, 12).also { it.println() }.checkEqualTo(3121910778619L)

    val input = readInput("Day${day}")
    outputJoltage(input, 2).also { it.println() }.checkEqualTo(17074L)
    outputJoltage(input, 12).also { it.println() }.checkEqualTo(169512729575727L)
}

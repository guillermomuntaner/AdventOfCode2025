import kotlin.math.abs

fun main() {
    fun parseInput(input: List<String>): List<Int> = input
        .map { line ->
            line.substring(1).toInt().let {
                if(line[0] == 'L') {
                    -it
                } else {
                    it
                }
            }
        }

    checkEquals(50, 50.mod(100))
    checkEquals(0, 0.mod(100))
    checkEquals(99, (-1).mod(100))
    checkEquals(0, 100.mod(100))
    checkEquals(0, (-100).mod(100))

    fun part1(input: List<String>): Int {
        return parseInput(input).fold(50 to 0) { (acc, count), instruction ->
            var count = count
            val newAcc = (acc + instruction).mod (100)
            if (newAcc == 0) count += 1
            newAcc to count
        }.second
    }

    fun Int.countWraps(range: Int): Int =
        abs(this.floorDiv(range))

    checkEquals(0, 0.countWraps(100))
    checkEquals(0, 1.countWraps(100))
    checkEquals(0, 4.countWraps(100))
    checkEquals(0, 99.countWraps(100))
    checkEquals(1, 100.countWraps(100))
    checkEquals(1, 150.countWraps(100))
    checkEquals(2, 250.countWraps(100))
    checkEquals(1, (-1).countWraps(100))
    checkEquals(1, (-100).countWraps(100))
    checkEquals(2, (-101).countWraps(100))
    checkEquals(3, (-201).countWraps(100))

    fun part2(input: List<String>): Int {
        return parseInput(input).fold(50 to 0) { (acc, count), instruction ->
            var count = count
            instruction.println()
            val newAcc = (acc + instruction)
            count += newAcc.countWraps(100)
            if (acc == 0 && newAcc < 0) {
                count -= 1
            }
            val modAcc = newAcc.mod(100)
            if (modAcc == 0 && newAcc <= 0) {
                count += 1
            }
            modAcc.println()
            count.println()
            modAcc to count
        }.second
    }

    val testInput = readInput("Day01_test")
    checkEquals(3, part1(testInput))

    checkEquals(1, part2(listOf("R60")))
    checkEquals(1, part2(listOf("L60")))
    checkEquals(1, part2(listOf("R50")))
    checkEquals(1, part2(listOf("R49", "R1")))
    checkEquals(1, part2(listOf("L50")))
    checkEquals(1, part2(listOf("L51")))
    checkEquals(1, part2(listOf("L50", "L1")))
    checkEquals(1, part2(listOf("L49", "L1")))
    checkEquals(1, part2(listOf("L49", "L1", "L1")))
    checkEquals(2, part2(listOf("L50", "L100")))
    checkEquals(3, part2(listOf("L50", "L200")))
    checkEquals(6, part2(testInput))

    // Read the input from the `src/Day01.txt` file.
    val input = readInput("Day01")
    checkEquals(980, part1(input))
    checkEquals(5961, part2(input))
}

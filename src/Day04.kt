fun main() {
    val directions = listOf(
        Pair(-1, 0),
        Pair(-1, 1),
        Pair(0, 1),
        Pair(1, 1),
        Pair(1, 0),
        Pair(1, -1),
        Pair(0, -1),
        Pair(-1, -1)
    )

    fun part1(input: List<String>): Int {

        val rows = input.size
        val cols = input[0].length
        var count = 0

        for (y in 0 until rows) {
            for (x in 0 until cols) {
                if (input[y][x] != '@') continue
                var emptySpots = 0
                directions@ for ((dx, dy) in directions) {
                    val row = y + dy
                    val col = x + dx
                    if (row !in 0 until rows || col !in 0 until cols || input[row][col] != '@') {
                        continue@directions
                    }
                    emptySpots++
                }
                if (emptySpots < 4) {
                    count++
                }
            }
        }

        return count
    }


    fun part2(input: List<String>): Int {

        val rows = input.size
        val cols = input[0].length

        val removedRolls: MutableSet<Pair<Int, Int>> = mutableSetOf()

        while (true) {

            val toBeRemovedRolls: MutableSet<Pair<Int, Int>> = mutableSetOf()
            for (y in 0 until rows) {
                for (x in 0 until cols) {
                    if (input[y][x] != '@' || removedRolls.contains(y to x)) continue
                    var emptySpots = 0
                    directions@ for ((dx, dy) in directions) {
                        val row = y + dy
                        val col = x + dx
                        if (row !in 0 until rows || col !in 0 until cols || input[row][col] != '@'|| removedRolls.contains(row to col)) {
                            continue@directions
                        }
                        emptySpots++
                    }
                    if (emptySpots < 4) {
                        toBeRemovedRolls.add(y to x)
                    }
                }
            }
            if (toBeRemovedRolls.isEmpty()) break
            removedRolls.addAll(toBeRemovedRolls)
        }

        return removedRolls.size
    }

    val day = "04"

    val testInput1 = readInput("Day${day}_test")
    part1(testInput1).also { it.println() }.checkEqualTo(13)
    part2(testInput1).also { it.println() }.checkEqualTo(43)

    val input = readInput("Day${day}")
    part1(input).also { it.println() }.checkEqualTo(1478)
    part2(input).also { it.println() }.checkEqualTo(9120)
}

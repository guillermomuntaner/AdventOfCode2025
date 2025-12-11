fun main() {

    fun parseInput(input: List<String>) = input
        .first()
        .split(',')
        .map { line ->
            line.split('-').map { it.toLong() }.toPair()
        }

    fun part1(input: List<String>): Long {
        return parseInput(input).map { (first, last) -> first..last }
            .flatMap { range ->
                range.mapNotNull {
                    it.takeIf {
                        val id = it.toString()
                        if (id.length % 2 != 0) return@takeIf false
                        val left = id.substring(0, id.length / 2)
                        val right = id.substring(id.length / 2)
                        return@takeIf left == right
                    }
                }
            }
            .toSet()
            .sum()
    }

    fun part2(input: List<String>): Long {
        return parseInput(input)
            .map { (first, last) -> first..last }
            .flatMap { range ->
                range.mapNotNull {
                    it.takeIf {
                        val id = it.toString()
                        (2..(id.length)).any { nParts ->
                            if (id.length % nParts != 0) return@any false
                            val partLength = id.length / nParts
                            (0..< nParts)
                                .map { id.substring(it * partLength, (it + 1) * partLength) }
                                .distinct()
                                .size == 1
                        }
                    }
                }
            }
            .toSet()
            .sum()
    }

    val day = "02"

    val testInput1 = readInput("Day${day}_test")
    part1(testInput1).also { it.println() }.checkEqualTo(1227775554L)
    part2(testInput1).also { it.println() }.checkEqualTo(4174379265L)

    val input = readInput("Day${day}")
    part1(input).also { it.println() }.checkEqualTo(43952536386L)
    part2(input).also { it.println() }.checkEqualTo(54486209192L)
}

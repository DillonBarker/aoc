package net.dill.y2024

import org.junit.jupiter.api.Test

internal class Day11Test {
    @Test
    fun testSolvePart1() {
        val day = Day11()

        assert(day.part1() == 55312)
    }

    @Test
    fun testSolvePart2() {
        val day = Day11()

        assert(day.part2() == 65601038650482)
    }
}
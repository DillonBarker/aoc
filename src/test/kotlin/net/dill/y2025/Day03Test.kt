package net.dill.y2025

import org.junit.jupiter.api.Test

internal class Day03Test {
    @Test
    fun testSolvePart1() {
        val day = Day03()

        assert(day.part1() == 357)
    }

    @Test
    fun testSolvePart2() {
        val day = Day03()

        assert(day.part2() == 3121910778619L)
    }
}

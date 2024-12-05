package net.dill.y2024

import org.junit.jupiter.api.Test

internal class Day01Test {
    @Test
    fun testPart1() {
        val day = Day01()

        assert(day.part1() == 11)
    }

    @Test
    fun testPart2() {
        val day = Day01()

        assert(day.part2() == 31)
    }
}
package net.dill.y2018

import org.junit.jupiter.api.Test

internal class Day01Test {
    @Test
    fun `should solve part1`() {
        val day = Day01()

        assert(day.part1() == 1)
    }

    @Test
    fun `should solve part2`() {
        val day = Day01()

        assert(day.part2() == 14)
    }
}
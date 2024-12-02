package net.dill.y2024

import org.junit.jupiter.api.Test

internal class Day02Test {
    @Test
    fun `should solve part1`() {
        val day = Day02()

        assert(day.part1() == 2)
    }

    @Test
    fun `should solve part2`() {
        val day = Day02()

        assert(day.part2() == 4)
    }
}
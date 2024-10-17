package net.dill.y2024

import org.junit.jupiter.api.Test

internal class Day00Test {
    @Test
    fun `should solve part1`() {
        val day00 = Day00()

        assert(day00.part1() == 1)
    }

    @Test
    fun `should solve part2`() {
        val day00 = Day00()

        assert(day00.part2() == 14)
    }
}
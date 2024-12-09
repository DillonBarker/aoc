package net.dill.y2024

import net.dill.resourceLines
import org.junit.jupiter.api.Test

internal class Day09Test {
    @Test
    fun testSolvePart1() {
        val day = Day09()

        assert(day.part1() == 1928L)
    }

    @Test
    fun testSolvePart2() {
        val day = Day09()

        assert(day.part2() == 2858L)
    }

    @Test
    fun testSolvePart2B() {
        val day = object : Day09() {
            override val data by lazy { resourceLines(2024, 9, "b") }
        }

        assert(day.part2() == 2858L)
    }
}
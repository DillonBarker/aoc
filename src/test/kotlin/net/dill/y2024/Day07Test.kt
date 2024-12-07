package net.dill.y2024

import net.dill.resourceLines
import org.junit.jupiter.api.Test

internal class Day07Test {
    @Test
    fun testSolvePart1() {
        val day = object : Day07() {
            override val data by lazy { resourceLines(2024, 7, "a") }
        }

        assert(day.part1() == 3749L)
    }

    @Test
    fun testSolvePart2() {
        val day = object : Day07() {
            override val data by lazy { resourceLines(2024, 7, "b") }
        }

        assert(day.part2() == 11387L)
    }
}
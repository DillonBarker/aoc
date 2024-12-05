package net.dill.y2024

import net.dill.resourceLines
import org.junit.jupiter.api.Test

internal class Day05Test {
    @Test
    fun testPart1() {
        val day = object : Day05() {
            override val data by lazy { resourceLines(2024, 5, "a") }
        }

        assert(day.part1() == 143)
    }

    @Test
    fun testPart2() {
        val day = object : Day05() {
            override val data by lazy { resourceLines(2024, 5, "b") }
        }

        assert(day.part2() == 123)
    }
}
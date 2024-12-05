package net.dill.y2024

import net.dill.resourceLines
import org.junit.jupiter.api.Test

internal class Day03Test {
    @Test
    fun testPart1() {
        val day = object : Day03() {
            override val data by lazy { resourceLines(2024, 3, "a") }
        }

        assert(day.part1() == 161)
    }

    @Test
    fun testPart2() {
        val day = object : Day03() {
            override val data by lazy { resourceLines(2024, 3, "b") }
        }

        assert(day.part2() == 48)
    }
}
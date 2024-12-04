package net.dill.y2024

import net.dill.resourceLines
import org.junit.jupiter.api.Test

internal class Day04Test {
    @Test
    fun solvePart1A() {
        val day = object : Day04() {
            override val data by lazy { resourceLines(2024, 4, "a") }
        }
        assert(day.part1() == 4)
    }

    @Test
    fun solvePart1B() {
        val day = object : Day04() {
            override val data by lazy { resourceLines(2024, 4, "b") }
        }
        assert(day.part1() == 18)
    }

    @Test
    fun solvePart2A() {
        val day = object : Day04() {
            override val data by lazy { resourceLines(2024, 4, "c") }
        }

        assert(day.part2() == 1)
    }

    @Test
    fun solvePart2B() {
        val day = object : Day04() {
            override val data by lazy { resourceLines(2024, 4, "d") }
        }

        assert(day.part2() == 9)
    }
}
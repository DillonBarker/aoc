package net.dill.y2024

import net.dill.resourceLines
import org.junit.jupiter.api.Test

internal class Day10Test {
    @Test
    fun testSolvePart1A() {
        val day = object : Day10() {
            override val data by lazy { resourceLines(2024, 10, "a") }
        }

        assert(day.part1() == 2)
    }

    @Test
    fun testSolvePart1B() {
        val day = object : Day10() {
            override val data by lazy { resourceLines(2024, 10, "b") }
        }

        assert(day.part1() == 4)
    }

    @Test
    fun testSolvePart1C() {
        val day = object : Day10() {
            override val data by lazy { resourceLines(2024, 10, "c") }
        }

        assert(day.part1() == 3)
    }

    @Test
    fun testSolvePart1D() {
        val day = object : Day10() {
            override val data by lazy { resourceLines(2024, 10, "d") }
        }

        assert(day.part1() == 36)
    }

    @Test
    fun testSolvePart2A() {
        val day = object : Day10() {
            override val data by lazy { resourceLines(2024, 10, "e") }
        }

        assert(day.part2() == 3)
    }

    @Test
    fun testSolvePart2B() {
        val day = object : Day10() {
            override val data by lazy { resourceLines(2024, 10, "f") }
        }

        assert(day.part2() == 13)
    }

    @Test
    fun testSolvePart2C() {
        val day = object : Day10() {
            override val data by lazy { resourceLines(2024, 10, "g") }
        }

        assert(day.part2() == 227)
    }

    @Test
    fun testSolvePart2D() {
        val day = object : Day10() {
            override val data by lazy { resourceLines(2024, 10, "h") }
        }

        assert(day.part2() == 81)
    }
}
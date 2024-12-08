package net.dill.y2024

import net.dill.resourceLines
import org.junit.jupiter.api.Test

internal class Day08Test {
    @Test
    fun testSolvePart1A() {
        val day = object : Day08() {
            override val data by lazy { resourceLines(2024, 8, "a") }
        }
        assert(day.part1() == 2)
    }

    @Test
    fun testSolvePart1B() {
        val day = object : Day08() {
            override val data by lazy { resourceLines(2024, 8, "b") }
        }
        assert(day.part1() == 4)
    }

    @Test
    fun testSolvePart1C() {
        val day = object : Day08() {
            override val data by lazy { resourceLines(2024, 8, "c") }
        }
        assert(day.part1() == 4)
    }

    @Test
    fun testSolvePart1D() {
        val day = object : Day08() {
            override val data by lazy { resourceLines(2024, 8, "d") }
        }
        assert(day.part1() == 14)
    }

    @Test
    fun testSolvePart2E() {
        val day = object : Day08() {
            override val data by lazy { resourceLines(2024, 8, "e") }
        }

        assert(day.part2() == 9)
    }

    @Test
    fun testSolvePart2D() {
        val day = object : Day08() {
            override val data by lazy { resourceLines(2024, 8, "d") }
        }

        assert(day.part2() == 34)
    }
}
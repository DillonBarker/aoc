package net.dill.y2025

import org.junit.jupiter.api.Test

internal class Day02Test {
    @Test
    fun testSolvePart1() {
        val day = Day02()

        assert(day.part1() == 1227775554L)
    }

    @Test
    fun testSolvePart2() {
        val day = Day02()

        assert(day.part2() == 4174379265L)
    }

    @Test
    fun testSolvePart2_Thrice() {
        val day = Day02()
        val id = "123123123"
        assert(day.hasRepeatedPattern(id))
    }

    @Test
    fun testSolvePart2_Twelve() {
        val day = Day02()
        val id = "1212121212"
        assert(day.hasRepeatedPattern(id))
    }

    @Test
    fun testSolvePart2_Seven() {
        val day = Day02()
        val id = "1111111"
        assert(day.hasRepeatedPattern(id))
    }
}

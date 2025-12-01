package net.dill.y2025

import net.dill.resourceLines
import org.junit.jupiter.api.Test

internal class Day01Test {
    @Test
    fun testSolvePart1() {
        val day = Day01()

        assert(day.part1() == 3)
    }

    @Test
    fun testSolvePart2() {
        val day = Day01()

        val result = day.part2()
        assert(result == 6) { "Expected 6 but got $result" }
    }

    @Test
    fun testPart2_SimpleRightCrossing() {
        // From 50, R50 lands on 0 (crosses once)
        val day = object : Day01() {
            override val data = listOf("R50")
        }
        assert(day.part2() == 1) { "R50 from 50 should cross 0 once, got ${day.part2()}" }
    }

    @Test
    fun testPart2_SimpleLeftCrossing() {
        // From 50, L68 crosses 0 once (goes to 82)
        val day = object : Day01() {
            override val data = listOf("L68")
        }
        assert(day.part2() == 1) { "L68 from 50 should cross 0 once, got ${day.part2()}" }
    }

    @Test
    fun testPart2_MultipleRightCrossings() {
        // From 50, R1000 crosses 0 ten times (100, 200, ..., 1000)
        val day = object : Day01() {
            override val data = listOf("R1000")
        }
        assert(day.part2() == 10) { "R1000 from 50 should cross 0 ten times, got ${day.part2()}" }
    }

    @Test
    fun testPart2_NoRightCrossing() {
        // From 50, R30 doesn't cross 0 (goes to 80)
        val day = object : Day01() {
            override val data = listOf("R30")
        }
        assert(day.part2() == 0) { "R30 from 50 should not cross 0, got ${day.part2()}" }
    }

    @Test
    fun testPart2_NoLeftCrossing() {
        // From 50, L30 doesn't cross 0 (goes to 20)
        val day = object : Day01() {
            override val data = listOf("L30")
        }
        assert(day.part2() == 0) { "L30 from 50 should not cross 0, got ${day.part2()}" }
    }

    @Test
    fun testPart2_FromZeroLeft() {
        // From 0, L5 doesn't cross 0 (goes to 95, no multiples of 100 hit)
        val day = object : Day01() {
            override val data = listOf("R50", "L5")  // Get to 0 first, then L5
        }
        // R50: 50->100 (cross once, land on 0)
        // L5: 0->95 (no crossing - we're starting at 0, moving backwards 5 positions)
        assert(day.part2() == 1) { "R50 then L5 should cross 0 once, got ${day.part2()}" }
    }

    @Test
    fun testPart2_FromZeroRight() {
        // From 0, R14 doesn't cross 0 (goes to 14)
        val day = object : Day01() {
            override val data = listOf("R50", "R14")  // Get to 0 first, then R14
        }
        // R50: cross once
        // R14: no crossing
        assert(day.part2() == 1) { "R50 then R14 should cross 0 once, got ${day.part2()}" }
    }

    @Test
    fun testPart2_LandExactlyOnZero() {
        // From 50, L50 lands exactly on 0
        val day = object : Day01() {
            override val data = listOf("L50")
        }
        assert(day.part2() == 1) { "L50 from 50 should cross 0 once (land on it), got ${day.part2()}" }
    }

    @Test
    fun testPart2_FromZeroGoingRight100() {
        // From 0, R100 should cross 0 once (at position 100 which is 0)
        val day = object : Day01() {
            override val data = listOf("R50", "R100")
        }
        // R50: cross once at 100 (lands on 0)
        // R100: cross once at 100 (lands on 0 again)
        assert(day.part2() == 2) { "R50 then R100 should cross 0 twice, got ${day.part2()}" }
    }

    @Test
    fun testPart2_FromZeroGoingLeft100() {
        // From 0, L100 should cross 0 once (going backwards a full circle)
        val day = object : Day01() {
            override val data = listOf("R50", "L100")
        }
        // R50: cross once
        // L100: cross once (going from 0 backwards by 100)
        assert(day.part2() == 2) { "R50 then L100 should cross 0 twice, got ${day.part2()}" }
    }
}

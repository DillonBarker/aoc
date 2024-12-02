package net.dill.y2024

import net.dill.Day
import net.dill.resourceLines

class Day02: Day() {
    private val data by lazy { resourceLines(2024, 2) }

    override fun part1(): Int {
        var safe = 0

        data.forEach { line ->
            val levels = line.split(" ").map { it.toInt() }
            var isSafe = false

            if (isSafeReport(levels)) isSafe = true

            if (isSafe) safe++
        }

        return safe
    }

    override fun part2(): Int {
        var safe = 0

        data.forEach { line ->
            val levels = line.split(" ").map { it.toInt() }
            var isSafe = false

            if (isSafeReport(levels)) {
                isSafe = true
            } else {
                for (i in levels.indices) {
                    val modifiedLevels = levels.toMutableList().apply { removeAt(i) }
                    if (isSafeReport(modifiedLevels)) {
                        isSafe = true
                        break
                    }
                }
            }

            if (isSafe) {
                safe++
            }
        }
        return safe
    }

    private fun isSafeReport(levels: List<Int>): Boolean {
        var unsafe = false
        var ascend = false
        var descend = false

        levels.forEachIndexed { i, level ->
            if (i < levels.size - 1) {
                if (level > levels[i + 1]) {
                    descend = true
                    val diff = level - levels[i + 1]
                    if (diff > 3) {
                        unsafe = true
                    }
                } else if (level < levels[i + 1]) {
                    ascend = true
                    val diff = levels[i + 1] - level
                    if (diff > 3) {
                        unsafe = true
                    }
                } else {
                    unsafe = true
                }
            }
        }

        return !unsafe && (ascend && !descend || !ascend && descend)
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            run(Day02())
        }
    }
}
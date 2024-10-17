package net.dill.y2024

import net.dill.resourceLines

class DayTemplate {
    private val data by lazy { resourceLines(2024, 0) }

    fun part1(): Int {
        return 0
    }

    fun part2(): Int {
        return 0
    }

    fun solve() {
        part1()
        part2()
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            DayTemplate().solve()
        }
    }
}
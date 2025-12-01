package net.dill.y2025

import net.dill.Day
import net.dill.resourceLines

open class DayTemplate: Day() {
    override val data by lazy { resourceLines(2025, 0) }

    override fun part1(): Int {
        return 0
    }

    override fun part2(): Int {
        return 0
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            run(DayTemplate())
        }
    }
}

package net.dill.y2024

import net.dill.Day
import net.dill.resourceLines

class DayTemplate: Day() {
    override val data by lazy { resourceLines(2024, 0) }

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
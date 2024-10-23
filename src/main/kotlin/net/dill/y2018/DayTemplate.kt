package net.dill.y2018

import net.dill.Day
import net.dill.resourceLines
import net.dill.y2024.DayTemplate

class DayTemplate: Day() {
    private val data by lazy { resourceLines(2018, 0) }

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
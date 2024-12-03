package net.dill

import kotlin.system.measureNanoTime
import kotlin.system.measureTimeMillis

abstract class Day() {
    protected open val data: List<String> by lazy { emptyList() }

    abstract fun part1(): Any?
    abstract fun part2(): Any?

    open fun solve() {
        println("┌───────────┬────────────────┬─────────────┐")
        println("│   Part    │    Result      │  Time (ns)  │")
        println("├───────────┼────────────────┼─────────────┤")

        val part1Result = part1()
        val part1Time = measureNanoTime { part1() }
        println("│     1     │ %-14d │ %-11d │".format(part1Result, part1Time))

        val part2Result = part2()
        val part2Time = measureNanoTime { part2() }
        println("│     2     │ %-14d │ %-11d │".format(part2Result, part2Time))

        println("└───────────┴────────────────┴─────────────┘")
    }

    companion object {
        @JvmStatic
        fun run(dayInstance: Day) {
            dayInstance.solve()
        }
    }
}